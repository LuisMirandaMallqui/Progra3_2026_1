package pe.edu.pucp.universidad.business.implementsBO;

import java.util.List;
import pe.edu.pucp.universidad.business.DocenteBO;
import pe.edu.pucp.universidad.dao.DocenteDAO;
import pe.edu.pucp.universidad.impl.DocenteDAOImpl;
import pe.edu.pucp.universidad.model.Docente;

public class DocenteImplementsBO implements DocenteBO {

    private DocenteDAO docenteDAO;

    public DocenteImplementsBO() {
        docenteDAO = new DocenteDAOImpl();
    }

    @Override
    public int insertar(Docente elemento) {
        try {
            return docenteDAO.insertar(elemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int modificar(Docente elemento) {
        try {
            return docenteDAO.modificar(elemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int eliminar(int idElemento) {
        try {
            return docenteDAO.eliminar(idElemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public Docente buscarPorId(int idElemento) {
        try {
            return docenteDAO.obtenerPorId(idElemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Docente> listarTodos() {
        try {
            return docenteDAO.listarTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            return List.of();
        }
    }

}
