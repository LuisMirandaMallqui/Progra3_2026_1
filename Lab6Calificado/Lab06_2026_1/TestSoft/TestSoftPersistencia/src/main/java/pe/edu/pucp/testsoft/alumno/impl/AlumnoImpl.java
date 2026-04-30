package pe.edu.pucp.testsoft.alumno.impl;

import pe.edu.pucp.testsoft.DaoImplBase;
import pe.edu.pucp.testsoft.alumno.dao.AlumnoDAO;
import pe.edu.pucp.testsoft.model.alumno.Alumno;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlumnoImpl extends DaoImplBase implements AlumnoDAO {

    @Override
    protected String obtenerSPEliminar() {
        return "ELIMINAR_TEACHER";
    }

    @Override
    public int insertar(Alumno alumno) {
        return 0;
    }

    @Override
    public int modificar(Alumno alumno) {
       return 0;
    }

    @Override
    public Alumno buscarPorId(int id) {
        Alumno alumno = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, id);
        ResultSet rs = dbManager.ejecutarProcedimientoLectura("buscar_alumno_por_id", parametrosEntrada);
        try {
            if (rs != null && rs.next()) {
                alumno = new Alumno();
                alumno.setId(rs.getInt("id"));
                alumno.setCodigo(rs.getString("codigo"));
                alumno.setCorreo(rs.getString("correo"));
                alumno.setNombre(rs.getString("nombre"));
            }
        } catch (Exception ex) {
            System.out.println("Error al buscar teacher: " + ex.getMessage());
        } finally {
            dbManager.cerrarConexion();
        }
        return alumno;
    }

    @Override
    public List<Alumno> listarTodos() {
        List<Alumno> lista = null;
        ResultSet rs = dbManager.ejecutarProcedimientoLectura("listar_alumnos", null);
        try {
            while (rs != null && rs.next()) {
                if (lista == null) lista = new ArrayList<>();
                Alumno a = new Alumno();
                a.setId(rs.getInt("id"));
                a.setCodigo(rs.getString("codigo"));
                a.setCorreo(rs.getString("correo"));
                a.setNombre(rs.getString("nombre"));
                lista.add(a);
            }
        } catch (Exception ex) {
            System.out.println("Error al listar teachers: " + ex.getMessage());
        } finally {
            dbManager.cerrarConexion();
        }
        return lista;
    }

}
