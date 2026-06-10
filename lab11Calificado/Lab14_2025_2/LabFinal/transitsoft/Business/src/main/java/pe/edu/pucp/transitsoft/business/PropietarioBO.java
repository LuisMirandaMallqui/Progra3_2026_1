package pe.edu.pucp.transitsoft.business;

import java.util.ArrayList;
import pe.edu.pucp.transitsoft.dao.PropietarioDAO;
import pe.edu.pucp.transitsoft.daoImp.PropietarioDAOImpl;
import pe.edu.pucp.transitsoft.model.PropietarioDTO;

public class PropietarioBO {

    private PropietarioDAO propietarioDAO;

    public PropietarioBO() {
        this.propietarioDAO = new PropietarioDAOImpl();
    }

    public Integer insertar(PropietarioDTO propietarioDTO) {
        Integer id = this.propietarioDAO.insertar(propietarioDTO);
        propietarioDTO.setId(id);
        return id;
    }

    public PropietarioDTO obtenerPorId(Integer propietarioId) {
        PropietarioDTO propietarioDTO = new PropietarioDTO();
        propietarioDTO.setId(propietarioId);
        return this.propietarioDAO.obtenerPorId(propietarioId);
    }

    public ArrayList<PropietarioDTO> listarTodos() {
        return this.propietarioDAO.listarTodos();
    }

    public Integer modificar(PropietarioDTO propietario) {
        return this.propietarioDAO.modificar(propietario);
    }

    public Integer eliminar(Integer propietarioId) {
        PropietarioDTO propietarioDTO = new PropietarioDTO();
        propietarioDTO.setId(propietarioId);
        return this.propietarioDAO.eliminar(propietarioDTO);
    }
}
