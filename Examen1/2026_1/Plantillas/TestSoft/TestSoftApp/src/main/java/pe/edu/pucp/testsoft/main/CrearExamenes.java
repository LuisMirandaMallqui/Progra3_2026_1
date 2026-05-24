package pe.edu.pucp.testsoft.main;

import pe.edu.pucp.testsoft.model.alumno.Alumno;
import pe.edu.pucp.testsoft.model.pregunta.Pregunta;
import pe.edu.pucp.testsoft.negocio.alumno.bo.AlumnoBO;
import pe.edu.pucp.testsoft.negocio.alumno.boimpl.AlumnoBOImpl;
import pe.edu.pucp.testsoft.negocio.examen.bo.ExamenBO;
import pe.edu.pucp.testsoft.negocio.examen.boimpl.ExamenBOImpl;
import pe.edu.pucp.testsoft.negocio.pregunta.bo.PreguntaBO;
import pe.edu.pucp.testsoft.negocio.pregunta.boimpl.PreguntaBOImpl;

import java.util.List;

public class CrearExamenes {
    public static void main(String[] args) {
        AlumnoBO alumnoBO = new AlumnoBOImpl();
        ExamenBO examenBO = new ExamenBOImpl();
        PreguntaBO preguntaBO = new PreguntaBOImpl();
        List<Alumno> alumnos = alumnoBO.leerTodos();
        for (Alumno alumno : alumnos) {
            List<Pregunta> preguntasSeleccionadas = preguntaBO.seleccionarPreguntasAleatorias();
            examenBO.crearExamenConPreguntas(
                    alumno,
                    "Examen configurado para " + alumno.getCodigo(),
                    preguntasSeleccionadas);
        }
    }
}
