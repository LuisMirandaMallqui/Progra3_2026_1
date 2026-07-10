package pe.edu.pucp.universidad.business.implementsBO;

import java.util.List;
import pe.edu.pucp.universidad.business.NotaBO;
import pe.edu.pucp.universidad.dao.NotaDAO;
import pe.edu.pucp.universidad.impl.NotaDAOImpl;
import pe.edu.pucp.universidad.model.Nota;

public class NotaImplementsBO implements NotaBO {

    private NotaDAO notaDAO;

    public NotaImplementsBO() {
        notaDAO = new NotaDAOImpl();
    }

    @Override
    public int insertar(Nota elemento) {
        try {
            return notaDAO.insertar(elemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int modificar(Nota elemento) {
        try {
            return notaDAO.modificar(elemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int eliminar(int idElemento) {
        try {
            return notaDAO.eliminar(idElemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public Nota buscarPorId(int idElemento) {
        try {
            return notaDAO.obtenerPorId(idElemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Nota> listarTodos() {
        try {
            return notaDAO.listarTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            return List.of();
        }
    }

    @Override
    public double calcularPromedioFinal(int idMatricula, int idHorarioCurso) {
        try {
            return notaDAO.calcularPromedioFinal(idMatricula, idHorarioCurso);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0.0;
        }
    }

}
