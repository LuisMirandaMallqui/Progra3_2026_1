package pe.edu.pucp.universidad.business.implementsBO;

import java.util.List;
import pe.edu.pucp.universidad.business.EvaluacionBO;
import pe.edu.pucp.universidad.dao.EvaluacionDAO;
import pe.edu.pucp.universidad.impl.EvaluacionDAOImpl;
import pe.edu.pucp.universidad.model.Evaluacion;

public class EvaluacionImplementsBO implements EvaluacionBO {

    private EvaluacionDAO evaluacionDAO;

    public EvaluacionImplementsBO() {
        evaluacionDAO = new EvaluacionDAOImpl();
    }

    @Override
    public int insertar(Evaluacion elemento) {
        try {
            return evaluacionDAO.insertar(elemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int modificar(Evaluacion elemento) {
        try {
            return evaluacionDAO.modificar(elemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int eliminar(int idElemento) {
        try {
            return evaluacionDAO.eliminar(idElemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public Evaluacion buscarPorId(int idElemento) {
        try {
            return evaluacionDAO.obtenerPorId(idElemento);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Evaluacion> listarTodos() {
        try {
            return evaluacionDAO.listarTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            return List.of();
        }
    }

}
