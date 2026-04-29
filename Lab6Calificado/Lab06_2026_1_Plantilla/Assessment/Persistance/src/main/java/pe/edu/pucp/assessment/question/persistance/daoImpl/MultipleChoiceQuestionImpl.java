package pe.edu.pucp.assessment.question.persistance.daoImpl;

import pe.edu.pucp.assessment.dao.persistance.DaoImplBase;
import pe.edu.pucp.assessment.question.model.MultipleChoiceQuestion;
import pe.edu.pucp.assessment.question.persistance.dao.MultipleChoiceQuestionDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultipleChoiceQuestionImpl extends DaoImplBase implements MultipleChoiceQuestionDao {

    @Override
    protected String obtenerSPEliminar() {
        return "ELIMINAR_QUESTION";
    }

    @Override
    public int insertar(MultipleChoiceQuestion question) {
        int resultado = 0;
        try {
            dbManager.iniciarTransaccion();
            // 1. Insertar en tabla padre question
            Map<Integer, Object> outQ = new HashMap<>();
            Map<Integer, Object> inQ = new HashMap<>();
            outQ.put(1, Types.INTEGER);
            inQ.put(2, question.getPrompt());
            inQ.put(3, "MultipleChoice");
            dbManager.ejecutarProcedimientoTransaccion("INSERTAR_QUESTION", inQ, outQ);
            question.setCode((int) outQ.get(1));
            // 2. Insertar en tabla hija multiple_choice_question
            Map<Integer, Object> inMC = new HashMap<>();
            inMC.put(1, question.getCode());
            dbManager.ejecutarProcedimientoTransaccion("INSERTAR_MULTIPLE_CHOICE", inMC, null);
            // 3. Insertar opciones
            for (int i = 0; i < question.getAvailableOptions().size(); i++) {
                Map<Integer, Object> inOpt = new HashMap<>();
                inOpt.put(1, question.getCode());
                inOpt.put(2, i + 1);
                inOpt.put(3, question.getAvailableOptions().get(i));
                dbManager.ejecutarProcedimientoTransaccion("INSERTAR_QUESTION_OPTION", inOpt, null);
            }
            // 4. Insertar opciones correctas
            for (Integer correctOpt : question.getCorrectOptions()) {
                Map<Integer, Object> inCorr = new HashMap<>();
                inCorr.put(1, question.getCode());
                inCorr.put(2, correctOpt);
                dbManager.ejecutarProcedimientoTransaccion("INSERTAR_CORRECT_OPTION", inCorr, null);
            }
            dbManager.confirmarTransaccion();
            resultado = question.getCode();
        } catch (SQLException ex) {
            System.out.println("Error al insertar multiple choice question: " + ex.getMessage());
            dbManager.cancelarTransaccion();
        }
        return resultado;
    }

    @Override
    public int modificar(MultipleChoiceQuestion question) {
        int resultado = 0;
        try {
            dbManager.iniciarTransaccion();
            Map<Integer, Object> inQ = new HashMap<>();
            inQ.put(1, question.getCode());
            inQ.put(2, question.getPrompt());
            dbManager.ejecutarProcedimientoTransaccion("MODIFICAR_QUESTION", inQ, null);
            dbManager.confirmarTransaccion();
            resultado = 1;
        } catch (SQLException ex) {
            System.out.println("Error al modificar multiple choice question: " + ex.getMessage());
            dbManager.cancelarTransaccion();
        }
        return resultado;
    }

    @Override
    public MultipleChoiceQuestion buscarPorId(int code) {
        MultipleChoiceQuestion q = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, code);
        ResultSet rs = dbManager.ejecutarProcedimientoLectura("BUSCAR_MULTIPLE_CHOICE_POR_ID", parametrosEntrada);
        try {
            if (rs != null && rs.next()) {
                q = new MultipleChoiceQuestion();
                q.setCode(rs.getInt("code"));
                q.setPrompt(rs.getString("prompt"));
                q.setAvailableOptions(cargarOpciones(code));
                q.setCorrectOptions(cargarCorrectOptions(code));
            }
        } catch (Exception ex) {
            System.out.println("Error al buscar multiple choice question: " + ex.getMessage());
        } finally {
            dbManager.cerrarConexion();
        }
        return q;
    }

    @Override
    public List<MultipleChoiceQuestion> listarTodos() {
        List<MultipleChoiceQuestion> lista = null;
        ResultSet rs = dbManager.ejecutarProcedimientoLectura("LISTAR_MULTIPLE_CHOICE_TODOS", null);
        try {
            while (rs != null && rs.next()) {
                if (lista == null) lista = new ArrayList<>();
                MultipleChoiceQuestion q = new MultipleChoiceQuestion();
                q.setCode(rs.getInt("code"));
                q.setPrompt(rs.getString("prompt"));
                lista.add(q);
            }
        } catch (Exception ex) {
            System.out.println("Error al listar multiple choice questions: " + ex.getMessage());
        } finally {
            dbManager.cerrarConexion();
        }
        return lista;
    }

    private List<String> cargarOpciones(int questionCode) {
        List<String> opciones = new ArrayList<>();
        Map<Integer, Object> in = new HashMap<>();
        in.put(1, questionCode);
        ResultSet rs = dbManager.ejecutarProcedimientoLectura("LISTAR_OPTIONS_POR_QUESTION", in);
        try {
            while (rs != null && rs.next()) {
                opciones.add(rs.getString("option_text"));
            }
        } catch (Exception ex) {
            System.out.println("Error al cargar opciones: " + ex.getMessage());
        }
        return opciones;
    }

    private List<Integer> cargarCorrectOptions(int questionCode) {
        List<Integer> correctas = new ArrayList<>();
        Map<Integer, Object> in = new HashMap<>();
        in.put(1, questionCode);
        ResultSet rs = dbManager.ejecutarProcedimientoLectura("LISTAR_CORRECT_OPTIONS_POR_QUESTION", in);
        try {
            while (rs != null && rs.next()) {
                correctas.add(rs.getInt("option_number"));
            }
        } catch (Exception ex) {
            System.out.println("Error al cargar opciones correctas: " + ex.getMessage());
        }
        return correctas;
    }
}
