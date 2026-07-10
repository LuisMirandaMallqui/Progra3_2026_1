package pe.edu.pucp.universidad.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import pe.edu.pucp.universidad.manager.DBManager;
import pe.edu.pucp.universidad.dao.CursoDAO;
import pe.edu.pucp.universidad.model.Curso;
import pe.edu.pucp.universidad.model.Especialidad;

public class CursoDAOImpl implements CursoDAO {

    @Override
    public Integer insertar(Curso curso) throws Exception {
        String sql = "{ call INSERTAR_CURSO(?, ?, ?, ?, ?, ?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, curso.getId() == null ? 0L : curso.getId());
            cs.registerOutParameter(1, Types.BIGINT);
            if (curso.getEspecialidad() != null) {
                cs.setLong(2, curso.getEspecialidad().getId());
            } else {
                cs.setNull(2, Types.BIGINT);
            }
            cs.setString(3, curso.getCodigo());
            cs.setString(4, curso.getNombre());
            cs.setInt(5, curso.getCreditos());
            cs.setInt(6, curso.getNivelAcademico());
            cs.setString(7, curso.getEstadoCurso());
            cs.setBoolean(8, curso.isActivo());
            cs.executeUpdate();
            curso.setId(cs.getLong(1));
            return curso.getId().intValue();
        }
    }

    @Override
    public int modificar(Curso curso) throws Exception {
        String sql = "{ call MODIFICAR_CURSO(?, ?, ?, ?, ?, ?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, curso.getId());
            if (curso.getEspecialidad() != null) {
                cs.setLong(2, curso.getEspecialidad().getId());
            } else {
                cs.setNull(2, Types.BIGINT);
            }
            cs.setString(3, curso.getCodigo());
            cs.setString(4, curso.getNombre());
            cs.setInt(5, curso.getCreditos());
            cs.setInt(6, curso.getNivelAcademico());
            cs.setString(7, curso.getEstadoCurso());
            cs.setBoolean(8, curso.isActivo());
            return cs.executeUpdate();
        }
    }

    @Override
    public int eliminar(long id) throws Exception {
        String sql = "{ call ELIMINAR_CURSO(?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, id);
            return cs.executeUpdate();
        }
    }

    @Override
    public Curso obtenerPorId(long id) throws Exception {
        String sql = "{ call OBTENER_CURSO_X_ID(?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public ArrayList<Curso> listarTodos() throws Exception {
        ArrayList<Curso> lista = new ArrayList<>();
        String sql = "{ call LISTAR_CURSOS() }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    private Curso mapear(ResultSet rs) throws Exception {
        Curso curso = new Curso();
        curso.setId(rs.getLong("CURSO_ID"));
        Especialidad especialidad = new Especialidad();
        especialidad.setId(rs.getLong("ESPECIALIDAD_ID"));
        curso.setEspecialidad(especialidad);
        curso.setCodigo(rs.getString("CODIGO"));
        curso.setNombre(rs.getString("NOMBRE"));
        curso.setCreditos(rs.getInt("CREDITOS"));
        curso.setNivelAcademico(rs.getInt("NIVEL_ACADEMICO"));
        curso.setEstadoCurso(rs.getString("ESTADO_CURSO"));
        curso.setActivo(rs.getBoolean("ACTIVO"));
        return curso;
    }

    private Connection getConnection() throws Exception {
        return DBManager.getInstance().getConnection();
    }

}
