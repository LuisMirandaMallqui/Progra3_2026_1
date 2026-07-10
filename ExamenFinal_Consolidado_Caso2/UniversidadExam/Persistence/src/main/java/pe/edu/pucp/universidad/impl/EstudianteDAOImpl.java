package pe.edu.pucp.universidad.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import pe.edu.pucp.universidad.manager.DBManager;
import pe.edu.pucp.universidad.dao.EstudianteDAO;
import pe.edu.pucp.universidad.model.Estudiante;
import pe.edu.pucp.universidad.model.Especialidad;

public class EstudianteDAOImpl implements EstudianteDAO {

    @Override
    public Integer insertar(Estudiante estudiante) throws Exception {
        String sql = "{ call INSERTAR_ESTUDIANTE(?, ?, ?, ?, ?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, estudiante.getId() == null ? 0L : estudiante.getId());
            cs.registerOutParameter(1, Types.BIGINT);
            if (estudiante.getEspecialidad() != null) {
                cs.setLong(2, estudiante.getEspecialidad().getId());
            } else {
                cs.setNull(2, Types.BIGINT);
            }
            cs.setString(3, estudiante.getCodigoUniversitario());
            cs.setString(4, estudiante.getNombres());
            cs.setString(5, estudiante.getApellidos());
            cs.setString(6, estudiante.getCorreoInstitucional());
            cs.setBoolean(7, estudiante.isActivo());
            cs.executeUpdate();
            estudiante.setId(cs.getLong(1));
            return estudiante.getId().intValue();
        }
    }

    @Override
    public int modificar(Estudiante estudiante) throws Exception {
        String sql = "{ call MODIFICAR_ESTUDIANTE(?, ?, ?, ?, ?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, estudiante.getId());
            if (estudiante.getEspecialidad() != null) {
                cs.setLong(2, estudiante.getEspecialidad().getId());
            } else {
                cs.setNull(2, Types.BIGINT);
            }
            cs.setString(3, estudiante.getCodigoUniversitario());
            cs.setString(4, estudiante.getNombres());
            cs.setString(5, estudiante.getApellidos());
            cs.setString(6, estudiante.getCorreoInstitucional());
            cs.setBoolean(7, estudiante.isActivo());
            return cs.executeUpdate();
        }
    }

    @Override
    public int eliminar(long id) throws Exception {
        String sql = "{ call ELIMINAR_ESTUDIANTE(?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, id);
            return cs.executeUpdate();
        }
    }

    @Override
    public Estudiante obtenerPorId(long id) throws Exception {
        String sql = "{ call OBTENER_ESTUDIANTE_X_ID(?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public ArrayList<Estudiante> listarTodos() throws Exception {
        ArrayList<Estudiante> lista = new ArrayList<>();
        String sql = "{ call LISTAR_ESTUDIANTES() }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    private Estudiante mapear(ResultSet rs) throws Exception {
        Estudiante estudiante = new Estudiante();
        estudiante.setId(rs.getLong("ESTUDIANTE_ID"));
        Especialidad especialidad = new Especialidad();
        especialidad.setId(rs.getLong("ESPECIALIDAD_ID"));
        estudiante.setEspecialidad(especialidad);
        estudiante.setCodigoUniversitario(rs.getString("CODIGO_UNIVERSITARIO"));
        estudiante.setNombres(rs.getString("NOMBRES"));
        estudiante.setApellidos(rs.getString("APELLIDOS"));
        estudiante.setCorreoInstitucional(rs.getString("CORREO_INSTITUCIONAL"));
        estudiante.setActivo(rs.getBoolean("ACTIVO"));
        return estudiante;
    }

    private Connection getConnection() throws Exception {
        return DBManager.getInstance().getConnection();
    }

}
