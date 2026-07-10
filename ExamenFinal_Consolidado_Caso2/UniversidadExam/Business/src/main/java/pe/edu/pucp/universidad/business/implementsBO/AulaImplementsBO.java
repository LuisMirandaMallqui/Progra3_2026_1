package pe.edu.pucp.universidad.business.implementsBO;

import java.util.List;
import pe.edu.pucp.universidad.business.AulaBO;
import pe.edu.pucp.universidad.dao.AulaDAO;
import pe.edu.pucp.universidad.impl.AulaDAOImpl;
import pe.edu.pucp.universidad.model.Aula;

public class AulaImplementsBO implements AulaBO {

    private AulaDAO aulaDAO;

    public AulaImplementsBO() {
        aulaDAO = new AulaDAOImpl();
    }

    @Override
    public int insertar(Aula elemento) {
        try {
            return aulaDAO.insertar(elemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int modificar(Aula elemento) {
        try {
            return aulaDAO.modificar(elemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int eliminar(int idElemento) {
        try {
            return aulaDAO.eliminar(idElemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public Aula buscarPorId(int idElemento) {
        try {
            return aulaDAO.obtenerPorId(idElemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Aula> listarTodos() {
        try {
            return aulaDAO.listarTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            return List.of();
        }
    }

}
