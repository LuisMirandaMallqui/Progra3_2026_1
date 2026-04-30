package pe.edu.pucp.testsoft.main;

import pe.edu.pucp.testsoft.model.examen.Examen;
import pe.edu.pucp.testsoft.model.pregunta.Pregunta;
import pe.edu.pucp.testsoft.examen.ExamenBO;
import pe.edu.pucp.testsoft.examen.ExamenBOImpl;

import java.util.List;

public class MostrarExamenes {
    public static void main(String[] args) {
        ExamenBO examenBO = new ExamenBOImpl();
        List<Examen> examenes = examenBO.leerTodos();
        for (Examen examen : examenes) {
            System.out.println("--------------------------------------------------");
            System.out.println("Examen ID: " + examen.getId());
            if (examen.getFechaCreacion() != null) {
                System.out.println("Fecha de creación: " + examen.getFechaCreacion());
            }
            System.out.println("Alumno: " + examen.getAlumno().getCodigo() + " - " + examen.getAlumno().getNombre());
            System.out.println("Título: " + examen.getTitulo());
            System.out.println("Preguntas asignadas: " + examen.getPreguntas().size());
            for (int i = 0; i < examen.getPreguntas().size(); i++) {
                Pregunta pregunta = examen.getPreguntas().get(i);
                System.out.println((i + 1) + ". " + pregunta.getEnunciado());
            }
        }
    }
}
