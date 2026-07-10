package pe.edu.pucp.universidad.business.implementsBO;

import java.util.List;
import pe.edu.pucp.universidad.business.EspecialidadBO;
import pe.edu.pucp.universidad.dao.EspecialidadDAO;
import pe.edu.pucp.universidad.impl.EspecialidadDAOImpl;
import pe.edu.pucp.universidad.model.Especialidad;

public class EspecialidadImplementsBO implements EspecialidadBO {

    private EspecialidadDAO especialidadDAO;

    public EspecialidadImplementsBO() {
        especialidadDAO = new EspecialidadDAOImpl();
    }

    @Override
    public int insertar(Especialidad elemento) {
        try {
            return especialidadDAO.insertar(elemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int modificar(Especialidad elemento) {
        try {
            return especialidadDAO.modificar(elemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int eliminar(int idElemento) {
        try {
            return especialidadDAO.eliminar(idElemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public Especialidad buscarPorId(int idElemento) {
        try {
            return especialidadDAO.obtenerPorId(idElemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Especialidad> listarTodos() {
        try {
            return especialidadDAO.listarTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            return List.of();
        }
    }

}
