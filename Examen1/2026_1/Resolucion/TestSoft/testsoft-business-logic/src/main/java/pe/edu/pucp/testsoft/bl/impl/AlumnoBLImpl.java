package pe.edu.pucp.testsoft.bl.impl;

import pe.edu.pucp.testsoft.bl.IAlumnoBL;
import pe.edu.pucp.testsoft.dao.IAlumnoDAO;
import pe.edu.pucp.testsoft.dao.impl.AlumnoDAOImpl;
import pe.edu.pucp.testsoft.model.Alumno;

public class AlumnoBLImpl implements IAlumnoBL {

    private IAlumnoDAO alumnoDAO = new AlumnoDAOImpl();

    public Alumno buscarAlumno(int id) {
        return alumnoDAO.buscarAlumno(id);
    }
}
