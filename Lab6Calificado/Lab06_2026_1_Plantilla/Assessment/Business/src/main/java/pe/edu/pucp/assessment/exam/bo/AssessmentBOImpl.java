package pe.edu.pucp.assessment.exam.bo;

import pe.edu.pucp.assessment.exam.boi.IAssessmentBO;
import pe.edu.pucp.assessment.exam.model.Assessment;
import pe.edu.pucp.assessment.exam.persistance.dao.AssessmentDao;
import pe.edu.pucp.assessment.exam.persistance.daoImpl.AssessmentImpl;

import java.util.List;

public class AssessmentBOImpl implements IAssessmentBO {

    private AssessmentDao daoAssessment;

    public AssessmentBOImpl() {
        daoAssessment = new AssessmentImpl();
    }

    @Override
    public int insertar(Assessment assessment) throws Exception {
        validar(assessment, false);
        return daoAssessment.insertar(assessment);
    }

    @Override
    public int modificar(Assessment assessment) throws Exception {
        validar(assessment, true);
        return daoAssessment.modificar(assessment);
    }

    @Override
    public int eliminar(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("El id del assessment debe ser mayor que cero.");
        }
        return daoAssessment.eliminar(id);
    }

    @Override
    public List<Assessment> listarTodos() throws Exception {
        return daoAssessment.listarTodos();
    }

    @Override
    public Assessment buscarPorId(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("El id del assessment debe ser mayor que cero.");
        }
        return daoAssessment.buscarPorId(id);
    }

    private void validar(Assessment assessment, boolean esModificacion) throws Exception {
        if (assessment == null) {
            throw new Exception("El assessment no puede ser nulo.");
        }
        if (esModificacion && assessment.getIdAssessment() <= 0) {
            throw new Exception("El id del assessment es obligatorio para la modificacion.");
        }
        validarDuracion(assessment.getDurationInMinutes());
        validarFecha(assessment.getStartDate());
    }

    private void validarDuracion(int duracion) throws Exception {
        if (duracion <= 0) {
            throw new Exception("La duracion del assessment debe ser mayor que cero.");
        }
    }

    private void validarFecha(java.util.Date fecha) throws Exception {
        if (fecha == null) {
            throw new Exception("La fecha de inicio del assessment es obligatoria.");
        }
    }
}
