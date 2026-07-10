package pe.edu.pucp.universidad.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import pe.edu.pucp.universidad.manager.DBManager;
import pe.edu.pucp.universidad.dao.EspecialidadDAO;
import pe.edu.pucp.universidad.model.Especialidad;
import pe.edu.pucp.universidad.model.Facultad;

public class EspecialidadDAOImpl implements EspecialidadDAO {

    @Override
    public Integer insertar(Especialidad especialidad) throws Exception {
        String sql = "{ call INSERTAR_ESPECIALIDAD(?, ?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, especialidad.getId() == null ? 0L : especialidad.getId());
            cs.registerOutParameter(1, Types.BIGINT);
            if (especialidad.getFacultad() != null) {
                cs.setLong(2, especialidad.getFacultad().getId());
            } else {
                cs.setNull(2, Types.BIGINT);
            }
            cs.setString(3, especialidad.getNombre());
            cs.setBoolean(4, especialidad.isActivo());
            cs.executeUpdate();
            especialidad.setId(cs.getLong(1));
            return especialidad.getId().intValue();
        }
    }

    @Override
    public int modificar(Especialidad especialidad) throws Exception {
        String sql = "{ call MODIFICAR_ESPECIALIDAD(?, ?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, especialidad.getId());
            if (especialidad.getFacultad() != null) {
                cs.setLong(2, especialidad.getFacultad().getId());
            } else {
                cs.setNull(2, Types.BIGINT);
            }
            cs.setString(3, especialidad.getNombre());
            cs.setBoolean(4, especialidad.isActivo());
            return cs.executeUpdate();
        }
    }

    @Override
    public int eliminar(long id) throws Exception {
        String sql = "{ call ELIMINAR_ESPECIALIDAD(?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, id);
            return cs.executeUpdate();
        }
    }

    @Override
    public Especialidad obtenerPorId(long id) throws Exception {
        String sql = "{ call OBTENER_ESPECIALIDAD_X_ID(?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public ArrayList<Especialidad> listarTodos() throws Exception {
        ArrayList<Especialidad> lista = new ArrayList<>();
        String sql = "{ call LISTAR_ESPECIALIDADS() }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    private Especialidad mapear(ResultSet rs) throws Exception {
        Especialidad especialidad = new Especialidad();
        especialidad.setId(rs.getLong("ESPECIALIDAD_ID"));
        Facultad facultad = new Facultad();
        facultad.setId(rs.getLong("FACULTAD_ID"));
        especialidad.setFacultad(facultad);
        especialidad.setNombre(rs.getString("NOMBRE"));
        especialidad.setActivo(rs.getBoolean("ACTIVO"));
        return especialidad;
    }

    private Connection getConnection() throws Exception {
        return DBManager.getInstance().getConnection();
    }

}
