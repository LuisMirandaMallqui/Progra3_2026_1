package pe.edu.pucp.testsoft.pregunta;

import pe.edu.pucp.testsoft.model.pregunta.Pregunta;
import pe.edu.pucp.testsoft.pregunta.dao.PreguntaDAO;
import pe.edu.pucp.testsoft.pregunta.impl.PreguntaImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PreguntaBOImpl implements PreguntaBO {
    private PreguntaDAO pregunta;

    @Override
    public int insertar(Pregunta objeto) throws Exception {
        return 0;
    }

    @Override
    public int modificar(Pregunta objeto) throws Exception {
        return 0;
    }

    @Override
    public int eliminar(int id) throws Exception {
        return 0;
    }

    @Override
    public List<Pregunta> leerTodos() {
        return List.of();
    }

    @Override
    public Pregunta buscarPorId(int id) throws Exception {
        return null;
    }

    public List<Pregunta> seleccionarPreguntasAleatorias(){
        pregunta = new PreguntaImpl();
        ArrayList<Pregunta>bancoPreguntas = (ArrayList<Pregunta>) pregunta.listarTodos();
        List<Pregunta> copia = new ArrayList<>(bancoPreguntas);
        Collections.shuffle(copia);
        return new ArrayList<>(copia.subList(0, 10));
    }
}
