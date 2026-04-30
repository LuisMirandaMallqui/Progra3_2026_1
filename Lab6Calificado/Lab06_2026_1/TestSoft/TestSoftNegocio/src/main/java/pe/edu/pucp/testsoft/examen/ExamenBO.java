package pe.edu.pucp.testsoft.examen;

import pe.edu.pucp.testsoft.IBaseBO;
import pe.edu.pucp.testsoft.model.alumno.Alumno;
import pe.edu.pucp.testsoft.model.examen.Examen;
import pe.edu.pucp.testsoft.model.pregunta.Pregunta;

import java.util.List;

public interface ExamenBO extends IBaseBO<Examen> {
    public void crearExamenConPreguntas(Alumno a, String titulo, List<Pregunta> p);
}
