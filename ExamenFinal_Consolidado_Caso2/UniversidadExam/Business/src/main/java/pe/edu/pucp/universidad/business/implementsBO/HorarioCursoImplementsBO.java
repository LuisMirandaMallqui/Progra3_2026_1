package pe.edu.pucp.universidad.business.implementsBO;

import java.util.List;
import pe.edu.pucp.universidad.business.HorarioCursoBO;
import pe.edu.pucp.universidad.dao.HorarioCursoDAO;
import pe.edu.pucp.universidad.impl.HorarioCursoDAOImpl;
import pe.edu.pucp.universidad.model.HorarioCurso;

public class HorarioCursoImplementsBO implements HorarioCursoBO {

    private HorarioCursoDAO horarioCursoDAO;

    public HorarioCursoImplementsBO() {
        horarioCursoDAO = new HorarioCursoDAOImpl();
    }

    @Override
    public int insertar(HorarioCurso elemento) {
        try {
            return horarioCursoDAO.insertar(elemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int modificar(HorarioCurso elemento) {
        try {
            return horarioCursoDAO.modificar(elemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int eliminar(int idElemento) {
        try {
            return horarioCursoDAO.eliminar(idElemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public HorarioCurso buscarPorId(int idElemento) {
        try {
            return horarioCursoDAO.obtenerPorId(idElemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<HorarioCurso> listarTodos() {
        try {
            return horarioCursoDAO.listarTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            return List.of();
        }
    }

}
