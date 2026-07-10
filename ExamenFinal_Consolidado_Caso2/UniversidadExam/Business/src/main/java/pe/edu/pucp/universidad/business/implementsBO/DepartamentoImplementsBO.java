package pe.edu.pucp.universidad.business.implementsBO;

import java.util.List;
import pe.edu.pucp.universidad.business.DepartamentoBO;
import pe.edu.pucp.universidad.dao.DepartamentoDAO;
import pe.edu.pucp.universidad.impl.DepartamentoDAOImpl;
import pe.edu.pucp.universidad.model.Departamento;

public class DepartamentoImplementsBO implements DepartamentoBO {

    private DepartamentoDAO departamentoDAO;

    public DepartamentoImplementsBO() {
        departamentoDAO = new DepartamentoDAOImpl();
    }

    @Override
    public int insertar(Departamento elemento) {
        try {
            return departamentoDAO.insertar(elemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int modificar(Departamento elemento) {
        try {
            return departamentoDAO.modificar(elemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int eliminar(int idElemento) {
        try {
            return departamentoDAO.eliminar(idElemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public Departamento buscarPorId(int idElemento) {
        try {
            return departamentoDAO.obtenerPorId(idElemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Departamento> listarTodos() {
        try {
            return departamentoDAO.listarTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            return List.of();
        }
    }

}
