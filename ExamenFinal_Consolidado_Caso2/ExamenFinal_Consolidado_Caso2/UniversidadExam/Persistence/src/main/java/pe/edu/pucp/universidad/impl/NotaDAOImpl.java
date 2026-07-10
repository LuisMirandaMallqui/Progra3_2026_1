package pe.edu.pucp.universidad.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import pe.edu.pucp.universidad.manager.DBManager;
import pe.edu.pucp.universidad.dao.NotaDAO;
import pe.edu.pucp.universidad.model.Nota;
import pe.edu.pucp.universidad.model.Evaluacion;
import pe.edu.pucp.universidad.model.Matricula;
import pe.edu.pucp.universidad.model.HorarioCurso;

public class NotaDAOImpl implements NotaDAO {

    @Override
    public Integer insertar(Nota nota) throws Exception {
        String sql = "{ call INSERTAR_NOTA(?, ?, ?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, nota.getId() == null ? 0L : nota.getId());
            cs.registerOutParameter(1, Types.BIGINT);
            if (nota.getEvaluacion() != null) {
                cs.setLong(2, nota.getEvaluacion().getId());
            } else {
                cs.setNull(2, Types.BIGINT);
            }
            if (nota.getMatricula() != null) {
                cs.setLong(3, nota.getMatricula().getId());
            } else {
                cs.setNull(3, Types.BIGINT);
            }
            if (nota.getHorarioCurso() != null) {
                cs.setLong(4, nota.getHorarioCurso().getId());
            } else {
                cs.setNull(4, Types.BIGINT);
            }
            cs.setDouble(5, nota.getCalificacion());
            cs.executeUpdate();
            nota.setId(cs.getLong(1));
            return nota.getId().intValue();
        }
    }

    @Override
    public int modificar(Nota nota) throws Exception {
        String sql = "{ call MODIFICAR_NOTA(?, ?, ?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, nota.getId());
            if (nota.getEvaluacion() != null) {
                cs.setLong(2, nota.getEvaluacion().getId());
            } else {
                cs.setNull(2, Types.BIGINT);
            }
            if (nota.getMatricula() != null) {
                cs.setLong(3, nota.getMatricula().getId());
            } else {
                cs.setNull(3, Types.BIGINT);
            }
            if (nota.getHorarioCurso() != null) {
                cs.setLong(4, nota.getHorarioCurso().getId());
            } else {
                cs.setNull(4, Types.BIGINT);
            }
            cs.setDouble(5, nota.getCalificacion());
            return cs.executeUpdate();
        }
    }

    @Override
    public int eliminar(long id) throws Exception {
        String sql = "{ call ELIMINAR_NOTA(?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, id);
            return cs.executeUpdate();
        }
    }

    @Override
    public Nota obtenerPorId(long id) throws Exception {
        String sql = "{ call OBTENER_NOTA_X_ID(?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public ArrayList<Nota> listarTodos() throws Exception {
        ArrayList<Nota> lista = new ArrayList<>();
        String sql = "{ call LISTAR_NOTAS() }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    private Nota mapear(ResultSet rs) throws Exception {
        Nota nota = new Nota();
        nota.setId(rs.getLong("NOTA_ID"));
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId(rs.getLong("EVALUACION_ID"));
        nota.setEvaluacion(evaluacion);
        Matricula matricula = new Matricula();
        matricula.setId(rs.getLong("MATRICULA_ID"));
        nota.setMatricula(matricula);
        HorarioCurso horarioCurso = new HorarioCurso();
        horarioCurso.setId(rs.getLong("HORARIO_CURSO_ID"));
        nota.setHorarioCurso(horarioCurso);
        nota.setCalificacion(rs.getDouble("CALIFICACION"));
        return nota;
    }

    @Override
    public double calcularPromedioFinal(long idMatricula, long idHorarioCurso) throws Exception {
        String sql = "{ call CALCULAR_PROMEDIO_FINAL(?, ?, ?) }";
        try (Connection con = getConnection(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setLong(1, idMatricula);
            cs.setLong(2, idHorarioCurso);
            cs.registerOutParameter(3, Types.DOUBLE);
            cs.execute();
            return cs.getDouble(3);
        }
    }

    private Connection getConnection() throws Exception {
        return DBManager.getInstance().getConnection();
    }

}
