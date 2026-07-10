package pe.edu.pucp.universidad.business.implementsBO;

import java.util.List;
import pe.edu.pucp.universidad.business.FacultadBO;
import pe.edu.pucp.universidad.dao.FacultadDAO;
import pe.edu.pucp.universidad.impl.FacultadDAOImpl;
import pe.edu.pucp.universidad.model.Facultad;

public class FacultadImplementsBO implements FacultadBO {

    private FacultadDAO facultadDAO;

    public FacultadImplementsBO() {
        facultadDAO = new FacultadDAOImpl();
    }

    @Override
    public int insertar(Facultad elemento) {
        try {
            return facultadDAO.insertar(elemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int modificar(Facultad elemento) {
        try {
            return facultadDAO.modificar(elemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int eliminar(int idElemento) {
        try {
            return facultadDAO.eliminar(idElemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public Facultad buscarPorId(int idElemento) {
        try {
            return facultadDAO.obtenerPorId(idElemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Facultad> listarTodos() {
        try {
            return facultadDAO.listarTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            return List.of();
        }
    }

}
