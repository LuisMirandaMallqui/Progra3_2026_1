package pe.edu.pucp.assessment.teacher.persistance.daoImpl;

import pe.edu.pucp.assessment.dao.persistance.DaoImplBase;
import pe.edu.pucp.assessment.teacher.model.Teacher;
import pe.edu.pucp.assessment.teacher.persistance.dao.TeacherDao;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherImpl extends DaoImplBase implements TeacherDao {

    @Override
    protected String obtenerSPEliminar() {
        return "ELIMINAR_TEACHER";
    }

    @Override
    public int insertar(Teacher teacher) {
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, teacher.getPucpCode());
        parametrosEntrada.put(3, teacher.getFirstName());
        parametrosEntrada.put(4, teacher.getLastName());
        dbManager.ejecutarProcedimiento("INSERTAR_TEACHER", parametrosEntrada, parametrosSalida);
        teacher.setIdTeacher((int) parametrosSalida.get(1));
        return teacher.getIdTeacher();
    }

    @Override
    public int modificar(Teacher teacher) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, teacher.getIdTeacher());
        parametrosEntrada.put(2, teacher.getPucpCode());
        parametrosEntrada.put(3, teacher.getFirstName());
        parametrosEntrada.put(4, teacher.getLastName());
        return dbManager.ejecutarProcedimiento("MODIFICAR_TEACHER", parametrosEntrada, null);
    }

    @Override
    public Teacher buscarPorId(int id) {
        Teacher teacher = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, id);
        ResultSet rs = dbManager.ejecutarProcedimientoLectura("BUSCAR_TEACHER_POR_ID", parametrosEntrada);
        try {
            if (rs != null && rs.next()) {
                teacher = new Teacher();
                teacher.setIdTeacher(rs.getInt("id_teacher"));
                teacher.setPucpCode(rs.getString("pucp_code"));
                teacher.setFirstName(rs.getString("first_name"));
                teacher.setLastName(rs.getString("last_name"));
            }
        } catch (Exception ex) {
            System.out.println("Error al buscar teacher: " + ex.getMessage());
        } finally {
            dbManager.cerrarConexion();
        }
        return teacher;
    }

    @Override
    public List<Teacher> listarTodos() {
        List<Teacher> lista = null;
        ResultSet rs = dbManager.ejecutarProcedimientoLectura("LISTAR_TEACHERS_TODOS", null);
        try {
            while (rs != null && rs.next()) {
                if (lista == null) lista = new ArrayList<>();
                Teacher t = new Teacher();
                t.setIdTeacher(rs.getInt("id_teacher"));
                t.setPucpCode(rs.getString("pucp_code"));
                t.setFirstName(rs.getString("first_name"));
                t.setLastName(rs.getString("last_name"));
                lista.add(t);
            }
        } catch (Exception ex) {
            System.out.println("Error al listar teachers: " + ex.getMessage());
        } finally {
            dbManager.cerrarConexion();
        }
        return lista;
    }
}
