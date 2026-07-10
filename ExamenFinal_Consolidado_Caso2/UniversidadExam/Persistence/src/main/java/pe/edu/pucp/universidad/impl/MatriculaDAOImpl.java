package pe.edu.pucp.universidad.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import pe.edu.pucp.universidad.manager.DBManager;
import pe.edu.pucp.universidad.dao.MatriculaDAO;
import pe.edu.pucp.universidad.model.Matricula;
import pe.edu.pucp.universidad.model.Estudiante;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class MatriculaDAOImpl implements MatriculaDAO {

    @Override
    public Integer insertar(Matricula matricula) throws Exception {
        String sql = "{ call INSERTAR_MATRICULA(?, ?, ?, ?, ?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, matricula.getId() == null ? 0L : matricula.getId());
            cs.registerOutParameter(1, Types.BIGINT);
            if (matricula.getEstudiante() != null) {
                cs.setLong(2, matricula.getEstudiante().getId());
            } else {
                cs.setNull(2, Types.BIGINT);
            }
            cs.setString(3, matricula.getSemestre());
            cs.setTimestamp(4, toSqlTimestamp(matricula.getFechaInscripcion()));
            cs.setString(5, matricula.getTipoMatricula());
            cs.setString(6, matricula.getEstadoMatricula());
            cs.setString(7, matricula.getModalidad());
            cs.executeUpdate();
            matricula.setId(cs.getLong(1));
            return matricula.getId().intValue();
        }
    }

    @Override
    public int modificar(Matricula matricula) throws Exception {
        String sql = "{ call MODIFICAR_MATRICULA(?, ?, ?, ?, ?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, matricula.getId());
            if (matricula.getEstudiante() != null) {
                cs.setLong(2, matricula.getEstudiante().getId());
            } else {
                cs.setNull(2, Types.BIGINT);
            }
            cs.setString(3, matricula.getSemestre());
            cs.setTimestamp(4, toSqlTimestamp(matricula.getFechaInscripcion()));
            cs.setString(5, matricula.getTipoMatricula());
            cs.setString(6, matricula.getEstadoMatricula());
            cs.setString(7, matricula.getModalidad());
            return cs.executeUpdate();
        }
    }

    @Override
    public int eliminar(long id) throws Exception {
        String sql = "{ call ELIMINAR_MATRICULA(?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, id);
            return cs.executeUpdate();
        }
    }

    @Override
    public Matricula obtenerPorId(long id) throws Exception {
        String sql = "{ call OBTENER_MATRICULA_X_ID(?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public ArrayList<Matricula> listarTodos() throws Exception {
        ArrayList<Matricula> lista = new ArrayList<>();
        String sql = "{ call LISTAR_MATRICULAS() }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    private Matricula mapear(ResultSet rs) throws Exception {
        Matricula matricula = new Matricula();
        matricula.setId(rs.getLong("MATRICULA_ID"));
        Estudiante estudiante = new Estudiante();
        estudiante.setId(rs.getLong("ESTUDIANTE_ID"));
        matricula.setEstudiante(estudiante);
        matricula.setSemestre(rs.getString("SEMESTRE"));
        matricula.setFechaInscripcion(toLocalDateTime(rs.getTimestamp("FECHA_INSCRIPCION")));
        matricula.setTipoMatricula(rs.getString("TIPO_MATRICULA"));
        matricula.setEstadoMatricula(rs.getString("ESTADO_MATRICULA"));
        matricula.setModalidad(rs.getString("MODALIDAD"));
        return matricula;
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
