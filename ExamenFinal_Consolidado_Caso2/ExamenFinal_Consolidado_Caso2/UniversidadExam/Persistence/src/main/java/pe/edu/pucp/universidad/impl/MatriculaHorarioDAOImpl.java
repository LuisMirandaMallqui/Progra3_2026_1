package pe.edu.pucp.universidad.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import pe.edu.pucp.universidad.manager.DBManager;
import pe.edu.pucp.universidad.dao.MatriculaHorarioDAO;
import pe.edu.pucp.universidad.model.HorarioCurso;
import pe.edu.pucp.universidad.model.Matricula;
import pe.edu.pucp.universidad.model.MatriculaHorario;

public class MatriculaHorarioDAOImpl implements MatriculaHorarioDAO {

    @Override
    public int insertar(MatriculaHorario elemento) throws Exception {
        String sql = "{ call INSERTAR_MATRICULA_HORARIO(?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, elemento.getMatricula().getId());
            cs.setLong(2, elemento.getHorarioCurso().getId());
            cs.setTimestamp(3, toSqlTimestamp(elemento.getFechaRegistro()));
            return cs.executeUpdate();
        }
    }

    @Override
    public int insertarValidado(MatriculaHorario elemento) throws Exception {
        String sql = "{ call REGISTRAR_MATRICULA_HORARIO_VALIDADO(?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, elemento.getMatricula().getId());
            cs.setLong(2, elemento.getHorarioCurso().getId());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.execute();
            return cs.getInt(3);
        }
    }

    @Override
    public int modificar(MatriculaHorario elemento) throws Exception {
        String sql = "{ call MODIFICAR_MATRICULA_HORARIO(?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, elemento.getMatricula().getId());
            cs.setLong(2, elemento.getHorarioCurso().getId());
            cs.setTimestamp(3, toSqlTimestamp(elemento.getFechaRegistro()));
            return cs.executeUpdate();
        }
    }

    @Override
    public int eliminar(long idMatricula, long idHorarioCurso) throws Exception {
        String sql = "{ call ELIMINAR_MATRICULA_HORARIO(?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, idMatricula);
            cs.setLong(2, idHorarioCurso);
            return cs.executeUpdate();
        }
    }

    @Override
    public MatriculaHorario obtenerPorId(long idMatricula, long idHorarioCurso) throws Exception {
        String sql = "{ call OBTENER_MATRICULA_HORARIO_X_ID(?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, idMatricula);
            cs.setLong(2, idHorarioCurso);
            try (ResultSet rs = cs.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public ArrayList<MatriculaHorario> listarTodos() throws Exception {
        ArrayList<MatriculaHorario> lista = new ArrayList<>();
        String sql = "{ call LISTAR_MATRICULAS_HORARIOS() }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    private MatriculaHorario mapear(ResultSet rs) throws Exception {
        MatriculaHorario elemento = new MatriculaHorario();

        Matricula matricula = new Matricula();
        matricula.setId(rs.getLong("MATRICULA_ID"));
        elemento.setMatricula(matricula);

        HorarioCurso horarioCurso = new HorarioCurso();
        horarioCurso.setId(rs.getLong("HORARIO_CURSO_ID"));
        elemento.setHorarioCurso(horarioCurso);

        elemento.setFechaRegistro(toLocalDateTime(rs.getTimestamp("FECHA_REGISTRO")));
        return elemento;
    }

    private Timestamp toSqlTimestamp(java.time.LocalDateTime value) {
        return value == null ? null : Timestamp.valueOf(value);
    }

    private java.time.LocalDateTime toLocalDateTime(Timestamp value) {
        return value == null ? null : value.toLocalDateTime();
    }

    private Connection getConnection() throws Exception {
        return DBManager.getInstance().getConnection();
    }
}
