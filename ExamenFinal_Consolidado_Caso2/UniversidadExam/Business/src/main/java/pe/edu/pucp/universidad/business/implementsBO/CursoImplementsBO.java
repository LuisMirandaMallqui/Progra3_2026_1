package pe.edu.pucp.universidad.business.implementsBO;

import java.util.List;
import pe.edu.pucp.universidad.business.CursoBO;
import pe.edu.pucp.universidad.dao.CursoDAO;
import pe.edu.pucp.universidad.impl.CursoDAOImpl;
import pe.edu.pucp.universidad.model.Curso;

public class CursoImplementsBO implements CursoBO {

    private CursoDAO cursoDAO;

    public CursoImplementsBO() {
        cursoDAO = new CursoDAOImpl();
    }

    @Override
    public int insertar(Curso elemento) {
        try {
            return cursoDAO.insertar(elemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int modificar(Curso elemento) {
        try {
            return cursoDAO.modificar(elemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int eliminar(int idElemento) {
        try {
            return cursoDAO.eliminar(idElemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public Curso buscarPorId(int idElemento) {
        try {
            return cursoDAO.obtenerPorId(idElemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Curso> listarTodos() {
        try {
            return cursoDAO.listarTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            return List.of();
        }
    }

}
