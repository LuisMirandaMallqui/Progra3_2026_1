package pe.edu.pucp.universidad.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import pe.edu.pucp.universidad.manager.DBManager;
import pe.edu.pucp.universidad.dao.HorarioCursoDAO;
import pe.edu.pucp.universidad.model.HorarioCurso;
import pe.edu.pucp.universidad.model.Curso;
import pe.edu.pucp.universidad.model.Docente;

public class HorarioCursoDAOImpl implements HorarioCursoDAO {

    @Override
    public Integer insertar(HorarioCurso horarioCurso) throws Exception {
        String sql = "{ call INSERTAR_HORARIO_CURSO(?, ?, ?, ?, ?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, horarioCurso.getId() == null ? 0L : horarioCurso.getId());
            cs.registerOutParameter(1, Types.BIGINT);
            if (horarioCurso.getCurso() != null) {
                cs.setLong(2, horarioCurso.getCurso().getId());
            } else {
                cs.setNull(2, Types.BIGINT);
            }
            cs.setString(3, horarioCurso.getSemestre());
            cs.setString(4, horarioCurso.getCodigoHorario());
            if (horarioCurso.getDocente() != null) {
                cs.setLong(5, horarioCurso.getDocente().getId());
            } else {
                cs.setNull(5, Types.BIGINT);
            }
            cs.setInt(6, horarioCurso.getCupoMaximo());
            cs.setString(7, horarioCurso.getEstado());
            cs.executeUpdate();
            horarioCurso.setId(cs.getLong(1));
            return horarioCurso.getId().intValue();
        }
    }

    @Override
    public int modificar(HorarioCurso horarioCurso) throws Exception {
        String sql = "{ call MODIFICAR_HORARIO_CURSO(?, ?, ?, ?, ?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, horarioCurso.getId());
            if (horarioCurso.getCurso() != null) {
                cs.setLong(2, horarioCurso.getCurso().getId());
            } else {
                cs.setNull(2, Types.BIGINT);
            }
            cs.setString(3, horarioCurso.getSemestre());
            cs.setString(4, horarioCurso.getCodigoHorario());
            if (horarioCurso.getDocente() != null) {
                cs.setLong(5, horarioCurso.getDocente().getId());
            } else {
                cs.setNull(5, Types.BIGINT);
            }
            cs.setInt(6, horarioCurso.getCupoMaximo());
            cs.setString(7, horarioCurso.getEstado());
            return cs.executeUpdate();
        }
    }

    @Override
    public int eliminar(long id) throws Exception {
        String sql = "{ call ELIMINAR_HORARIO_CURSO(?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, id);
            return cs.executeUpdate();
        }
    }

    @Override
    public HorarioCurso obtenerPorId(long id) throws Exception {
        String sql = "{ call OBTENER_HORARIO_CURSO_X_ID(?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public ArrayList<HorarioCurso> listarTodos() throws Exception {
        ArrayList<HorarioCurso> lista = new ArrayList<>();
        String sql = "{ call LISTAR_HORARIO_CURSOS() }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    private HorarioCurso mapear(ResultSet rs) throws Exception {
        HorarioCurso horarioCurso = new HorarioCurso();
        horarioCurso.setId(rs.getLong("HORARIO_CURSO_ID"));
        Curso curso = new Curso();
        curso.setId(rs.getLong("CURSO_ID"));
        horarioCurso.setCurso(curso);
        horarioCurso.setSemestre(rs.getString("SEMESTRE"));
        horarioCurso.setCodigoHorario(rs.getString("CODIGO_HORARIO"));
        Docente docente = new Docente();
        docente.setId(rs.getLong("DOCENTE_ID"));
        horarioCurso.setDocente(docente);
        horarioCurso.setCupoMaximo(rs.getInt("CUPO_MAXIMO"));
        horarioCurso.setEstado(rs.getString("ESTADO"));
        return horarioCurso;
    }

    private Connection getConnection() throws Exception {
        return DBManager.getInstance().getConnection();
    }

}
