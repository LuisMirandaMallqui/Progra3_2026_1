package pe.edu.pucp.assessment.question.persistance.daoImpl;

import pe.edu.pucp.assessment.dao.persistance.DaoImplBase;
import pe.edu.pucp.assessment.question.model.SingleChoiceQuestion;
import pe.edu.pucp.assessment.question.persistance.dao.SingleChoiceQuestionDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingleChoiceQuestionImpl extends DaoImplBase implements SingleChoiceQuestionDao {

    @Override
    protected String obtenerSPEliminar() {
        return "ELIMINAR_QUESTION";
    }

    @Override
    public int insertar(SingleChoiceQuestion question) {
        int resultado = 0;
        try {
            dbManager.iniciarTransaccion();
            // 1. Insertar en tabla padre question
            Map<Integer, Object> outQ = new HashMap<>();
            Map<Integer, Object> inQ = new HashMap<>();
            outQ.put(1, Types.INTEGER);
            inQ.put(2, question.getPrompt());
            inQ.put(3, "SingleChoice");
            dbManager.ejecutarProcedimientoTransaccion("INSERTAR_QUESTION", inQ, outQ);
            question.setCode((int) outQ.get(1));
            // 2. Insertar en tabla hija single_choice_question
            Map<Integer, Object> inSC = new HashMap<>();
            inSC.put(1, question.getCode());
            inSC.put(2, question.getCorrectOption());
            dbManager.ejecutarProcedimientoTransaccion("INSERTAR_SINGLE_CHOICE", inSC, null);
            // 3. Insertar opciones
            for (int i = 0; i < question.getAvailableOptions().size(); i++) {
                Map<Integer, Object> inOpt = new HashMap<>();
                inOpt.put(1, question.getCode());
                inOpt.put(2, i + 1);
                inOpt.put(3, question.getAvailableOptions().get(i));
                dbManager.ejecutarProcedimientoTransaccion("INSERTAR_QUESTION_OPTION", inOpt, null);
            }
            dbManager.confirmarTransaccion();
            resultado = question.getCode();
        } catch (SQLException ex) {
            System.out.println("Error al insertar single choice question: " + ex.getMessage());
            dbManager.cancelarTransaccion();
        }
        return resultado;
    }

    @Override
    public int modificar(SingleChoiceQuestion question) {
        int resultado = 0;
        try {
            dbManager.iniciarTransaccion();
            Map<Integer, Object> inQ = new HashMap<>();
            inQ.put(1, question.getCode());
            inQ.put(2, question.getPrompt());
            dbManager.ejecutarProcedimientoTransaccion("MODIFICAR_QUESTION", inQ, null);
            Map<Integer, Object> inSC = new HashMap<>();
            inSC.put(1, question.getCode());
            inSC.put(2, question.getCorrectOption());
            dbManager.ejecutarProcedimientoTransaccion("MODIFICAR_SINGLE_CHOICE", inSC, null);
            dbManager.confirmarTransaccion();
            resultado = 1;
        } catch (SQLException ex) {
            System.out.println("Error al modificar single choice question: " + ex.getMessage());
            dbManager.cancelarTransaccion();
        }
        return resultado;
    }

    @Override
    public SingleChoiceQuestion buscarPorId(int code) {
        SingleChoiceQuestion q = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, code);
        ResultSet rs = dbManager.ejecutarProcedimientoLectura("BUSCAR_SINGLE_CHOICE_POR_ID", parametrosEntrada);
        try {
            if (rs != null && rs.next()) {
                q = new SingleChoiceQuestion();
                q.setCode(rs.getInt("code"));
                q.setPrompt(rs.getString("prompt"));
                q.setCorrectOption(rs.getInt("correct_option"));
                // Cargar opciones
                q.setAvailableOptions(cargarOpciones(code));
            }
        } catch (Exception ex) {
            System.out.println("Error al buscar single choice question: " + ex.getMessage());
        } finally {
            dbManager.cerrarConexion();
        }
        return q;
    }

    @Override
    public List<SingleChoiceQuestion> listarTodos() {
        List<SingleChoiceQuestion> lista = null;
        ResultSet rs = dbManager.ejecutarProcedimientoLectura("LISTAR_SINGLE_CHOICE_TODOS", null);
        try {
            while (rs != null && rs.next()) {
                if (lista == null) lista = new ArrayList<>();
                SingleChoiceQuestion q = new SingleChoiceQuestion();
                q.setCode(rs.getInt("code"));
                q.setPrompt(rs.getString("prompt"));
                q.setCorrectOption(rs.getInt("correct_option"));
                lista.add(q);
            }
        } catch (Exception ex) {
            System.out.println("Error al listar single choice questions: " + ex.getMessage());
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
}
