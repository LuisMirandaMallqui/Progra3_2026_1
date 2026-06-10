package pe.edu.pucp.transitsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.transitsoft.model.PropietarioDTO;

public interface PropietarioDAO {
 public Integer insertar(PropietarioDTO almacen);

    public PropietarioDTO obtenerPorId(Integer almacenId);

    public ArrayList<PropietarioDTO> listarTodos();

    public Integer modificar(PropietarioDTO almacen);

    public Integer eliminar(PropietarioDTO almacen);
}
