package pe.edu.pucp.testsoft.pregunta;

import pe.edu.pucp.testsoft.IBaseBO;
import pe.edu.pucp.testsoft.model.pregunta.Pregunta;

import java.util.List;

public interface PreguntaBO  extends IBaseBO<Pregunta> {
    public List<Pregunta> seleccionarPreguntasAleatorias();
}
