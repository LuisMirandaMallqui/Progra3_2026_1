package pe.edu.pucp.fifasoft.business.DirectorTecnico.impl;

import pe.edu.pucp.fifasoft.business.DirectorTecnico.bo.IDirectorTecnicoBO;
import pe.edu.pucp.fifasoft.model.DirectorTecnico;
import pe.edu.pucp.fifasoft.persistance.DirectorTecnico.DAO.DirectorTecnicoDAO;
import pe.edu.pucp.fifasoft.persistance.DirectorTecnico.Impl.DirectorTecnicoImpl;

import java.util.List;

public class DirectorTecnicoBOImpl implements IDirectorTecnicoBO {

    private final DirectorTecnicoDAO directorDAO = new DirectorTecnicoImpl();


    @Override
    public DirectorTecnico buscarPorId(int id) throws Exception {
        if (id <= 0) throw new Exception("El id del director tecnico es inválido.");
        return directorDAO.buscarPorId(id);
    }

    @Override
    public int insertar(DirectorTecnico objeto) throws Exception {
        return 0;
    }

    @Override
    public int modificar(DirectorTecnico objeto) throws Exception {
        return 0;
    }

    @Override
    public int eliminar(int id) throws Exception {
        return 0;
    }

    @Override
    public List<DirectorTecnico> listarTodos() throws Exception {
        return List.of();
    }
}
