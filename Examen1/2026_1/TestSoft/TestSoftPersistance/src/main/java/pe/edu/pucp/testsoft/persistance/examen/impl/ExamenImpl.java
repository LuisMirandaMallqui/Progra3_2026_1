package pe.edu.pucp.testsoft.persistance.examen.impl;

import pe.edu.pucp.testsoft.config.DBManager;
import pe.edu.pucp.testsoft.model.examen.Examen;
import pe.edu.pucp.testsoft.model.pregunta.Pregunta;
import pe.edu.pucp.testsoft.persistance.alumno.dao.AlumnoDAO;
import pe.edu.pucp.testsoft.persistance.alumno.impl.AlumnoImpl;
import pe.edu.pucp.testsoft.persistance.examen.dao.ExamenDAO;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExamenImpl implements ExamenDAO {
    private final DBManager dbManager = DBManager.getInstance();

    @Override
    public int insertar(Examen examen) {
        try {
            dbManager.iniciarTransaccion();

            // Insertar cabecera del examen
            Map<Integer, Object> entrada = new HashMap<>();
            Map<Integer, Object> salida = new HashMap<>();
            entrada.put(1, examen.getAlumno().getId());
            entrada.put(2, examen.getTitulo());
            salida.put(3, Types.INTEGER);

            dbManager.ejecutarProcedimientoTransaccion(
                    "insertar_examen", entrada, salida);
            examen.setId((int) salida.get(3));

            // Insertar cada pregunta del examen (tabla puente)
            int orden = 1;
            for (Pregunta pregunta : examen.getPreguntas()) {
                Map<Integer, Object> entDet = new HashMap<>();
                Map<Integer, Object> salDet = new HashMap<>();
                entDet.put(1, examen.getId());
                entDet.put(2, pregunta.getId());
                entDet.put(3, orden);
                salDet.put(4, Types.INTEGER);

                dbManager.ejecutarProcedimientoTransaccion(
                        "insertar_examen_pregunta", entDet, salDet);
                orden++;
            }

            dbManager.confirmarTransaccion();
            return examen.getId();

        } catch (SQLException ex) {
            dbManager.cancelarTransaccion();
            System.out.println("Error al insertar examen: " + ex.getMessage());
            return 0;
        }
    }


    @Override
    public Examen buscarPorId(int id) {
        Examen examen = null;
        int idAlumno = 0;
        Map<Integer, Object> entrada = new HashMap<>();
        entrada.put(1, id);

        // Fase 1: leer datos del ResultSet
        try (DBManager.ResultadoConsulta rc = dbManager.ejecutarProcedimientoLectura(
                "buscar_examen_por_id", entrada)) {
            if (rc.getRs().next()) {
                examen = new Examen();
                examen.setId(rc.getRs().getInt("id"));
                examen.setTitulo(rc.getRs().getString("titulo"));
                examen.setFechaCreacion(rc.getRs().getDate("fecha_creacion"));
                idAlumno = rc.getRs().getInt("id_alumno");
            }
        } catch (Exception ex) {
            System.out.println("Error al buscar examen: " + ex.getMessage());
        }

        // Fase 2: cargar relaciones (conexiones separadas)
        if (examen != null) {
            AlumnoDAO alumnoDAO = new AlumnoImpl();
            examen.setAlumno(alumnoDAO.buscarPorId(idAlumno));
            List<Pregunta> preguntas = listarPreguntasPorExamen(examen.getId());
            examen.setPreguntas(preguntas != null ? preguntas : new ArrayList<>());
        }

        return examen;
    }

    @Override
    public List<Examen> listarTodos() {
        List<Examen> lista = null;
        List<Integer> idsAlumno = new ArrayList<>();

        // Fase 1: leer solo del ResultSet
        try (DBManager.ResultadoConsulta rc = dbManager.ejecutarProcedimientoLectura(
                "listar_examenes", null)) {
            while (rc.getRs().next()) {
                if (lista == null) lista = new ArrayList<>();
                Examen e = new Examen();
                e.setId(rc.getRs().getInt("id"));
                e.setTitulo(rc.getRs().getString("titulo"));
                e.setFechaCreacion(rc.getRs().getDate("fecha_creacion"));
                idsAlumno.add(rc.getRs().getInt("id_alumno"));
                lista.add(e);
            }
        } catch (Exception ex) {
            System.out.println("Error al listar examenes: " + ex.getMessage());
        }

        if (lista != null) {
            AlumnoDAO alumnoDAO = new AlumnoImpl();
            for (int i = 0; i < lista.size(); i++) {
                Examen e = lista.get(i);
                e.setAlumno(alumnoDAO.buscarPorId(idsAlumno.get(i)));
                List<Pregunta> preguntas = listarPreguntasPorExamen(e.getId());
                e.setPreguntas(preguntas != null ? preguntas : new ArrayList<>());
            }
        }

        return lista;
    }

    @Override
    public List<Pregunta> listarPreguntasPorExamen(int idExamen) {
        List<Pregunta> lista = null;
        Map<Integer, Object> entrada = new HashMap<>();
        entrada.put(1, idExamen);
        try (DBManager.ResultadoConsulta rc = dbManager.ejecutarProcedimientoLectura(
                "listar_preguntas_por_examen", entrada)) {
            while (rc.getRs().next()) {
                if (lista == null) lista = new ArrayList<>();
                Pregunta p = new Pregunta();
                p.setId(rc.getRs().getInt("id"));
                p.setEnunciado(rc.getRs().getString("enunciado"));
                lista.add(p);
            }
        } catch (Exception ex) {
            System.out.println("Error al listar preguntas por examen: " + ex.getMessage());
        }
        return lista;
    }

    // SP: modificar_examen(IN p_id, IN p_titulo)
    @Override
    public int modificar(Examen examen) {
        Map<Integer, Object> entrada = new HashMap<>();
        entrada.put(1, examen.getId());
        entrada.put(2, examen.getTitulo());
        return dbManager.ejecutarProcedimiento("modificar_examen", entrada, null);
    }

    // SP: eliminar_examen(IN p_id) → borra detalles (examen_pregunta) y luego cabecera
    @Override
    public int eliminar(int id) {
        Map<Integer, Object> entrada = new HashMap<>();
        entrada.put(1, id);
        return dbManager.ejecutarProcedimiento("eliminar_examen", entrada, null);
    }
}
