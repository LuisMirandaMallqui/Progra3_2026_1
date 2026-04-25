package pe.edu.pucp.softprog.main;
import pe.edu.pucp.softprog.almacen.bo.ProductoBOImpl;
import pe.edu.pucp.softprog.almacen.boi.IProductoBO;
import pe.edu.pucp.softprog.almacen.dao.ProductoDAO;
import pe.edu.pucp.softprog.almacen.impl.ProductoImpl;
import pe.edu.pucp.softprog.almacen.model.Producto;
import pe.edu.pucp.softprog.gestclientes.bo.ClienteBOImpl;
import pe.edu.pucp.softprog.gestclientes.boi.IClienteBO;
import pe.edu.pucp.softprog.gestclientes.dao.ClienteDAO;
import pe.edu.pucp.softprog.gestclientes.impl.ClienteImpl;
import pe.edu.pucp.softprog.gestclientes.model.Categoria;
import pe.edu.pucp.softprog.gestclientes.model.Cliente;
import pe.edu.pucp.softprog.rrhh.bo.AreaBOImpl;
import pe.edu.pucp.softprog.rrhh.bo.EmpleadoBOImpl;
import pe.edu.pucp.softprog.rrhh.boi.IAreaBO;
import pe.edu.pucp.softprog.rrhh.boi.IEmpleadoBO;
import pe.edu.pucp.softprog.rrhh.dao.AreaDAO;
import pe.edu.pucp.softprog.rrhh.dao.EmpleadoDAO;
import pe.edu.pucp.softprog.rrhh.impl.AreaImpl;
import pe.edu.pucp.softprog.rrhh.impl.EmpleadoImpl;
import pe.edu.pucp.softprog.rrhh.model.Area;
import pe.edu.pucp.softprog.ventas.bo.OrdenVentaBOImpl;
import pe.edu.pucp.softprog.ventas.boi.IOrdenVentaBO;
import pe.edu.pucp.softprog.ventas.dao.OrdenVentaDAO;
import pe.edu.pucp.softprog.ventas.impl.OrdenVentaImpl;
import pe.edu.pucp.softprog.ventas.model.OrdenVenta;
import pe.edu.pucp.softprog.ventas.model.LineaOrdenVenta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.rrhh.model.Empleado;

public class Principal {
    public static void main(String[] args) throws Exception{
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

        IAreaBO areaBO = new AreaBOImpl();
        Area area = areaBO.buscarPorId(1);
        Empleado empleado = new Empleado("16527933",
                "MANUEL","TUPIA",'M',
                formato.parse("01-03-1977"),area,
                "JEFE DE VENTAS",1500.00);

        IEmpleadoBO empleadoBO = new EmpleadoBOImpl();
        int resultado = empleadoBO.insertar(empleado);
        if(resultado!=0)
            System.out.println("Se ha registrado con exito");
        resultado = empleadoBO.modificar(empleado);
        if(resultado!=0)
            System.out.println("Se ha modificado con exito");
        List<Empleado> empleados = empleadoBO.listarTodos();
        for(Empleado emp : empleados){
            System.out.println(emp.getNombre());
        }
        empleado = empleadoBO.buscarPorId(empleado.getIdPersona());
        resultado = empleadoBO.eliminar(empleado.getIdPersona());

        Cliente cliente = new Cliente("76276541","CAROLINA",
                "CACERES",'F',formato.parse("10-10-1988"),1800.00, Categoria.STANDARD);
        IClienteBO clienteBO = new ClienteBOImpl();
        resultado = clienteBO.insertar(cliente);
        if(resultado!=0)
            System.out.println("Se ha insertado el cliente");
        resultado = clienteBO.modificar(cliente);
        if(resultado!=0)
            System.out.println("Se ha modificado el cliente");
        List<Cliente> clientes = clienteBO.listarTodos();
        for(Cliente c : clientes)
            System.out.println(c.getNombre());

        IProductoBO productoBO = new ProductoBOImpl();
        List<Producto> productos = productoBO.listarTodos();
        for(Producto p : productos){
            System.out.println(p);
        }

        OrdenVenta ov = new OrdenVenta();
        ov.setCliente(cliente);
        ov.setEmpleado(empleado);
        ov.setFechaHora(new Date());

        LineaOrdenVenta lov1 = new LineaOrdenVenta();
        lov1.setProducto(productos.get(0));
        lov1.setCantidadUnidades(2);

        LineaOrdenVenta lov2 = new LineaOrdenVenta();
        lov2.setProducto(productos.get(2));
        lov2.setCantidadUnidades(3);

        ov.agregarLineaOrdenVenta(lov1);
        ov.agregarLineaOrdenVenta(lov2);

        ov.calcularSutotalesYTotal();

        IOrdenVentaBO ordenVentaBO = new OrdenVentaBOImpl();
        resultado = ordenVentaBO.insertar(ov);
        if(resultado!=0)
            System.out.println("La orden de venta se ha registrado");

    }
}