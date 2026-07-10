package pe.edu.pucp.universidad.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import pe.edu.pucp.universidad.manager.DBManager;
import pe.edu.pucp.universidad.dao.AulaDAO;
import pe.edu.pucp.universidad.model.Aula;

public class AulaDAOImpl implements AulaDAO {

    @Override
    public Integer insertar(Aula aula) throws Exception {
        String sql = "{ call INSERTAR_AULA(?, ?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, aula.getId() == null ? 0L : aula.getId());
            cs.registerOutParameter(1, Types.BIGINT);
            cs.setString(2, aula.getCodigoAula());
            cs.setString(3, aula.getUbicacion());
            cs.setBoolean(4, aula.isActivo());
            cs.executeUpdate();
            aula.setId(cs.getLong(1));
            return aula.getId().intValue();
        }
    }

    @Override
    public int modificar(Aula aula) throws Exception {
        String sql = "{ call MODIFICAR_AULA(?, ?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, aula.getId());
            cs.setString(2, aula.getCodigoAula());
            cs.setString(3, aula.getUbicacion());
            cs.setBoolean(4, aula.isActivo());
            return cs.executeUpdate();
        }
    }

    @Override
    public int eliminar(long id) throws Exception {
        String sql = "{ call ELIMINAR_AULA(?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, id);
            return cs.executeUpdate();
        }
    }

    @Override
    public Aula obtenerPorId(long id) throws Exception {
        String sql = "{ call OBTENER_AULA_X_ID(?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public ArrayList<Aula> listarTodos() throws Exception {
        ArrayList<Aula> lista = new ArrayList<>();
        String sql = "{ call LISTAR_AULAS() }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    private Aula mapear(ResultSet rs) throws Exception {
        Aula aula = new Aula();
        aula.setId(rs.getLong("AULA_ID"));
        aula.setCodigoAula(rs.getString("UNI_CODIGO_AULA"));
        aula.setUbicacion(rs.getString("UNI_UBICACION"));
        aula.setActivo(rs.getBoolean("UNI_ACTIVO"));
        return aula;
    }

    private Connection getConnection() throws Exception {
        return DBManager.getInstance().getConnection();
    }

}
