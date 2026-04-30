package pe.edu.pucp.testsoft.examen.impl;

import pe.edu.pucp.testsoft.DaoImplBase;
import pe.edu.pucp.testsoft.alumno.dao.AlumnoDAO;
import pe.edu.pucp.testsoft.alumno.impl.AlumnoImpl;
import pe.edu.pucp.testsoft.examen.dao.ExamenDAO;
import pe.edu.pucp.testsoft.model.alumno.Alumno;
import pe.edu.pucp.testsoft.model.examen.Examen;
import pe.edu.pucp.testsoft.model.pregunta.Pregunta;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamenImpl extends DaoImplBase implements ExamenDAO {

    @Override
    protected String obtenerSPEliminar() {
        return "";
    }

    @Override
    public int insertar(Examen objeto) {
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, objeto.getAlumno().getId());
        parametrosEntrada.put(2, objeto.getTitulo());
        parametrosSalida.put(3, Types.INTEGER);

        dbManager.ejecutarProcedimiento("insertar_examen",
                parametrosEntrada, parametrosSalida);
        objeto.setId((int)parametrosSalida.get(3));
        int orden = 1;
        for(Pregunta pregunta : objeto.getPreguntas()){
            parametrosSalida = new HashMap<>();
            parametrosEntrada = new HashMap<>();
            parametrosEntrada.put(1, objeto.getId());
            parametrosEntrada.put(2, pregunta.getId());
            parametrosEntrada.put(3, orden);
            parametrosSalida.put(4, Types.INTEGER);
            dbManager.ejecutarProcedimiento("insertar_examen_pregunta",
                    parametrosEntrada, parametrosSalida);
            pregunta.setId((int) parametrosSalida.get(1));
            orden++;
        }

        return objeto.getId();
    }

    @Override
    public int modificar(Examen objeto) {
        return 0;
    }

    @Override
    public Examen buscarPorId(int id) {
        Examen examen = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, id);
        ResultSet rs = dbManager.ejecutarProcedimientoLectura(
                "buscar_examen_por_id", parametrosEntrada);
        try {
            if (rs != null && rs.next()) {
                examen = new Examen();
                examen.setId(rs.getInt("id"));
                examen.setTitulo(rs.getString("titulo"));
                examen.setFechaCreacion(rs.getDate("fecha_creacion"));

                // Cargar alumno
                AlumnoDAO alumnoDAO = new AlumnoImpl();
                Alumno alumno = alumnoDAO.buscarPorId(rs.getInt("id_alumno"));
                examen.setAlumno(alumno);

                // Cargar preguntas
                List<Pregunta> preguntas = listarPreguntasPorExamen(examen.getId());
                if (preguntas == null) {
                    preguntas = new ArrayList<>();
                }
                examen.setPreguntas(preguntas);
            }
        } catch (Exception ex) {
            System.err.println("Error al buscar examen: " + ex.getMessage());
        } finally {
            dbManager.cerrarConexion();
        }
        return examen;
    }

    @Override
    public List<Examen> listarTodos() {
        List<Examen> lista = null;
        ResultSet rs = dbManager.ejecutarProcedimientoLectura(
                "listar_examenes", null);
        try {
            while (rs != null && rs.next()) {
                if (lista == null) lista = new ArrayList<>();//Lo pongo acá para no hacer un new en caso el resultSet llegue mal
                Examen e = new Examen();
                e.setId(rs.getInt("id"));
                e.setTitulo(rs.getString("titulo"));
                e.setFechaCreacion(rs.getDate("final_score"));
                // PREGUNTAS
                List<Pregunta> preguntas = new ArrayList<Pregunta>();
                preguntas = listarPreguntasPorExamen(e.getId());
                e.setPreguntas(preguntas);
                //
                // ALUMNO
                //FORMA 1
                Alumno a = null;
                Map<Integer, Object> parametrosEntrada = new HashMap<>();
                parametrosEntrada.put(1, rs.getInt("id_alumno"));
                rs = dbManager.ejecutarProcedimientoLectura(
                        "buscar_alumno_por_id", parametrosEntrada);
                try {
                    if (rs != null && rs.next()) {
                        a = new Alumno();
                        a.setId(rs.getInt("id"));
                        a.setCodigo(rs.getString("codigo"));
                        a.setCorreo(rs.getString("correo"));
                        a.setNombre(rs.getString("nombre"));
                    }
                } catch (Exception ex) {
                    System.out.println("Error al buscar teacher: " + ex.getMessage());
                }
                //FORMA 2
//                AlumnoDAO alumnoDAO = new AlumnoImpl();
//                a = alumnoDAO.buscarPorId(rs.getInt("id_alumno"));
//                //
                e.setAlumno(a);
                lista.add(e);
            }
        } catch (Exception ex) {
            System.out.println("Error al listar assessments: " + ex.getMessage());
        } finally {
            dbManager.cerrarConexion();
        }
        return lista;
    }

    public List<Pregunta> listarPreguntasPorExamen(int id_examen){
        List<Pregunta> lista = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, id_examen);
        ResultSet rs = dbManager.ejecutarProcedimientoLectura(
                "listar_preguntas_por_examen", parametrosEntrada);
        try {
            while (rs != null && rs.next()) {
                if (lista == null) lista = new ArrayList<>();//Lo pongo acá para no hacer un new en caso el resultSet llegue mal
                Pregunta p = new Pregunta();
                p.setId(rs.getInt("id"));
                p.setEnunciado(rs.getString("enunciado"));
                lista.add(p);
            }
        } catch (Exception ex) {
            System.out.println("Error al listar assessments: " + ex.getMessage());
        } finally {
            dbManager.cerrarConexion();
        }
        return lista;
    }

}
