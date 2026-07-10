package pe.edu.pucp.universidad.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import pe.edu.pucp.universidad.manager.DBManager;
import pe.edu.pucp.universidad.dao.DepartamentoDAO;
import pe.edu.pucp.universidad.model.Departamento;

public class DepartamentoDAOImpl implements DepartamentoDAO {

    @Override
    public Integer insertar(Departamento departamento) throws Exception {
        String sql = "{ call INSERTAR_DEPARTAMENTO(?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, departamento.getId() == null ? 0L : departamento.getId());
            cs.registerOutParameter(1, Types.BIGINT);
            cs.setString(2, departamento.getNombre());
            cs.setBoolean(3, departamento.isActivo());
            cs.executeUpdate();
            departamento.setId(cs.getLong(1));
            return departamento.getId().intValue();
        }
    }

    @Override
    public int modificar(Departamento departamento) throws Exception {
        String sql = "{ call MODIFICAR_DEPARTAMENTO(?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, departamento.getId());
            cs.setString(2, departamento.getNombre());
            cs.setBoolean(3, departamento.isActivo());
            return cs.executeUpdate();
        }
    }

    @Override
    public int eliminar(long id) throws Exception {
        String sql = "{ call ELIMINAR_DEPARTAMENTO(?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, id);
            return cs.executeUpdate();
        }
    }

    @Override
    public Departamento obtenerPorId(long id) throws Exception {
        String sql = "{ call OBTENER_DEPARTAMENTO_X_ID(?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public ArrayList<Departamento> listarTodos() throws Exception {
        ArrayList<Departamento> lista = new ArrayList<>();
        String sql = "{ call LISTAR_DEPARTAMENTOS() }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    private Departamento mapear(ResultSet rs) throws Exception {
        Departamento departamento = new Departamento();
        departamento.setId(rs.getLong("DEPARTAMENTO_ID"));
        departamento.setNombre(rs.getString("NOMBRE"));
        departamento.setActivo(rs.getBoolean("ACTIVO"));
        return departamento;
    }

    private Connection getConnection() throws Exception {
        return DBManager.getInstance().getConnection();
    }

}
