package pe.edu.pucp.softprog.rrhh.impl;

import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.rrhh.dao.AreaDAO;
import pe.edu.pucp.softprog.rrhh.model.Area;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
public class AreaImpl implements AreaDAO {

    private Connection con;
    private PreparedStatement pst;
    private CallableStatement cs;
    private ResultSet rs;
    private Statement st;

    @Override
    public int insertar(Area area) {
        int resultado = 0;
        try {
            /**
            con = DBManager.getInstance().getConnection();
            String sql = "INSERT INTO area(nombre, activa) VALUES (?,1)";
            pst = con.prepareStatement(sql);
            pst.setString(1,area.getNombre());
            resultado = pst.executeUpdate();**/
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_AREA(?,?)}");
            cs.registerOutParameter("_id_area", Types.INTEGER);
            cs.setString("_nombre",area.getNombre());
            cs.executeUpdate();
            resultado = cs.getInt("_id_area");
        }catch(Exception ex){
            System.out.println("ERROR: " + ex.getMessage());
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
    public int modificar(Area area) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "UPDATE area SET nombre = ? WHERE id_area = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1,area.getNombre());
            pst.setInt(2,area.getIdArea());
            resultado = pst.executeUpdate();
        }catch(Exception ex){
            System.out.println("ERROR: " + ex.getMessage());
        }finally{
            try{pst.close();}catch(Exception ex){
                System.out.println("ERROR: " + ex.getMessage());
            }
            try{con.close();}catch(Exception ex){
                System.out.println("ERROR: " + ex.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public int eliminar(int idArea) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "UPDATE area SET activa = 0 WHERE id_area = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1,idArea);
            resultado = pst.executeUpdate();
        }catch(Exception ex){
            System.out.println("ERROR: " + ex.getMessage());
        }finally{
            try{pst.close();}catch(Exception ex){
                System.out.println("ERROR: " + ex.getMessage());
            }
            try{con.close();}catch(Exception ex){
                System.out.println("ERROR: " + ex.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public Area buscarPorId(int idArea) {
        Area area = null;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT id_area, nombre, activa FROM area " +
                    "WHERE id_area = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1,idArea);
            rs = pst.executeQuery();
            if(rs.next()){
                area = new Area();
                area.setIdArea(rs.getInt("id_area"));
                area.setNombre(rs.getString("nombre"));
                area.setActiva(true);
            }
        }catch(Exception ex){
            System.out.println("ERROR: " + ex.getMessage());
        }finally{
            try{pst.close();}catch(Exception ex){
                System.out.println("ERROR: " + ex.getMessage());
            }
            try{con.close();}catch(Exception ex){
                System.out.println("ERROR: " + ex.getMessage());
            }
        }
        return area;
    }

    @Override
    public List<Area> listarTodas() {
        List<Area> areas = null;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT id_area, nombre, activa FROM area " +
                    "WHERE activa = 1";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                if(areas == null) areas = new ArrayList<>();
                Area area = new Area();
                area.setIdArea(rs.getInt("id_area"));
                area.setNombre(rs.getString("nombre"));
                area.setActiva(true);
                areas.add(area);
            }
        }catch(Exception ex){
            System.out.println("ERROR: " + ex.getMessage());
        }finally{
            try{pst.close();}catch(Exception ex){
                System.out.println("ERROR: " + ex.getMessage());
            }
            try{con.close();}catch(Exception ex){
                System.out.println("ERROR: " + ex.getMessage());
            }
        }
        return areas;
    }
}
