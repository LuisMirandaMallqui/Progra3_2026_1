package pe.edu.pucp.softprog.main;
import pe.edu.pucp.softprog.rrhh.dao.AreaDAO;
import pe.edu.pucp.softprog.rrhh.impl.AreaImpl;
import pe.edu.pucp.softprog.rrhh.model.Area;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import pe.edu.pucp.softprog.config.DBManager;

public class Principal {
    public static void main(String[] args) {
        Area area = new Area();
        area.setNombre("DOCENCIA");

        AreaDAO daoArea = new AreaImpl();
        int resultado = daoArea.insertar(area);
        if(resultado!=0)
            System.out.println("Se ha insertado correctamente");

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
        resultado = daoArea.modificar(areaBuscada);
        if(resultado!=0)
            System.out.println("Se ha modificado");
        resultado = daoArea.eliminar(areaBuscada.getIdArea());
        if(resultado!=0)
            System.out.println("Se ha eliminado");
    }
}