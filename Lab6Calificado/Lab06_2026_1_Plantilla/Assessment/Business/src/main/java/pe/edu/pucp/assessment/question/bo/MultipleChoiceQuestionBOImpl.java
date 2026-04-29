package pe.edu.pucp.assessment.question.bo;

import pe.edu.pucp.assessment.question.boi.IMultipleChoiceQuestionBO;
import pe.edu.pucp.assessment.question.model.MultipleChoiceQuestion;
import pe.edu.pucp.assessment.question.persistance.dao.MultipleChoiceQuestionDao;
import pe.edu.pucp.assessment.question.persistance.daoImpl.MultipleChoiceQuestionImpl;

import java.util.List;

public class MultipleChoiceQuestionBOImpl implements IMultipleChoiceQuestionBO {

    private MultipleChoiceQuestionDao daoMCQ;

    public MultipleChoiceQuestionBOImpl() {
        daoMCQ = new MultipleChoiceQuestionImpl();
    }

    @Override
    public int insertar(MultipleChoiceQuestion question) throws Exception {
        validar(question, false);
        return daoMCQ.insertar(question);
    }

    @Override
    public int modificar(MultipleChoiceQuestion question) throws Exception {
        validar(question, true);
        return daoMCQ.modificar(question);
    }

    @Override
    public int eliminar(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("El code de la pregunta debe ser mayor que cero.");
        }
        return daoMCQ.eliminar(id);
    }

    @Override
    public List<MultipleChoiceQuestion> listarTodos() throws Exception {
        return daoMCQ.listarTodos();
    }

    @Override
    public MultipleChoiceQuestion buscarPorId(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("El code de la pregunta debe ser mayor que cero.");
        }
        return daoMCQ.buscarPorId(id);
    }

    private void validar(MultipleChoiceQuestion question, boolean esModificacion) throws Exception {
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
        if (question.getCorrectOptions() == null || question.getCorrectOptions().isEmpty()) {
            throw new Exception("Debe haber al menos una opcion correcta.");
        }
        for (Integer opt : question.getCorrectOptions()) {
            if (opt < 1 || opt > question.getAvailableOptions().size()) {
                throw new Exception("La opcion correcta " + opt + " esta fuera de rango.");
            }
        }
    }
}
