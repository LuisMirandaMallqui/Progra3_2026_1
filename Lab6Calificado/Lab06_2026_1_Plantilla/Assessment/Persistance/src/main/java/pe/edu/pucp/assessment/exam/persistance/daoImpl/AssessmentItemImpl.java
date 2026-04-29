package pe.edu.pucp.assessment.exam.persistance.daoImpl;

import pe.edu.pucp.assessment.dao.persistance.DaoImplBase;
import pe.edu.pucp.assessment.exam.model.AssessmentItem;
import pe.edu.pucp.assessment.exam.persistance.dao.AssessmentItemDao;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssessmentItemImpl extends DaoImplBase implements AssessmentItemDao {

    @Override
    protected String obtenerSPEliminar() {
        return "ELIMINAR_ASSESSMENT_ITEM";
    }

    // eliminar(int id) -> heredado del padre

    @Override
    public int insertar(AssessmentItem item) {
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER); // _id_assessment_item OUT
        parametrosEntrada.put(2, item.getIdAssessment());
        parametrosEntrada.put(3, item.getQuestion().getCode());
        parametrosEntrada.put(4, item.getScore());
        dbManager.ejecutarProcedimiento("INSERTAR_ASSESSMENT_ITEM",
                parametrosEntrada, parametrosSalida);
        item.setIdAssessmentItem((int) parametrosSalida.get(1));
        return item.getIdAssessmentItem();
    }

    @Override
    public int modificar(AssessmentItem item) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, item.getIdAssessmentItem());
        parametrosEntrada.put(2, item.getIdAssessment());
        parametrosEntrada.put(3, item.getQuestion().getCode());
        parametrosEntrada.put(4, item.getScore());
        return dbManager.ejecutarProcedimiento("MODIFICAR_ASSESSMENT_ITEM",
                parametrosEntrada, null);
    }

    @Override
    public AssessmentItem buscarPorId(int id) {
        AssessmentItem item = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, id);
        ResultSet rs = dbManager.ejecutarProcedimientoLectura(
                "BUSCAR_ASSESSMENT_ITEM_POR_ID", parametrosEntrada);
        try {
            if (rs != null && rs.next()) {
                item = new AssessmentItem();
                item.setIdAssessmentItem(rs.getInt("id_assessment_item"));
                item.setIdAssessment(rs.getInt("id_assessment"));
                // question se carga aparte o con JOIN segun el SP
                item.setScore(rs.getDouble("score"));
            }
        } catch (Exception ex) {
            System.out.println("Error al buscar assessment item: " + ex.getMessage());
        } finally {
            dbManager.cerrarConexion();
        }
        return item;
    }

    @Override
    public List<AssessmentItem> listarTodos() {
        List<AssessmentItem> lista = null;
        ResultSet rs = dbManager.ejecutarProcedimientoLectura(
                "LISTAR_ASSESSMENT_ITEMS_TODOS", null);
        try {
            while (rs != null && rs.next()) {
                if (lista == null) lista = new ArrayList<>();
                AssessmentItem item = new AssessmentItem();
                item.setIdAssessmentItem(rs.getInt("id_assessment_item"));
                item.setIdAssessment(rs.getInt("id_assessment"));
                item.setScore(rs.getDouble("score"));
                lista.add(item);
            }
        } catch (Exception ex) {
            System.out.println("Error al listar assessment items: " + ex.getMessage());
        } finally {
            dbManager.cerrarConexion();
        }
        return lista;
    }
}
