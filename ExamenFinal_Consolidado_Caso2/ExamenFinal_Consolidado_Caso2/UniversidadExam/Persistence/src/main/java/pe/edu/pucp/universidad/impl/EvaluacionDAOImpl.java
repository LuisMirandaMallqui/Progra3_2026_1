package pe.edu.pucp.universidad.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import pe.edu.pucp.universidad.manager.DBManager;
import pe.edu.pucp.universidad.dao.EvaluacionDAO;
import pe.edu.pucp.universidad.model.Evaluacion;
import pe.edu.pucp.universidad.model.HorarioCurso;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class EvaluacionDAOImpl implements EvaluacionDAO {

    @Override
    public Integer insertar(Evaluacion evaluacion) throws Exception {
        String sql = "{ call INSERTAR_EVALUACION(?, ?, ?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, evaluacion.getId() == null ? 0L : evaluacion.getId());
            cs.registerOutParameter(1, Types.BIGINT);
            if (evaluacion.getHorarioCurso() != null) {
                cs.setLong(2, evaluacion.getHorarioCurso().getId());
            } else {
                cs.setNull(2, Types.BIGINT);
            }
            cs.setString(3, evaluacion.getTipoEvaluacion());
            cs.setDouble(4, evaluacion.getPeso());
            cs.setTimestamp(5, toSqlTimestamp(evaluacion.getFechaEvaluacion()));
            cs.executeUpdate();
            evaluacion.setId(cs.getLong(1));
            return evaluacion.getId().intValue();
        }
    }

    @Override
    public int modificar(Evaluacion evaluacion) throws Exception {
        String sql = "{ call MODIFICAR_EVALUACION(?, ?, ?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, evaluacion.getId());
            if (evaluacion.getHorarioCurso() != null) {
                cs.setLong(2, evaluacion.getHorarioCurso().getId());
            } else {
                cs.setNull(2, Types.BIGINT);
            }
            cs.setString(3, evaluacion.getTipoEvaluacion());
            cs.setDouble(4, evaluacion.getPeso());
            cs.setTimestamp(5, toSqlTimestamp(evaluacion.getFechaEvaluacion()));
            return cs.executeUpdate();
        }
    }

    @Override
    public int eliminar(long id) throws Exception {
        String sql = "{ call ELIMINAR_EVALUACION(?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, id);
            return cs.executeUpdate();
        }
    }

    @Override
    public Evaluacion obtenerPorId(long id) throws Exception {
        String sql = "{ call OBTENER_EVALUACION_X_ID(?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public ArrayList<Evaluacion> listarTodos() throws Exception {
        ArrayList<Evaluacion> lista = new ArrayList<>();
        String sql = "{ call LISTAR_EVALUACIONS() }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    private Evaluacion mapear(ResultSet rs) throws Exception {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId(rs.getLong("EVALUACION_ID"));
        HorarioCurso horarioCurso = new HorarioCurso();
        horarioCurso.setId(rs.getLong("HORARIO_CURSO_ID"));
        evaluacion.setHorarioCurso(horarioCurso);
        evaluacion.setTipoEvaluacion(rs.getString("TIPO_EVALUACION"));
        evaluacion.setPeso(rs.getDouble("PESO"));
        evaluacion.setFechaEvaluacion(toLocalDateTime(rs.getTimestamp("FECHA_EVALUACION")));
        return evaluacion;
    }

    private Connection getConnection() throws Exception {
        return DBManager.getInstance().getConnection();
    }

    private Timestamp toSqlTimestamp(LocalDateTime value) {
        return value == null ? null : Timestamp.valueOf(value);
    }

    private LocalDateTime toLocalDateTime(Timestamp value) {
        return value == null ? null : value.toLocalDateTime();
    }

}
