package pe.edu.pucp.softprog.rrhh.impl;

import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.rrhh.dao.EmpleadoDAO;
import pe.edu.pucp.softprog.rrhh.model.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EmpleadoImpl  implements EmpleadoDAO {
    private Connection con;
    private PreparedStatement pst;

    @Override
    public List<Empleado> buscarPorDNI(String DNI) {
        return List.of();
    }

    @Override
    public int insertar(Empleado objeto) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "INSERT INTO persona(DNI,nombre," +
                    "apellido_paterno,genero,fecha_nacimiento) VALUES" +
                    "(?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1,empleado.getDNI());
            pst.setString(2,empleado.getNombre());
            pst.setString(3,empleado.getApellidoPaterno());
            pst.setString(4,String.valueOf(empleado.getGenero()));
            pst.setString(5, new java.sql.Date(empleado.getFechaNacimiento().getTime()));
            resultado = pst.executeUpdate();
            sql = "SELECT @@last_insert_id AS id_persona";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            empleado.setIdPersona(rs.getInt("id_persona"));
            sql = "INSERT INTO empleado(id_empleado,fid_area," +
                    "cargo,sueldo,activo) VALUES " +
                    "(?,?,?,?,1)";
            pst = con.prepareStatement(sql);
            pst.setInt(1,empleado.getIdPersona());
            pst.setInt(2,empleado.getArea().getIdArea());



        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            try{pst.close();}cath(Exception ex){System.out.println(ex);}
        }
    }

    @Override
    public int modificar(Empleado objeto) {
        return 0;
    }

    @Override
    public int eliminar(int id) {
        return 0;
    }

    @Override
    public Empleado buscarPorId(int id) {
        return null;
    }

    @Override
    public List<Empleado> listarTodas() {
        return List.of();
    }
}
