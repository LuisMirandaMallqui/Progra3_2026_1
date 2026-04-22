package pe.edu.pucp.softprog.gestclientes.impl;

import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.gestclientes.dao.ClienteDAO;
import pe.edu.pucp.softprog.gestclientes.model.Categoria;
import pe.edu.pucp.softprog.gestclientes.model.Cliente;
import pe.edu.pucp.softprog.rrhh.model.Area;
import pe.edu.pucp.softprog.rrhh.model.Empleado;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ClienteImpl implements ClienteDAO {

    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;

    @Override
    public int insertar(Cliente cliente) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_CLIENTE (?,?,?,?,?,?,?,?)}");
            cs.registerOutParameter("_id_cliente", Types.INTEGER);
            cs.setString("_DNI",cliente.getDNI());
            cs.setString("_nombre", cliente.getNombre());
            cs.setString("_apellido_paterno", cliente.getApellidoPaterno());
            cs.setString("_genero", String.valueOf(cliente.getGenero()));
            cs.setDate("_fecha_nacimiento", new java.sql.Date(cliente.getFechaNacimiento().getTime()));
            cs.setDouble("_linea_credito", cliente.getLineaCredito());
            cs.setString("_categoria", cliente.getCategoria().name());
            cs.executeUpdate();
            cliente.setIdPersona(cs.getInt("_id_cliente"));
            resultado = cliente.getIdPersona();
        }catch(Exception ex){
            System.out.println("Error al insertar cliente: " + ex.getMessage());
        }finally{
            try{cs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int modificar(Cliente cliente) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call MODIFICAR_CLIENTE (?,?,?,?,?,?,?,?)}");
            cs.setInt("_id_cliente", cliente.getIdPersona());
            cs.setString("_DNI",cliente.getDNI());
            cs.setString("_nombre", cliente.getNombre());
            cs.setString("_apellido_paterno", cliente.getApellidoPaterno());
            cs.setString("_genero", String.valueOf(cliente.getGenero()));
            cs.setDate("_fecha_nacimiento", new java.sql.Date(cliente.getFechaNacimiento().getTime()));
            cs.setDouble("_linea_credito", cliente.getLineaCredito());
            cs.setString("_categoria", cliente.getCategoria().name());
            resultado = cs.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error al modificar cliente: " + ex.getMessage());
        }finally{
            try{cs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    //No se implementará el eliminar debido a que no corresponde a la lógica del negocio
    @Override
    public int eliminar(int id) {
        return 0;
    }


    @Override
    public Cliente buscarPorId(int id) {
        return null;
    }

    @Override
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = null;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_CLIENTES_TODOS()}");
            rs = cs.executeQuery();
            while(rs.next()){
                if(clientes == null) clientes = new ArrayList<>();
                Cliente cliente = new Cliente();
                cliente.setIdPersona(rs.getInt("id_persona"));
                cliente.setDNI(rs.getString("DNI"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellidoPaterno(rs.getString("apellido_paterno"));
                cliente.setGenero(rs.getString("genero").charAt(0));
                cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                cliente.setLineaCredito(rs.getDouble("linea_credito"));
                cliente.setCategoria(Categoria.valueOf(rs.getString("categoria")));
                clientes.add(cliente);
            }
        }catch(Exception ex){
            System.out.println("Error al listar clientes: " + ex.getMessage());
        }finally{
            try{cs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return clientes;
    }
}
