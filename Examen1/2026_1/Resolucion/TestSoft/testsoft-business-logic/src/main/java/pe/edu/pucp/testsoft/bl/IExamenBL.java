package pe.edu.pucp.testsoft.bl;

import pe.edu.pucp.testsoft.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IExamenBL {
    ArrayList<Examen> listarExamenesPendientes(Alumno alumno);
    Examen obtenerExamenCompleto(int idExamen, Alumno alumno);
    public RespuestaAlumno construirRespuesta (Examen examenRendir, ExamenPregunta preguntaExamen, OpcionRespuesta opcionCorrecta, int ordenCorrecto, int ordenElegido);
    public void cargarExamen(List<RespuestaAlumno> respuestaAlumnos, Examen examenRendido) throws SQLException;
}
