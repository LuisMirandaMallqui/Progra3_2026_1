package pe.edu.pucp.testsoft.negocio.alumno.boimpl;

import pe.edu.pucp.testsoft.model.alumno.Alumno;
import pe.edu.pucp.testsoft.negocio.alumno.bo.AlumnoBO;
import pe.edu.pucp.testsoft.persistance.alumno.dao.AlumnoDAO;
import pe.edu.pucp.testsoft.persistance.alumno.impl.AlumnoImpl;

import java.util.List;

public class AlumnoBOImpl implements AlumnoBO {

    private AlumnoDAO alumnoDAO = new AlumnoImpl();

    @Override
    public int insertar(Alumno objeto) throws Exception {
        return alumnoDAO.insertar(objeto);
    }

    @Override
    public int modificar(Alumno objeto) throws Exception {
        return alumnoDAO.modificar(objeto);
    }

    @Override
    public int eliminar(int id) throws Exception {
        return alumnoDAO.eliminar(id);
    }

    @Override
    public Alumno buscarPorId(int id) throws Exception {
        return alumnoDAO.buscarPorId(id);
    }

    @Override
    public List<Alumno> leerTodos() {
        return alumnoDAO.listarTodos();
    }
}
