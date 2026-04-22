package pe.edu.pucp.softprog.rrhh.impl;

import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.rrhh.dao.EmpleadoDAO;
import pe.edu.pucp.softprog.rrhh.model.Empleado;
import pe.edu.pucp.softprog.rrhh.model.Area;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoImpl implements EmpleadoDAO {

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    private CallableStatement cs;

    @Override
    public List<Empleado> buscarPorDNI(String DNI) {
        return List.of();
    }

    @Override
    public int insertar(Empleado empleado) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_EMPLEADO (?,?,?,?,?,?,?,?,?)}");
            cs.registerOutParameter("_id_empleado", Types.INTEGER);
            cs.setString("_DNI",empleado.getDNI());
            cs.setString("_nombre", empleado.getNombre());
            cs.setString("_apellido_paterno", empleado.getApellidoPaterno());
            cs.setString("_genero", String.valueOf(empleado.getGenero()));
            cs.setDate("_fecha_nacimiento", new java.sql.Date(empleado.getFechaNacimiento().getTime()));
            cs.setInt("_fid_area",empleado.getArea().getIdArea());
            cs.setString("_cargo", empleado.getCargo());
            cs.setDouble("_sueldo",empleado.getSueldo());
            cs.executeUpdate();
            empleado.setIdPersona(cs.getInt("_id_empleado"));
            resultado = empleado.getIdPersona();
        }catch(Exception ex){
            System.out.println("Error al insertar empleado: " + ex.getMessage());
        }finally{
            try{cs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int modificar(Empleado empleado) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call MODIFICAR_EMPLEADO (?,?,?,?,?,?,?,?,?)}");
            cs.setInt("_id_empleado", empleado.getIdPersona());
            cs.setString("_DNI",empleado.getDNI());
            cs.setString("_nombre", empleado.getNombre());
            cs.setString("_apellido_paterno", empleado.getApellidoPaterno());
            cs.setString("_genero", String.valueOf(empleado.getGenero()));
            cs.setDate("_fecha_nacimiento", new java.sql.Date(empleado.getFechaNacimiento().getTime()));
            cs.setInt("_fid_area",empleado.getArea().getIdArea());
            cs.setString("_cargo", empleado.getCargo());
            cs.setDouble("_sueldo",empleado.getSueldo());
            resultado = cs.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error al modificar empleado: " + ex.getMessage());
        }finally{
            try{cs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int idEmpleado) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ELIMINAR_EMPLEADO (?)}");
            cs.setInt("_id_empleado", idEmpleado);
            resultado = cs.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error al eliminar empleado: " + ex.getMessage());
        }finally{
            try{cs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public Empleado buscarPorId(int idEmpleado) {
        Empleado empleado = null;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_EMPLEADO_X_ID(?)}");
            cs.setInt("_id_empleado", idEmpleado);
            rs = cs.executeQuery();
            if(rs.next()){
                if(empleado == null) empleado = new Empleado();
                empleado.setIdPersona(rs.getInt("id_persona"));
                empleado.setDNI(rs.getString("DNI"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellidoPaterno(rs.getString("apellido_paterno"));
                empleado.setGenero(rs.getString("genero").charAt(0));
                empleado.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                Area area = new Area();
                area.setIdArea(rs.getInt("id_area"));
                area.setNombre(rs.getString("nombre_area"));
                empleado.setArea(area);
                empleado.setCargo(rs.getString("cargo"));
                empleado.setSueldo(rs.getDouble("sueldo"));
            }
        }catch(Exception ex){
            System.out.println("Error al buscar empleado por id: " + ex.getMessage());
        }finally{
            try{cs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return empleado;
    }

    @Override
    public List<Empleado> listarTodos() {
        List<Empleado> empleados = null;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_EMPLEADOS_TODOS()}");
            rs = cs.executeQuery();
            while(rs.next()){
                if(empleados == null) empleados = new ArrayList<>();
                Empleado empleado = new Empleado();
                empleado.setIdPersona(rs.getInt("id_persona"));
                empleado.setDNI(rs.getString("DNI"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellidoPaterno(rs.getString("apellido_paterno"));
                empleado.setGenero(rs.getString("genero").charAt(0));
                empleado.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                Area area = new Area();
                area.setIdArea(rs.getInt("id_area"));
                area.setNombre(rs.getString("nombre_area"));
                empleado.setArea(area);
                empleado.setCargo(rs.getString("cargo"));
                empleado.setSueldo(rs.getDouble("sueldo"));
                empleados.add(empleado);
            }
        }catch(Exception ex){
            System.out.println("Error al listar empleados: " + ex.getMessage());
        }finally{
            try{cs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return empleados;
    }
}
