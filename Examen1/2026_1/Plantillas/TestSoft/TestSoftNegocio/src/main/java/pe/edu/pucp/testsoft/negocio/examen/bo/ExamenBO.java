package pe.edu.pucp.testsoft.negocio.examen.bo;

import pe.edu.pucp.testsoft.model.alumno.Alumno;
import pe.edu.pucp.testsoft.model.examen.Examen;
import pe.edu.pucp.testsoft.model.pregunta.Pregunta;
import pe.edu.pucp.testsoft.negocio.bo.IBaseBO;

import java.util.List;

public interface ExamenBO extends IBaseBO<Examen> {
    void crearExamenConPreguntas(Alumno alumno, String titulo, List<Pregunta> preguntas);
}
