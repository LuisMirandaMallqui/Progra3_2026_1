package pe.edu.pucp.testsoft.negocio.pregunta.bo;

import pe.edu.pucp.testsoft.model.pregunta.Pregunta;
import pe.edu.pucp.testsoft.negocio.bo.IBaseBO;

import java.util.List;

public interface PreguntaBO extends IBaseBO<Pregunta> {
    List<Pregunta> seleccionarPreguntasAleatorias();
}
