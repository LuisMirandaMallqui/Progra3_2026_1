package pe.edu.pucp.assessment.question.bo;

import pe.edu.pucp.assessment.question.boi.ISingleChoiceQuestionBO;
import pe.edu.pucp.assessment.question.model.SingleChoiceQuestion;
import pe.edu.pucp.assessment.question.persistance.dao.SingleChoiceQuestionDao;
import pe.edu.pucp.assessment.question.persistance.daoImpl.SingleChoiceQuestionImpl;

import java.util.List;

public class SingleChoiceQuestionBOImpl implements ISingleChoiceQuestionBO {

    private SingleChoiceQuestionDao daoSCQ;

    public SingleChoiceQuestionBOImpl() {
        daoSCQ = new SingleChoiceQuestionImpl();
    }

    @Override
    public int insertar(SingleChoiceQuestion question) throws Exception {
        validar(question, false);
        return daoSCQ.insertar(question);
    }

    @Override
    public int modificar(SingleChoiceQuestion question) throws Exception {
        validar(question, true);
        return daoSCQ.modificar(question);
    }

    @Override
    public int eliminar(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("El code de la pregunta debe ser mayor que cero.");
        }
        return daoSCQ.eliminar(id);
    }

    @Override
    public List<SingleChoiceQuestion> listarTodos() throws Exception {
        return daoSCQ.listarTodos();
    }

    @Override
    public SingleChoiceQuestion buscarPorId(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("El code de la pregunta debe ser mayor que cero.");
        }
        return daoSCQ.buscarPorId(id);
    }

    private void validar(SingleChoiceQuestion question, boolean esModificacion) throws Exception {
        if (question == null) {
            throw new Exception("La pregunta no puede ser nula.");
        }
        if (esModificacion && question.getCode() <= 0) {
            throw new Exception("El code es obligatorio para la modificacion.");
        }
        if (question.getPrompt() == null || question.getPrompt().trim().isEmpty()) {
            throw new Exception("El enunciado de la pregunta es obligatorio.");
        }
        if (question.getAvailableOptions() == null || question.getAvailableOptions().size() < 2) {
            throw new Exception("La pregunta debe tener al menos 2 opciones.");
        }
        if (question.getCorrectOption() < 1 || question.getCorrectOption() > question.getAvailableOptions().size()) {
            throw new Exception("La opcion correcta debe estar entre 1 y " + question.getAvailableOptions().size() + ".");
        }
    }
}
