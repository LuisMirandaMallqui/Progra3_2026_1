package pe.edu.pucp.transitsoft.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import pe.edu.pucp.transitsoft.dao.VehiculoDAO;
import pe.edu.pucp.transitsoft.daoImp.util.Columna;
import pe.edu.pucp.transitsoft.model.PropietarioDTO;
import pe.edu.pucp.transitsoft.model.VehiculoDTO;
//import pe.edu.pucp.transitsoft.dbmanager.util.MotorDeBaseDeDatos;

public class VehiculoDAOImpl extends DAOImplBase implements VehiculoDAO {

    private VehiculoDTO vehiculoDTO;

    public VehiculoDAOImpl() {
        super("vehiculo");
        this.vehiculoDTO = null;
        this.retornarLlavePrimaria = true;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("id", true, true));
        this.listaColumnas.add(new Columna("placa", false, false));
        this.listaColumnas.add(new Columna("marca", false, false));
        this.listaColumnas.add(new Columna("modelo", false, false));
        this.listaColumnas.add(new Columna("anho", false, false));
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        PropietarioDTO propietario = new PropietarioDTO();
        propietario.setId(this.resultSet.getInt("id_propietario"));
        propietario.setDni(this.resultSet.getString("dni"));
        propietario.setNombres(this.resultSet.getString("nombres"));
        propietario.setApellidos(this.resultSet.getString("apellidos"));
        propietario.setDireccion(this.resultSet.getString("direccion"));

        this.vehiculoDTO = new VehiculoDTO();
        this.vehiculoDTO.setId(this.resultSet.getInt("id_vehiculo"));
        this.vehiculoDTO.setPlaca(this.resultSet.getString("placa"));
        this.vehiculoDTO.setMarca(this.resultSet.getString("marca"));
        this.vehiculoDTO.setModelo(this.resultSet.getString("modelo"));
        this.vehiculoDTO.setAnho(this.resultSet.getInt("anho"));
        this.vehiculoDTO.setPropietario(propietario);
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.vehiculoDTO = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.vehiculoDTO);
    }

    @Override
    public Integer insertar(VehiculoDTO vehiculo) {
        this.vehiculoDTO = vehiculo;
        return super.insertar();
    }

    @Override
    public VehiculoDTO obtenerPorId(Integer vehiculoId) {
        this.vehiculoDTO = new VehiculoDTO();
        this.vehiculoDTO.setId(vehiculoId);
        super.obtenerPorId();
        return this.vehiculoDTO;
    }

    @Override
    public ArrayList<VehiculoDTO> listarTodos() {
        return (ArrayList<VehiculoDTO>) super.listarTodos();
    }

    @Override
    public Integer modificar(VehiculoDTO almacen) {
        this.vehiculoDTO = almacen;
        return super.modificar();
    }

    @Override
    public Integer eliminar(VehiculoDTO almacen) {
        this.vehiculoDTO = almacen;
        return super.eliminar();
    }

}
