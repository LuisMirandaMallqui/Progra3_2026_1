package pe.edu.pucp.testsoft.consola;
import pe.edu.pucp.testsoft.bl.IAlumnoBL;
import pe.edu.pucp.testsoft.bl.IExamenBL;
import pe.edu.pucp.testsoft.bl.impl.AlumnoBLImpl;
import pe.edu.pucp.testsoft.bl.impl.ExamenBLImpl;
import pe.edu.pucp.testsoft.dao.manager.DBManagerPAZ;
import pe.edu.pucp.testsoft.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        IAlumnoBL alumnoBL = new AlumnoBLImpl();
        IExamenBL examenBL = new ExamenBLImpl();

        System.out.println("===== SISTEMA TESTSOFT =====");
        System.out.print("Ingrese ID del alumno: ");
        int idAlumno = sc.nextInt();
        Alumno alumno = alumnoBL.buscarAlumno(idAlumno); // OK
        ArrayList<Examen> examenes = examenBL.listarExamenesPendientes(alumno); // OK
        if (examenes.isEmpty()) {
            System.out.println("El alumno no tiene exámenes pendientes.");
            return;
        }
        // Objetos model trabajados hasta aca: list Examen, Alumno
        System.out.println("\nExámenes pendientes:");
        for (Examen examen : examenes) {
            System.out.println("ID Examen: " + examen.getId()
                    + " | Titulo: " + examen.getTitulo() + " | Estado: " + examen.getEstado());
        }

        System.out.print("\nSeleccione el ID del examen a resolver: ");
        int idExamen = sc.nextInt();
        Examen examenRendir = examenBL.obtenerExamenCompleto(idExamen, alumno); // OK
        //examenRendir es un obj model Examen, se le puso completo por el metodo BO que termina por unificar las preguntas con su opcion_respuesta
        if (examenRendir.getPreguntas().isEmpty()) {
            System.out.println("El examen seleccionado no tiene preguntas.");
            return;
        }

        System.out.println("\n===== RESOLUCIÓN DEL EXAMEN =====");
        //
        List<RespuestaAlumno> respuestas = new ArrayList<>();
        for (ExamenPregunta preguntaExamen : examenRendir.getPreguntas()) {
            System.out.println("\nPregunta " + preguntaExamen.getPregunta().getId() + ":");
            System.out.println(preguntaExamen.getPregunta().getEnunciado());
            System.out.println("Puntaje: " + preguntaExamen.getPuntaje());

            System.out.println("Opciones:");

            int ordenCorrecto = -1;
            OpcionRespuesta opcionCorrecta = new OpcionRespuesta();
            for (OpcionRespuesta opcion : preguntaExamen.getPregunta().getOpciones()) {
                System.out.println(opcion.getOrden() + ". " + opcion.getTextoOpcion());
                if(opcion.isEsCorrecta()) {
                    ordenCorrecto = opcion.getOrden();
                    opcionCorrecta = opcion;
                }
            }
            // Parte uno, registrar respuesta del alumno en Model Respuesta Alumno
            System.out.println("Registra tú respuesta");
            int ordenElegido = sc.nextInt(); // capturo la pregunta

            //Logica para definir atributos de respuesta
            // no hace ninguna transacción, solo separa la la logica de negocio (como se decide el criterio de rpta)
            // actualiza tambien la nota del examen por pregunta correcta
            RespuestaAlumno respuestaAlumno = examenBL.construirRespuesta(examenRendir, preguntaExamen,opcionCorrecta,ordenCorrecto,ordenElegido);
            respuestas.add(respuestaAlumno);
            // Parte 2 transaccion de respuesta alumno y actualizar examen
            examenBL.cargarExamen(respuestas,examenRendir);
        }
    }
}