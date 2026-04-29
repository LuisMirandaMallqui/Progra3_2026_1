package pe.edu.pucp.assessment.exam.persistance.daoImpl;

import pe.edu.pucp.assessment.dao.persistance.DaoImplBase;
import pe.edu.pucp.assessment.exam.model.Assessment;
import pe.edu.pucp.assessment.exam.persistance.dao.AssessmentDao;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssessmentImpl extends DaoImplBase implements AssessmentDao {
    @Override
    protected String obtenerSPEliminar() {
        return "ELIMINAR_ASSESSMENT";
    }

    // eliminar(int id) → heredado del padre, no hay que escribir nada

    @Override
    public int insertar(Assessment assessment) {
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);  // _assessment_id OUT
        parametrosEntrada.put(2, assessment.getDurationInMinutes());
        parametrosEntrada.put(3, assessment.getStartDate());
        parametrosEntrada.put(4, assessment.getFinalScore());
        dbManager.ejecutarProcedimiento("INSERTAR_ASSESSMENT",
                parametrosEntrada, parametrosSalida);
        assessment.setIdAssessment((int) parametrosSalida.get(1));
        return assessment.getIdAssessment();
    }

    @Override
    public int modificar(Assessment assessment) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, assessment.getIdAssessment());
        parametrosEntrada.put(2, assessment.getDurationInMinutes());
        parametrosEntrada.put(3, assessment.getStartDate());
        parametrosEntrada.put(4, assessment.getFinalScore());
        return dbManager.ejecutarProcedimiento("MODIFICAR_ASSESSMENT",
                parametrosEntrada, null);
    }

    @Override
    public Assessment buscarPorId(int id) {
        Assessment assessment = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, id);
        ResultSet rs = dbManager.ejecutarProcedimientoLectura(
                "BUSCAR_ASSESSMENT_POR_ID", parametrosEntrada);
        try {
            if (rs != null && rs.next()) {
                assessment = new Assessment();
                assessment.setIdAssessment(rs.getInt("id_assessment"));
                assessment.setDurationInMinutes(rs.getInt("duration_in_minutes"));
                assessment.setStartDate(rs.getDate("start_date"));
                assessment.setFinalScore(rs.getDouble("final_score"));
            }
        } catch (Exception ex) {
            System.out.println("Error al buscar assessment: " + ex.getMessage());
        } finally {
            dbManager.cerrarConexion();
        }
        return assessment;
    }

    @Override
    public List<Assessment> listarTodos() {
        List<Assessment> lista = null;
        ResultSet rs = dbManager.ejecutarProcedimientoLectura(
                "LISTAR_ASSESSMENTS_TODOS", null);
        try {
            while (rs != null && rs.next()) {
                if (lista == null) lista = new ArrayList<>();//Lo pongo acá para no hacer un new en caso el resultSet llegue mal
                Assessment a = new Assessment();
                a.setIdAssessment(rs.getInt("id_assessment"));
                a.setDurationInMinutes(rs.getInt("duration_in_minutes"));
                a.setStartDate(rs.getDate("start_date"));
                a.setFinalScore(rs.getDouble("final_score"));
                lista.add(a);
            }
        } catch (Exception ex) {
            System.out.println("Error al listar assessments: " + ex.getMessage());
        } finally {
            dbManager.cerrarConexion();
        }
        return lista;
    }
}
