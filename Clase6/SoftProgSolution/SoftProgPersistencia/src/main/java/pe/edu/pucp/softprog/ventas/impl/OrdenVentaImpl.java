package pe.edu.pucp.softprog.ventas.impl;

import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.ventas.dao.OrdenVentaDAO;
import pe.edu.pucp.softprog.ventas.model.LineaOrdenVenta;
import pe.edu.pucp.softprog.ventas.model.OrdenVenta;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

public class OrdenVentaImpl implements OrdenVentaDAO {

    private Connection con;
    private CallableStatement cs;

    @Override
    public int insertar(OrdenVenta ordenVenta) {
        int resultado = 0;
        try {

            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);
            cs = con.prepareCall("{call INSERTAR_ORDEN_VENTA(?,?,?,?)}");
            cs.registerOutParameter("_id_orden_venta", Types.INTEGER);
            cs.setInt("_fid_empleado",ordenVenta.getEmpleado().getIdPersona());
            cs.setInt("_fid_cliente",ordenVenta.getCliente().getIdPersona());
            cs.setDouble("_total", ordenVenta.getTotal());
            cs.executeUpdate();
            ordenVenta.setIdOrdenVenta(cs.getInt("_id_orden_venta"));
            for(LineaOrdenVenta lov : ordenVenta.getLineasOrdenVenta()) {
                cs = con.prepareCall("{call INSERTAR_LINEA_ORDEN_VENTA(?,?,?,?,?)}");
                cs.registerOutParameter("_id_linea_orden_venta", Types.INTEGER);
                cs.setInt("_fid_orden_venta", ordenVenta.getIdOrdenVenta());
                cs.setInt("_fid_producto", lov.getProducto().getIdProducto());
                cs.setInt("_cantidad_unidades", lov.getCantidadUnidades());
                cs.setDouble("_subtotal", lov.getSubtotal());
                cs.executeUpdate();
            }
            resultado = ordenVenta.getIdOrdenVenta();
            con.commit();
        }catch(Exception ex){
            try{con.rollback();}catch(Exception ex1){}
            System.out.println("Error al insertar la orden de venta: " + ex.getMessage());
        }finally{
            try{cs.close();}catch(Exception ex){
                System.out.println("ERROR: " + ex.getMessage());
            }
            try{con.close();}catch(Exception ex){
                System.out.println("ERROR: " + ex.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public int modificar(OrdenVenta objeto) {
        return 0;
    }

    @Override
    public int eliminar(int id) {
        return 0;
    }

    @Override
    public OrdenVenta buscarPorId(int id) {
        return null;
    }

    @Override
    public List<OrdenVenta> listarTodos() {
        return List.of();
    }
}
