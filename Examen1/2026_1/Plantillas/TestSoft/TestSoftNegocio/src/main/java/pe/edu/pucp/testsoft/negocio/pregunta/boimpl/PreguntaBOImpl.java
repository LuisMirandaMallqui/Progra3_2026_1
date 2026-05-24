package pe.edu.pucp.testsoft.negocio.pregunta.boimpl;

import pe.edu.pucp.testsoft.model.pregunta.Pregunta;
import pe.edu.pucp.testsoft.negocio.pregunta.bo.PreguntaBO;
import pe.edu.pucp.testsoft.persistance.pregunta.dao.PreguntaDAO;
import pe.edu.pucp.testsoft.persistance.pregunta.impl.PreguntaImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PreguntaBOImpl implements PreguntaBO {

    private PreguntaDAO preguntaDAO = new PreguntaImpl();

    @Override
    public int insertar(Pregunta objeto) throws Exception {
        return preguntaDAO.insertar(objeto);
    }

    @Override
    public int modificar(Pregunta objeto) throws Exception {
        return preguntaDAO.modificar(objeto);
    }

    @Override
    public int eliminar(int id) throws Exception {
        return preguntaDAO.eliminar(id);
    }

    @Override
    public Pregunta buscarPorId(int id) throws Exception {
        return preguntaDAO.buscarPorId(id);
    }

    @Override
    public List<Pregunta> leerTodos() {
        return preguntaDAO.listarTodos();
    }

    @Override
    public List<Pregunta> seleccionarPreguntasAleatorias() {
        List<Pregunta> todas = preguntaDAO.listarTodos();
        if (todas == null || todas.isEmpty()) return new ArrayList<>();

        // Mezclar y tomar las primeras 10 (o menos si hay menos)
        Collections.shuffle(todas);
        int cantidad = Math.min(10, todas.size());
        return new ArrayList<>(todas.subList(0, cantidad));
    }
}
