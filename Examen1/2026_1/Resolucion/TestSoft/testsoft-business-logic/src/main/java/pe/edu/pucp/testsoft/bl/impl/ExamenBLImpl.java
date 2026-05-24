package pe.edu.pucp.testsoft.bl.impl;

import pe.edu.pucp.testsoft.bl.IExamenBL;
import pe.edu.pucp.testsoft.dao.IExamenDAO;
import pe.edu.pucp.testsoft.dao.IExamenPreguntaDAO;
import pe.edu.pucp.testsoft.dao.IOpcionRespuestaDAO;
import pe.edu.pucp.testsoft.dao.IRespuestaAlumno;
import pe.edu.pucp.testsoft.dao.impl.ExamenDAOImpl;
import pe.edu.pucp.testsoft.dao.impl.ExamenPreguntaDAOImpl;
import pe.edu.pucp.testsoft.dao.impl.OpcionRespuestaDAOImpl;
import pe.edu.pucp.testsoft.dao.impl.RespuestaAlumnoDAOImpl;
import pe.edu.pucp.testsoft.dao.manager.DBManagerPAZ;
import pe.edu.pucp.testsoft.model.*;

import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamenBLImpl implements IExamenBL {
    private IExamenDAO examenDAO = new ExamenDAOImpl();
    private IExamenPreguntaDAO examenPreguntaDAO = new ExamenPreguntaDAOImpl();
    private IOpcionRespuestaDAO opcionRespuestaDAO = new OpcionRespuestaDAOImpl();

    public ArrayList<Examen> listarExamenesPendientes(Alumno alumno) {
        return examenDAO.listarPendientesPorAlumno(alumno);
    }

    public Examen obtenerExamenCompleto(int idExamen, Alumno alumno){
        Examen examen = examenDAO.obtenerExamen(idExamen,alumno);
        //Aca se llena el atr preguntas
        ArrayList<ExamenPregunta> preguntasExamen = examenPreguntaDAO.obtenerPreguntas(idExamen);
        for (ExamenPregunta preguntaExamen : preguntasExamen){
            ArrayList<OpcionRespuesta> opciones = opcionRespuestaDAO.obtenerOpciones(preguntaExamen.getPregunta().getId());
            preguntaExamen.getPregunta().setOpciones(opciones);
        }
        examen.setPreguntas(preguntasExamen);
        return examen;
    }

    public RespuestaAlumno construirRespuesta (Examen examenRendir,ExamenPregunta preguntaExamen,OpcionRespuesta opcionCorrecta,int ordenCorrecto,int ordenElegido){
        RespuestaAlumno respuestaAlumno = new RespuestaAlumno();
        respuestaAlumno.setPregunta(preguntaExamen);
        respuestaAlumno.setRespuesta(opcionCorrecta);
        int nota = examenRendir.getNota();
        if(ordenCorrecto == ordenElegido){
            respuestaAlumno.setPuntajeObtenido(preguntaExamen.getPuntaje());
            respuestaAlumno.setEsCorrecta(true);
            nota+= preguntaExamen.getPuntaje();
        } else {
            respuestaAlumno.setPuntajeObtenido(0);
            respuestaAlumno.setEsCorrecta(false);
        }
        examenRendir.setNota(nota);
        return respuestaAlumno;
    }

    public void cargarExamen(List<RespuestaAlumno> respuestaAlumnos, Examen examenRendido) throws SQLException {
        try{
            DBManagerPAZ.getInstance().iniciarTransaccion();
            //registro de respuesta
            IRespuestaAlumno respuestaAlumnoDAO = new RespuestaAlumnoDAOImpl();
            for (RespuestaAlumno respuestaAlumno : respuestaAlumnos) {
                    respuestaAlumnoDAO.insertar(respuestaAlumno);
            }
            Date fecha = java.sql.Date.from(Instant.now());
            examenRendido.setEstado(EstadoExamen.RESUELTO);
            int modificado = examenDAO.modificar(examenRendido);
            if(modificado == 1) System.out.println("Resultados cargados");
        }
        catch (SQLException ex){
            DBManagerPAZ.getInstance().cancelarTransaccion();
        } finally {
            try {
                DBManagerPAZ.getInstance().confirmarTransaccion();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
