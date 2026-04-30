package pe.edu.pucp.testsoft.pregunta.impl;

import pe.edu.pucp.testsoft.DaoImplBase;
import pe.edu.pucp.testsoft.model.pregunta.Pregunta;
import pe.edu.pucp.testsoft.pregunta.dao.PreguntaDAO;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PreguntaImpl extends DaoImplBase implements PreguntaDAO {

    @Override
    protected String obtenerSPEliminar() {
        return "ELIMINAR_QUESTION";
    }

    public int insertar(Pregunta pregunta) {
        // Se usa desde los hijos (SingleChoice/MultipleChoice) via transaccion
//        Map<Integer, Object> parametrosSalida = new HashMap<>();
//        Map<Integer, Object> parametrosEntrada = new HashMap<>();
//        parametrosSalida.put(1, Types.INTEGER);
//        parametrosEntrada.put(2, pregunta.getPrompt());
//        parametrosEntrada.put(3, pregunta.getClass().getSimpleName());
//        dbManager.ejecutarProcedimiento("INSERTAR_QUESTION", parametrosEntrada, parametrosSalida);
//        pregunta.setCode((int) parametrosSalida.get(1));
//        return pregunta.getCode();

        return 0;
    }

    @Override
    public int modificar(Pregunta question) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, question.getId());
        parametrosEntrada.put(2, question.getEnunciado());
        //no se usa pero para futuro
        return dbManager.ejecutarProcedimiento("modificar_preguntas", parametrosEntrada, null);
    }

    @Override
    public Pregunta buscarPorId(int id) {
        // Retorna null — usar SingleChoiceQuestionImpl o MultipleChoiceQuestionImpl
        return null;
    }

    @Override
    public List<Pregunta> listarTodos() {
        List<Pregunta> lista = null;
        ResultSet rs = dbManager.ejecutarProcedimientoLectura(
                "listar_preguntas", null);
        try {
            while (rs != null && rs.next()) {
                if (lista == null) lista = new ArrayList<>();//Lo pongo acá para no hacer un new en caso el resultSet llegue mal
                Pregunta a = new Pregunta();
                a.setId(rs.getInt("id"));
                a.setEnunciado(rs.getString("enunciado"));
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
