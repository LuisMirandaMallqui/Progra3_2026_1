package pe.edu.pucp.fifasoft.business.Seleccion.impl;

import pe.edu.pucp.fifasoft.business.Seleccion.bo.ISeleccionBO;
import pe.edu.pucp.fifasoft.model.Seleccion;
import pe.edu.pucp.fifasoft.persistance.Seleccion.DAO.SeleccionDAO;
import pe.edu.pucp.fifasoft.persistance.Seleccion.Impl.SeleccionImpl;

import java.util.List;

public class SeleccionBOImpl implements ISeleccionBO {

    private final SeleccionDAO seleccionDAO = new SeleccionImpl();

    @Override
    public List<Seleccion> listarTodos() throws Exception {
        return seleccionDAO.listarTodos();
    }

    @Override
    public int insertar(Seleccion objeto) throws Exception {
        return 0;
    }

    @Override
    public int modificar(Seleccion objeto) throws Exception {
        return 0;
    }

    @Override
    public int eliminar(int id) throws Exception {
        return 0;
    }

    @Override
    public Seleccion buscarPorId(int id) throws Exception {
        return null;
    }
}
