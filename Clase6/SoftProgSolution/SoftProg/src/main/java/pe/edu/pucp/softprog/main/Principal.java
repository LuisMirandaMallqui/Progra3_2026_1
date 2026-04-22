package pe.edu.pucp.softprog.main;
import pe.edu.pucp.softprog.gestclientes.dao.ClienteDAO;
import pe.edu.pucp.softprog.gestclientes.impl.ClienteImpl;
import pe.edu.pucp.softprog.gestclientes.model.Categoria;
import pe.edu.pucp.softprog.gestclientes.model.Cliente;
import pe.edu.pucp.softprog.rrhh.dao.AreaDAO;
import pe.edu.pucp.softprog.rrhh.dao.EmpleadoDAO;
import pe.edu.pucp.softprog.rrhh.impl.AreaImpl;
import pe.edu.pucp.softprog.rrhh.impl.EmpleadoImpl;
import pe.edu.pucp.softprog.rrhh.model.Area;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.rrhh.model.Empleado;

public class Principal {
    public static void main(String[] args) throws Exception{
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        AreaDAO daoArea = new AreaImpl();
        Area area = daoArea.buscarPorId(1);
        Empleado empleado = new Empleado("16527933",
                "MANUEL","TUPIA",'M',
                formato.parse("01-03-1977"),area,
                "JEFE DE VENTAS",1500.00);
        EmpleadoDAO daoEmpleado = new EmpleadoImpl();
        int resultado = daoEmpleado.insertar(empleado);
        if(resultado!=0)
            System.out.println("Se ha registrado con exito");
        resultado = daoEmpleado.modificar(empleado);
        if(resultado!=0)
            System.out.println("Se ha modificado con exito");

        List<Empleado> empleados = daoEmpleado.listarTodos();
        for(Empleado emp : empleados){
            System.out.println(emp.getNombre());
        }
        empleado = daoEmpleado.buscarPorId(empleado.getIdPersona());
        resultado = daoEmpleado.eliminar(empleado.getIdPersona());

        Cliente cliente = new Cliente("76276541","CAROLINA",
                "CACERES",'F',formato.parse("10-10-1988"),1800.00, Categoria.STANDARD);
        ClienteDAO daoCliente = new ClienteImpl();
        resultado = daoCliente.insertar(cliente);
        if(resultado!=0)
            System.out.println("Se ha insertado el cliente");
        resultado = daoCliente.modificar(cliente);
        if(resultado!=0)
            System.out.println("Se ha modificado el cliente");
        List<Cliente> clientes = daoCliente.listarTodos();
        for(Cliente c : clientes)
            System.out.println(c.getNombre());
    }
}