package pe.edu.pucp.universidad.business.implementsBO;

import java.util.List;
import pe.edu.pucp.universidad.business.EstudianteBO;
import pe.edu.pucp.universidad.dao.EstudianteDAO;
import pe.edu.pucp.universidad.impl.EstudianteDAOImpl;
import pe.edu.pucp.universidad.model.Estudiante;

public class EstudianteImplementsBO implements EstudianteBO {

    private EstudianteDAO estudianteDAO;

    public EstudianteImplementsBO() {
        estudianteDAO = new EstudianteDAOImpl();
    }

    @Override
    public int insertar(Estudiante elemento) {
        try {
            return estudianteDAO.insertar(elemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int modificar(Estudiante elemento) {
        try {
            return estudianteDAO.modificar(elemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int eliminar(int idElemento) {
        try {
            return estudianteDAO.eliminar(idElemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public Estudiante buscarPorId(int idElemento) {
        try {
            return estudianteDAO.obtenerPorId(idElemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Estudiante> listarTodos() {
        try {
            return estudianteDAO.listarTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            return List.of();
        }
    }

}
