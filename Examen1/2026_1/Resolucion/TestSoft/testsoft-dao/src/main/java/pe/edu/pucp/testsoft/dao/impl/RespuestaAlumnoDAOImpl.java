package pe.edu.pucp.testsoft.dao.impl;

import pe.edu.pucp.testsoft.dao.IRespuestaAlumno;
import pe.edu.pucp.testsoft.dao.manager.DBManager;
import pe.edu.pucp.testsoft.dao.manager.DBManagerPAZ;
import pe.edu.pucp.testsoft.model.RespuestaAlumno;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RespuestaAlumnoDAOImpl implements IRespuestaAlumno {
    private final DBManagerPAZ dbManager = DBManagerPAZ.getInstance();

    public void insertar(RespuestaAlumno respuestaAlumno) {
        try {
            // Insertar cabecera del examen
            Map<Integer, Object> entrada = new HashMap<>();
            Map<Integer, Object> salida = new HashMap<>();
            entrada.put(1, respuestaAlumno.getPregunta().getId()); // idExamenPregunta
            entrada.put(2, respuestaAlumno.getRespuesta().getId()); // idOpcionRespuesta
            entrada.put(3, respuestaAlumno.getRespuesta().isEsCorrecta()); //ENTONCES antes de llamar aca ya debe estar el calculo
            entrada.put(4, respuestaAlumno.getPuntajeObtenido());

            dbManager.ejecutarProcedimientoTransaccion(
                    "SP_INSERTAR_RESPUESTA_ALUMNO", entrada, salida);
        } catch (Exception ex) {
            System.out.println("Error al insertar respuesta del alumno: " + ex.getMessage());
        }
    }

}
