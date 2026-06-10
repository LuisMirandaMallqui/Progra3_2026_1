package pe.edu.pucp.transitsoft.business;

import java.util.ArrayList;
import pe.edu.pucp.transitsoft.daoImp.VehiculoDAOImpl;
import pe.edu.pucp.transitsoft.dao.VehiculoDAO;
import pe.edu.pucp.transitsoft.model.VehiculoDTO;


public class VehiculoBO {

    private VehiculoDAO vehiculoDAO;
    
    public VehiculoBO(){
        this.vehiculoDAO = new VehiculoDAOImpl();
    }
    
    public Integer insertar(VehiculoDTO vehiculoDTO){
        Integer id = this.vehiculoDAO.insertar(vehiculoDTO); 
        vehiculoDTO.setId(id);
        return id;
    }
    
    public VehiculoDTO obtenerPorId(Integer vehiculoId){
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setId(vehiculoId);
        return this.vehiculoDAO.obtenerPorId(vehiculoId);
    }
    
    public ArrayList<VehiculoDTO> listarTodos(){
        return this.vehiculoDAO.listarTodos();
    }
    
    public Integer modificar(VehiculoDTO vehiculo){
        return this.vehiculoDAO.modificar(vehiculo);
    }
    
    public Integer eliminar(Integer vehiculoId){
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setId(vehiculoId);
        return this.vehiculoDAO.eliminar(vehiculoDTO);
    }
}
