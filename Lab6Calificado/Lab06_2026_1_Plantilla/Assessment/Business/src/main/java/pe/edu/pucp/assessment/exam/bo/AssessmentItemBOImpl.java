package pe.edu.pucp.assessment.exam.bo;

import pe.edu.pucp.assessment.exam.boi.IAssessmentItemBO;
import pe.edu.pucp.assessment.exam.model.AssessmentItem;
import pe.edu.pucp.assessment.exam.persistance.dao.AssessmentItemDao;
import pe.edu.pucp.assessment.exam.persistance.daoImpl.AssessmentItemImpl;

import java.util.List;

public class AssessmentItemBOImpl implements IAssessmentItemBO {

    private AssessmentItemDao daoAssessmentItem;

    public AssessmentItemBOImpl() {
        daoAssessmentItem = new AssessmentItemImpl();
    }

    @Override
    public int insertar(AssessmentItem item) throws Exception {
        validar(item, false);
        return daoAssessmentItem.insertar(item);
    }

    @Override
    public int modificar(AssessmentItem item) throws Exception {
        validar(item, true);
        return daoAssessmentItem.modificar(item);
    }

    @Override
    public int eliminar(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("El id del assessment item debe ser mayor que cero.");
        }
        return daoAssessmentItem.eliminar(id);
    }

    @Override
    public List<AssessmentItem> listarTodos() throws Exception {
        return daoAssessmentItem.listarTodos();
    }

    @Override
    public AssessmentItem buscarPorId(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("El id del assessment item debe ser mayor que cero.");
        }
        return daoAssessmentItem.buscarPorId(id);
    }

    private void validar(AssessmentItem item, boolean esModificacion) throws Exception {
        if (item == null) {
            throw new Exception("El assessment item no puede ser nulo.");
        }
        if (esModificacion && item.getIdAssessmentItem() <= 0) {
            throw new Exception("El id del assessment item es obligatorio para la modificacion.");
        }
        validarAssessment(item.getIdAssessment());
        validarScore(item.getScore());
    }

    private void validarAssessment(int idAssessment) throws Exception {
        if (idAssessment <= 0) {
            throw new Exception("El id del assessment asociado es obligatorio.");
        }
    }

    private void validarScore(double score) throws Exception {
        if (score < 0) {
            throw new Exception("El puntaje no puede ser negativo.");
        }
    }
}
