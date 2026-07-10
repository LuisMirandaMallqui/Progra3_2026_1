package pe.edu.pucp.universidad.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import pe.edu.pucp.universidad.manager.DBManager;
import pe.edu.pucp.universidad.dao.CursoPrerrequisitoDAO;
import pe.edu.pucp.universidad.model.Curso;
import pe.edu.pucp.universidad.model.CursoPrerrequisito;

public class CursoPrerrequisitoDAOImpl implements CursoPrerrequisitoDAO {

    @Override
    public int insertar(CursoPrerrequisito elemento) throws Exception {
        String sql = "{ call INSERTAR_CURSO_PRERREQUISITO(?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, elemento.getCurso().getId());
            cs.setLong(2, elemento.getCursoPrerreq().getId());
            cs.setBoolean(3, elemento.isActivo());
            return cs.executeUpdate();
        }
    }

    @Override
    public int modificar(CursoPrerrequisito elemento) throws Exception {
        String sql = "{ call MODIFICAR_CURSO_PRERREQUISITO(?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, elemento.getCurso().getId());
            cs.setLong(2, elemento.getCursoPrerreq().getId());
            cs.setBoolean(3, elemento.isActivo());
            return cs.executeUpdate();
        }
    }

    @Override
    public int eliminar(long idCurso, long idCursoPrerreq) throws Exception {
        String sql = "{ call ELIMINAR_CURSO_PRERREQUISITO(?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, idCurso);
            cs.setLong(2, idCursoPrerreq);
            return cs.executeUpdate();
        }
    }

    @Override
    public CursoPrerrequisito obtenerPorId(long idCurso, long idCursoPrerreq) throws Exception {
        String sql = "{ call OBTENER_CURSO_PRERREQUISITO_X_ID(?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, idCurso);
            cs.setLong(2, idCursoPrerreq);
            try (ResultSet rs = cs.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public ArrayList<CursoPrerrequisito> listarTodos() throws Exception {
        ArrayList<CursoPrerrequisito> lista = new ArrayList<>();
        String sql = "{ call LISTAR_CURSOS_PRERREQUISITOS() }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    private CursoPrerrequisito mapear(ResultSet rs) throws Exception {
        CursoPrerrequisito elemento = new CursoPrerrequisito();
        Curso curso = new Curso();
        curso.setId(rs.getLong("CURSO_ID"));
        elemento.setCurso(curso);

        Curso cursoPrerreq = new Curso();
        cursoPrerreq.setId(rs.getLong("CURSO_PRERREQ_ID"));
        elemento.setCursoPrerreq(cursoPrerreq);

        elemento.setActivo(rs.getBoolean("ACTIVO"));
        return elemento;
    }

    private Connection getConnection() throws Exception {
        return DBManager.getInstance().getConnection();
    }
}
