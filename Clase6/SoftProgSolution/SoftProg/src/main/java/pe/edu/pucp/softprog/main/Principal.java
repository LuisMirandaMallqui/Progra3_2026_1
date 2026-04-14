package pe.edu.pucp.softprog.main;

import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.rrhh.model.Area;

import java.sql.*;


public class Principal {
    public static void main(String[] args){
        Area area = new Area();
        area.setNombre("CONTABILIDAD");
       System.out.println(area.getNombre());
        try {
            Connection con = DBManager.getDbManager().getConnection();
            System.out.println("conexión establecida");
            Statement st = con.createStatement();
            String sql = "INSERT INTO area(nombre,activa)"+
                    "VALUES('FINANZAS',1)";
            st.executeUpdate(sql);
            System.out.println("EJECUTADO OK");
            sql = "SELECT * FROM area";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){  // mientras pueda ir avanznado fila en fila
                System.out.println(rs.getInt("id_area") +
                        rs.getString("nombre"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
