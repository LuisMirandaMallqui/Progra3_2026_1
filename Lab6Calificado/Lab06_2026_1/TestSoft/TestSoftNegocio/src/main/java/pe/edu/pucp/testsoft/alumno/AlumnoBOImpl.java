package pe.edu.pucp.testsoft.alumno;

import pe.edu.pucp.testsoft.alumno.dao.AlumnoDAO;
import pe.edu.pucp.testsoft.alumno.impl.AlumnoImpl;
import pe.edu.pucp.testsoft.model.alumno.Alumno;

import java.util.List;

public class AlumnoBOImpl implements  AlumnoBO{

    @Override
    public int insertar(Alumno objeto) throws Exception {
        return 0;
    }

    @Override
    public int modificar(Alumno objeto) throws Exception {
        return 0;
    }

    @Override
    public int eliminar(int id) throws Exception {
        return 0;
    }

    @Override
    public List<Alumno> leerTodos(){
        AlumnoDAO alumnoDAO = new AlumnoImpl();
        return alumnoDAO.listarTodos();
    }

    @Override
    public Alumno buscarPorId(int id) throws Exception {
        return null;
    }
}
