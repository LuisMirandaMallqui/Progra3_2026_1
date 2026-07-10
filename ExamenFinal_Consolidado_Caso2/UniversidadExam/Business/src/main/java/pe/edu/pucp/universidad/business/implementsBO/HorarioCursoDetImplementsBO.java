package pe.edu.pucp.universidad.business.implementsBO;

import java.util.List;
import pe.edu.pucp.universidad.business.HorarioCursoDetBO;
import pe.edu.pucp.universidad.dao.HorarioCursoDetDAO;
import pe.edu.pucp.universidad.impl.HorarioCursoDetDAOImpl;
import pe.edu.pucp.universidad.model.HorarioCursoDet;

public class HorarioCursoDetImplementsBO implements HorarioCursoDetBO {

    private HorarioCursoDetDAO horarioCursoDetDAO;

    public HorarioCursoDetImplementsBO() {
        horarioCursoDetDAO = new HorarioCursoDetDAOImpl();
    }

    @Override
    public int insertar(HorarioCursoDet elemento) {
        try {
            return horarioCursoDetDAO.insertar(elemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int modificar(HorarioCursoDet elemento) {
        try {
            return horarioCursoDetDAO.modificar(elemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int eliminar(int idElemento) {
        try {
            return horarioCursoDetDAO.eliminar(idElemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public HorarioCursoDet buscarPorId(int idElemento) {
        try {
            return horarioCursoDetDAO.obtenerPorId(idElemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<HorarioCursoDet> listarTodos() {
        try {
            return horarioCursoDetDAO.listarTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            return List.of();
        }
    }

}
