package pe.com.citasvet.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.com.citasvet.config.DBManager;
import pe.com.citasvet.dao.ITutorDAO;
import pe.com.citasvet.modelo.Tutor;

public class TutorDAOImpl implements ITutorDAO {
    @Override
    public int insertar(Tutor tutor) {
        String sql = "insert into tutor(dni, nombre, direccion, telefono) values(?, ?, ?, ?)";
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement cmd = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            cmd.setString(1, tutor.getDni());
            cmd.setString(2, tutor.getNombre());
            cmd.setString(3, tutor.getDireccion());
            cmd.setString(4, tutor.getTelefono());
            
            if (cmd.executeUpdate() == 0) {
                System.err.println("El registro no se inserto.");
                return 0;
            }
            
            try (ResultSet rs = cmd.getGeneratedKeys()) {
                return rs.next() ? rs.getInt(1) : -1;
            }
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante la insercion: " + e.getMessage());
            throw new RuntimeException("No se pudo insertar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al insertar el registro.", e);
        }
    }

    @Override
    public boolean modificar(Tutor tutor) {
        String sql = "update tutor set dni = ?, nombre = ?, direccion = ?, telefono = ? WHERE id = ?";
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement cmd = conn.prepareStatement(sql)) {
            
            cmd.setString(1, tutor.getDni());
            cmd.setString(2, tutor.getNombre());
            cmd.setString(3, tutor.getDireccion());
            cmd.setString(4, tutor.getTelefono());
            cmd.setInt(5, tutor.getId());
            
            return cmd.executeUpdate() > 0;
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante la modificacion: " + e.getMessage());
            throw new RuntimeException("No se pudo modificar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al modificar el registro.", e);
        }
    }
    
    @Override
    public boolean eliminar(int id) {
        String sql = "delete from tutor where id = ?";
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement cmd = conn.prepareStatement(sql)) {
            
            cmd.setInt(1, id);
            
            return cmd.executeUpdate() > 0;
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante la eliminacion: " + e.getMessage());
            throw new RuntimeException("No se pudo eliminar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al eliminar el registro.", e);
        }
    }

    @Override
    public Tutor buscar(int id) {
        String sql = "select * from tutor where id = ?";
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement cmd = conn.prepareStatement(sql)) {
            
            cmd.setInt(1, id);
            ResultSet rs = cmd.executeQuery();
            
            if (!rs.next()) {
                System.err.println("No se encontro el area con id: " + id);
                return null;
            }
            
            Tutor tutor = new Tutor();
            tutor.setId(rs.getInt("id"));
            tutor.setDni(rs.getString("dni"));
            tutor.setNombre(rs.getString("nombre"));
            tutor.setDireccion(rs.getString("direccion"));
            tutor.setTelefono(rs.getString("telefono"));
            
            return tutor;
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante la busqueda: " + e.getMessage());
            throw new RuntimeException("No se pudo buscar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al buscar el registro.", e);
        }
    }

    @Override
    public List<Tutor> listar() {
        String sql = "select * from tutor";
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement cmd = conn.prepareStatement(sql)) {
            ResultSet rs = cmd.executeQuery();
            
            List<Tutor> tutores = new ArrayList<>();
            while (rs.next()) {
                Tutor tutor = new Tutor();
                tutor.setId(rs.getInt("id"));
                tutor.setDni(rs.getString("dni"));
                tutor.setNombre(rs.getString("nombre"));
                tutor.setDireccion(rs.getString("direccion"));
                tutor.setTelefono(rs.getString("telefono"));
                tutores.add(tutor);
            }
            
            return tutores;
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante el listado: " + e.getMessage());
            throw new RuntimeException("No se pudo listar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al listar los registros.", e);
        }
    }
}