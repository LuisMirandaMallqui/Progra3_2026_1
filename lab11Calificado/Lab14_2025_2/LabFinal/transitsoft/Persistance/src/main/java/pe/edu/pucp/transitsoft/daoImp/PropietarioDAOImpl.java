/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.transitsoft.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.transitsoft.dao.PropietarioDAO;
import pe.edu.pucp.transitsoft.daoImp.util.Columna;
import pe.edu.pucp.transitsoft.dbmanager.util.MotorDeBaseDeDatos;
import pe.edu.pucp.transitsoft.model.PropietarioDTO;

/**
 *
 * @author alulab14
 */


public class PropietarioDAOImpl extends DAOImplBase implements PropietarioDAO {

    private PropietarioDTO propieatarioDTO;

    public PropietarioDAOImpl() {
        super("propietario");
        this.propieatarioDTO = null;
        this.retornarLlavePrimaria = true;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("id", true, true));
        this.listaColumnas.add(new Columna("dni", false, false));
        this.listaColumnas.add(new Columna("nombres", false, false));
        this.listaColumnas.add(new Columna("apellidos", false, false));
        this.listaColumnas.add(new Columna("direccion", false, false));
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        PropietarioDTO propietario = new PropietarioDTO();
        propietario.setId(this.resultSet.getInt("id"));
        propietario.setDni(this.resultSet.getString("dni"));
        propietario.setNombres(this.resultSet.getString("nombres"));
        propietario.setApellidos(this.resultSet.getString("apellidos"));
        propietario.setDireccion(this.resultSet.getString("direccion"));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.propieatarioDTO = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.propieatarioDTO);
    }

    
    @Override
    public Integer insertar(PropietarioDTO propietario) {        
        this.propieatarioDTO = propietario;
        return super.insertar();
    }
    
    @Override
    public PropietarioDTO obtenerPorId(Integer propietarioId) {
        this.propieatarioDTO = new PropietarioDTO();
        this.propieatarioDTO.setId(propietarioId);
        super.obtenerPorId();
        return this.propieatarioDTO;
    }
    
    @Override
    public ArrayList<PropietarioDTO> listarTodos() {
        return (ArrayList<PropietarioDTO>) super.listarTodos();
    }
    
    @Override
    public Integer modificar(PropietarioDTO propietario) {
        this.propieatarioDTO = propietario;
        return super.modificar();
    }
    
    @Override
    public Integer eliminar(PropietarioDTO propietario) {
        this.propieatarioDTO = propietario;
        return super.eliminar();
    }
    
}