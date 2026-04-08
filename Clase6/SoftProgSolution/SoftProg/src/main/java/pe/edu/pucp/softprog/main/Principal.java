package pe.edu.pucp.softprog.main;

import pe.edu.pucp.softprog.rrhh.model.Area;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Principal {
    public static void main(String[] args){
        Area area = new Area();
        area.setNombre("AREA");
       System.out.println(area.getNombre());

        try {
            Class.forName("com.mysql.c")
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://mysql-prog3-1inf30.co0o3xdzc4rd.us-east-1.rds.amazonaws.com",
                    "admin",
                    "1inf3020261");
            System.out.println("conexión establecida");
            Statement st = con.createStatement();
            String sql = "INSERT INTO area(nombre,activa)"+
                    "VALUES('FINANZAS',1)";
            st.executeUpdate(sql);
            System.out.println("EJECUTADO OK");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


}
