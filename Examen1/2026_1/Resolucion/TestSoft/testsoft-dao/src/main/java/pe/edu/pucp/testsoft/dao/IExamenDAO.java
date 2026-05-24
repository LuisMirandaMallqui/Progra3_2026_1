package pe.edu.pucp.testsoft.dao;

import pe.edu.pucp.testsoft.dao.manager.TransactionContext;
import pe.edu.pucp.testsoft.model.Alumno;
import pe.edu.pucp.testsoft.model.Examen;

import java.sql.Connection;
import java.util.ArrayList;

public interface IExamenDAO {
    ArrayList<Examen> listarPendientesPorAlumno(Alumno alumno);
    Examen obtenerExamen(int id, Alumno alumno);

    int modificar(Examen examenRendido);
}
