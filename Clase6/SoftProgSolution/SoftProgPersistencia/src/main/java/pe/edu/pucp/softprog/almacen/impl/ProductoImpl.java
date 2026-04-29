package pe.edu.pucp.softprog.almacen.impl;

import pe.edu.pucp.softprog.almacen.dao.ProductoDAO;
import pe.edu.pucp.softprog.almacen.model.Producto;
import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.rrhh.model.Area;
import pe.edu.pucp.softprog.rrhh.model.Empleado;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductoImpl implements ProductoDAO {

    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;

    @Override
    public int insertar(Producto producto) {
        Map<Integer,Object> parametrosSalida = new HashMap<>();
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, producto.getNombre());
        parametrosEntrada.put(3, producto.getUnidadMedida());
        parametrosEntrada.put(4, producto.getPrecio());
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_PRODUCTO", parametrosEntrada, parametrosSalida);
        producto.setIdProducto((int)parametrosSalida.get(1));
        System.out.println("Se ha registrado el producto");
        return producto.getIdProducto();
    }

    @Override
    public int modificar(Producto producto) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, producto.getIdProducto());
        parametrosEntrada.put(2, producto.getNombre());
        parametrosEntrada.put(3, producto.getUnidadMedida());
        parametrosEntrada.put(4, producto.getPrecio());
        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_PRODUCTO", parametrosEntrada, null);
        System.out.println("Se ha modificado el producto");
        return resultado;
    }

    @Override
    public int eliminar(int idProducto) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idProducto);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_PRODUCTO", parametrosEntrada, null);
        System.out.println("Se ha realizado la eliminacion del producto");
        return resultado;
    }

    @Override
    public Producto buscarPorId(int idProducto) {
        Producto producto = null;
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_PRODUCTO_X_ID", null);
        try{
            if(rs.next()){
                producto = new Producto();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setUnidadMedida(rs.getString("unidad_medida"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setActivo(true);
            }
        }catch(Exception ex){
            System.out.println("Error al buscar producto por id: " + ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return producto;
    }

    @Override
    public List<Producto> listarTodos() {
        List<Producto> productos = null;
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_PRODUCTOS_TODOS", null);
        try{
            while(rs.next()){
                if(productos == null) productos = new ArrayList<>();
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setUnidadMedida(rs.getString("unidad_medida"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setActivo(true);
                productos.add(producto);
            }
        }catch(Exception ex){
            System.out.println("Error al listar productos: " + ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return productos;
    }
}
