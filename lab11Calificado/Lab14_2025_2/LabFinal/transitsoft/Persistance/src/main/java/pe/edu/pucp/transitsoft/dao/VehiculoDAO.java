package pe.edu.pucp.transitsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.transitsoft.model.VehiculoDTO;

public interface VehiculoDAO {

    public Integer insertar(VehiculoDTO almacen);

    public VehiculoDTO obtenerPorId(Integer almacenId);

    public ArrayList<VehiculoDTO> listarTodos();

    public Integer modificar(VehiculoDTO almacen);

    public Integer eliminar(VehiculoDTO almacen);
}
