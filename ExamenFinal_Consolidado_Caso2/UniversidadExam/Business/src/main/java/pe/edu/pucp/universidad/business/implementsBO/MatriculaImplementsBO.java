package pe.edu.pucp.universidad.business.implementsBO;

import java.util.List;
import pe.edu.pucp.universidad.business.MatriculaBO;
import pe.edu.pucp.universidad.dao.MatriculaDAO;
import pe.edu.pucp.universidad.impl.MatriculaDAOImpl;
import pe.edu.pucp.universidad.model.Matricula;

public class MatriculaImplementsBO implements MatriculaBO {

    private MatriculaDAO matriculaDAO;

    public MatriculaImplementsBO() {
        matriculaDAO = new MatriculaDAOImpl();
    }

    @Override
    public int insertar(Matricula elemento) {
        try {
            return matriculaDAO.insertar(elemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int modificar(Matricula elemento) {
        try {
            return matriculaDAO.modificar(elemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int eliminar(int idElemento) {
        try {
            return matriculaDAO.eliminar(idElemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public Matricula buscarPorId(int idElemento) {
        try {
            return matriculaDAO.obtenerPorId(idElemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Matricula> listarTodos() {
        try {
            return matriculaDAO.listarTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            return List.of();
        }
    }

}
