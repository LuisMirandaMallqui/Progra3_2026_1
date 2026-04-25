package pe.edu.pucp.softprog.almacen.impl;

import pe.edu.pucp.softprog.almacen.dao.ProductoDAO;
import pe.edu.pucp.softprog.almacen.model.Producto;
import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.rrhh.model.Area;
import pe.edu.pucp.softprog.rrhh.model.Empleado;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoImpl implements ProductoDAO {

    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;

    @Override
    public int insertar(Producto objeto) {
        return 0;
    }

    @Override
    public int modificar(Producto objeto) {
        return 0;
    }

    @Override
    public int eliminar(int id) {
        return 0;
    }

    @Override
    public Producto buscarPorId(int id) {
        return null;
    }

    @Override
    public List<Producto> listarTodos() {
        List<Producto> productos = null;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_PRODUCTOS_TODOS()}");
            rs = cs.executeQuery();
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
            try{cs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return productos;
    }
}
