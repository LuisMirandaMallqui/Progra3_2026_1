package pe.edu.pucp.universidad.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import pe.edu.pucp.universidad.manager.DBManager;
import pe.edu.pucp.universidad.dao.DocenteDAO;
import pe.edu.pucp.universidad.model.Docente;
import pe.edu.pucp.universidad.model.Departamento;

public class DocenteDAOImpl implements DocenteDAO {

    @Override
    public Integer insertar(Docente docente) throws Exception {
        String sql = "{ call INSERTAR_DOCENTE(?, ?, ?, ?, ?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, docente.getId() == null ? 0L : docente.getId());
            cs.registerOutParameter(1, Types.BIGINT);
            if (docente.getDepartamento() != null) {
                cs.setLong(2, docente.getDepartamento().getId());
            } else {
                cs.setNull(2, Types.BIGINT);
            }
            cs.setString(3, docente.getCodigo());
            cs.setString(4, docente.getNombreCompleto());
            cs.setString(5, docente.getCategoria());
            cs.setString(6, docente.getDedicacion());
            cs.setBoolean(7, docente.isActivo());
            cs.executeUpdate();
            docente.setId(cs.getLong(1));
            return docente.getId().intValue();
        }
    }

    @Override
    public int modificar(Docente docente) throws Exception {
        String sql = "{ call MODIFICAR_DOCENTE(?, ?, ?, ?, ?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, docente.getId());
            if (docente.getDepartamento() != null) {
                cs.setLong(2, docente.getDepartamento().getId());
            } else {
                cs.setNull(2, Types.BIGINT);
            }
            cs.setString(3, docente.getCodigo());
            cs.setString(4, docente.getNombreCompleto());
            cs.setString(5, docente.getCategoria());
            cs.setString(6, docente.getDedicacion());
            cs.setBoolean(7, docente.isActivo());
            return cs.executeUpdate();
        }
    }

    @Override
    public int eliminar(long id) throws Exception {
        String sql = "{ call ELIMINAR_DOCENTE(?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, id);
            return cs.executeUpdate();
        }
    }

    @Override
    public Docente obtenerPorId(long id) throws Exception {
        String sql = "{ call OBTENER_DOCENTE_X_ID(?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public ArrayList<Docente> listarTodos() throws Exception {
        ArrayList<Docente> lista = new ArrayList<>();
        String sql = "{ call LISTAR_DOCENTES() }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    private Docente mapear(ResultSet rs) throws Exception {
        Docente docente = new Docente();
        docente.setId(rs.getLong("DOCENTE_ID"));
        Departamento departamento = new Departamento();
        departamento.setId(rs.getLong("DEPARTAMENTO_ID"));
        docente.setDepartamento(departamento);
        docente.setCodigo(rs.getString("CODIGO"));
        docente.setNombreCompleto(rs.getString("NOMBRE_COMPLETO"));
        docente.setCategoria(rs.getString("CATEGORIA"));
        docente.setDedicacion(rs.getString("DEDICACION"));
        docente.setActivo(rs.getBoolean("ACTIVO"));
        return docente;
    }

    private Connection getConnection() throws Exception {
        return DBManager.getInstance().getConnection();
    }

}
