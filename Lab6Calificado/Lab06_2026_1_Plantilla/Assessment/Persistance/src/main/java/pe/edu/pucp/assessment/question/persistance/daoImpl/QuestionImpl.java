package pe.edu.pucp.assessment.question.persistance.daoImpl;

import pe.edu.pucp.assessment.dao.persistance.DaoImplBase;
import pe.edu.pucp.assessment.question.model.Question;
import pe.edu.pucp.assessment.question.persistance.dao.QuestionDao;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionImpl extends DaoImplBase implements QuestionDao {

    @Override
    protected String obtenerSPEliminar() {
        return "ELIMINAR_QUESTION";
    }

    @Override
    public int insertar(Question question) {
        // Se usa desde los hijos (SingleChoice/MultipleChoice) via transaccion
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, question.getPrompt());
        parametrosEntrada.put(3, question.getClass().getSimpleName());
        dbManager.ejecutarProcedimiento("INSERTAR_QUESTION", parametrosEntrada, parametrosSalida);
        question.setCode((int) parametrosSalida.get(1));
        return question.getCode();
    }

    @Override
    public int modificar(Question question) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, question.getCode());
        parametrosEntrada.put(2, question.getPrompt());
        return dbManager.ejecutarProcedimiento("MODIFICAR_QUESTION", parametrosEntrada, null);
    }

    @Override
    public Question buscarPorId(int id) {
        // Retorna null — usar SingleChoiceQuestionImpl o MultipleChoiceQuestionImpl
        return null;
    }

    @Override
    public List<Question> listarTodos() {
        // Retorna null — usar los Impl concretos
        return null;
    }
}
