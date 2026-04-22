package pe.edu.pucp.softprog.main;
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
import java.util.List;
import java.util.ResourceBundle;
import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.rrhh.model.Empleado;

public class Principal {
    public static void main(String[] args) {
        Area area = new Area();
        area.setNombre("DOCENCIA");

        AreaDAO daoArea = new AreaImpl();
      //  int resultado = daoArea.insertar(area);
    //    if(resultado!=0)
   //         System.out.println("Se ha insertado correctamente");

        List<Area> areas = daoArea.listarTodas();
//        for(Area a : areas){
//            System.out.println(a);
//        }
//        for(int i=0; i<areas.size();i++){
//            System.out.println(areas.get(i));
//        }
        Area areaBuscada = daoArea.buscarPorId(2);
        System.out.println(areaBuscada);

        areaBuscada.setNombre("COMPRAS");
        //resultado = daoArea.modificar(areaBuscada);
      //  if(resultado!=0)
      //      System.out.println("Se ha modificado");
      //  resultado = daoArea.eliminar(areaBuscada.getIdArea());
      //  if(resultado!=0)
      //      System.out.println("Se ha eliminado");

        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy)");
        Empleado empleado = new Empleado("12315","MANUEl","TUPIA","M"
                ,formato.parse("01-03-1977"),area,"JEFE DE VENTAS",1500.00);
        EmpleadoDAO daoEmpleado = new EmpleadoImpl();
        int resultado = daoEmpleado.insertar(empleado);
        if(resultado!=0)
            System.out.println("Se ha resigtrado con exito");

    }
}