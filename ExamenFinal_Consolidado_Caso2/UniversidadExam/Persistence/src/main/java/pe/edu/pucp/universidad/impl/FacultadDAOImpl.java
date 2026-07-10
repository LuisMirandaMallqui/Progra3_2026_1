package pe.edu.pucp.universidad.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import pe.edu.pucp.universidad.manager.DBManager;
import pe.edu.pucp.universidad.dao.FacultadDAO;
import pe.edu.pucp.universidad.model.Facultad;

public class FacultadDAOImpl implements FacultadDAO {

    @Override
    public Integer insertar(Facultad facultad) throws Exception {
        String sql = "{ call INSERTAR_FACULTAD(?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, facultad.getId() == null ? 0L : facultad.getId());
            cs.registerOutParameter(1, Types.BIGINT);
            cs.setString(2, facultad.getNombre());
            cs.setBoolean(3, facultad.isActivo());
            cs.executeUpdate();
            facultad.setId(cs.getLong(1));
            return facultad.getId().intValue();
        }
    }

    @Override
    public int modificar(Facultad facultad) throws Exception {
        String sql = "{ call MODIFICAR_FACULTAD(?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, facultad.getId());
            cs.setString(2, facultad.getNombre());
            cs.setBoolean(3, facultad.isActivo());
            return cs.executeUpdate();
        }
    }

    @Override
    public int eliminar(long id) throws Exception {
        String sql = "{ call ELIMINAR_FACULTAD(?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, id);
            return cs.executeUpdate();
        }
    }

    @Override
    public Facultad obtenerPorId(long id) throws Exception {
        String sql = "{ call OBTENER_FACULTAD_X_ID(?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public ArrayList<Facultad> listarTodos() throws Exception {
        ArrayList<Facultad> lista = new ArrayList<>();
        String sql = "{ call LISTAR_FACULTADS() }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    private Facultad mapear(ResultSet rs) throws Exception {
        Facultad facultad = new Facultad();
        facultad.setId(rs.getLong("FACULTAD_ID"));
        facultad.setNombre(rs.getString("NOMBRE"));
        facultad.setActivo(rs.getBoolean("ACTIVO"));
        return facultad;
    }

    private Connection getConnection() throws Exception {
        return DBManager.getInstance().getConnection();
    }

}
