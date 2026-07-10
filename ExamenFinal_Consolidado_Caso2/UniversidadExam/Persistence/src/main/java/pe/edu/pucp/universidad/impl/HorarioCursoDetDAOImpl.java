package pe.edu.pucp.universidad.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import pe.edu.pucp.universidad.manager.DBManager;
import pe.edu.pucp.universidad.dao.HorarioCursoDetDAO;
import pe.edu.pucp.universidad.model.HorarioCursoDet;
import pe.edu.pucp.universidad.model.HorarioCurso;
import pe.edu.pucp.universidad.model.Aula;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class HorarioCursoDetDAOImpl implements HorarioCursoDetDAO {

    @Override
    public Integer insertar(HorarioCursoDet horarioCursoDet) throws Exception {
        String sql = "{ call INSERTAR_HORARIO_CURSO_DET(?, ?, ?, ?, ?, ?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, horarioCursoDet.getId() == null ? 0L : horarioCursoDet.getId());
            cs.registerOutParameter(1, Types.BIGINT);
            if (horarioCursoDet.getHorarioCurso() != null) {
                cs.setLong(2, horarioCursoDet.getHorarioCurso().getId());
            } else {
                cs.setNull(2, Types.BIGINT);
            }
            cs.setString(3, horarioCursoDet.getTipoSesion());
            cs.setInt(4, horarioCursoDet.getDiaSemana());
            cs.setTimestamp(5, toSqlTimestamp(horarioCursoDet.getHoraInicio()));
            cs.setTimestamp(6, toSqlTimestamp(horarioCursoDet.getHoraFin()));
            if (horarioCursoDet.getAula() != null) {
                cs.setLong(7, horarioCursoDet.getAula().getId());
            } else {
                cs.setNull(7, Types.BIGINT);
            }
            cs.setString(8, horarioCursoDet.getFrecuencia());
            cs.executeUpdate();
            horarioCursoDet.setId(cs.getLong(1));
            return horarioCursoDet.getId().intValue();
        }
    }

    @Override
    public int modificar(HorarioCursoDet horarioCursoDet) throws Exception {
        String sql = "{ call MODIFICAR_HORARIO_CURSO_DET(?, ?, ?, ?, ?, ?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, horarioCursoDet.getId());
            if (horarioCursoDet.getHorarioCurso() != null) {
                cs.setLong(2, horarioCursoDet.getHorarioCurso().getId());
            } else {
                cs.setNull(2, Types.BIGINT);
            }
            cs.setString(3, horarioCursoDet.getTipoSesion());
            cs.setInt(4, horarioCursoDet.getDiaSemana());
            cs.setTimestamp(5, toSqlTimestamp(horarioCursoDet.getHoraInicio()));
            cs.setTimestamp(6, toSqlTimestamp(horarioCursoDet.getHoraFin()));
            if (horarioCursoDet.getAula() != null) {
                cs.setLong(7, horarioCursoDet.getAula().getId());
            } else {
                cs.setNull(7, Types.BIGINT);
            }
            cs.setString(8, horarioCursoDet.getFrecuencia());
            return cs.executeUpdate();
        }
    }

    @Override
    public int eliminar(long id) throws Exception {
        String sql = "{ call ELIMINAR_HORARIO_CURSO_DET(?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, id);
            return cs.executeUpdate();
        }
    }

    @Override
    public HorarioCursoDet obtenerPorId(long id) throws Exception {
        String sql = "{ call OBTENER_HORARIO_CURSO_DET_X_ID(?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public ArrayList<HorarioCursoDet> listarTodos() throws Exception {
        ArrayList<HorarioCursoDet> lista = new ArrayList<>();
        String sql = "{ call LISTAR_HORARIO_CURSO_DETS() }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    private HorarioCursoDet mapear(ResultSet rs) throws Exception {
        HorarioCursoDet horarioCursoDet = new HorarioCursoDet();
        horarioCursoDet.setId(rs.getLong("HORARIO_CURSO_DET_ID"));
        HorarioCurso horarioCurso = new HorarioCurso();
        horarioCurso.setId(rs.getLong("HORARIO_CURSO_ID"));
        horarioCursoDet.setHorarioCurso(horarioCurso);
        horarioCursoDet.setTipoSesion(rs.getString("TIPO_SESION"));
        horarioCursoDet.setDiaSemana(rs.getInt("DIA_SEMANA"));
        horarioCursoDet.setHoraInicio(toLocalDateTime(rs.getTimestamp("HORA_INICIO")));
        horarioCursoDet.setHoraFin(toLocalDateTime(rs.getTimestamp("HORA_FIN")));
        Aula aula = new Aula();
        aula.setId(rs.getLong("AULA_ID"));
        horarioCursoDet.setAula(aula);
        horarioCursoDet.setFrecuencia(rs.getString("FRECUENCIA"));
        return horarioCursoDet;
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
