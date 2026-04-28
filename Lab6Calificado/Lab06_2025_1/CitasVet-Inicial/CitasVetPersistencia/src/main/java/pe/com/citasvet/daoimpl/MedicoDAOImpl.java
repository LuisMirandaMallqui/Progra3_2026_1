package pe.com.citasvet.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.com.citasvet.config.DBManager;
import pe.com.citasvet.dao.IMedicoDAO;
import pe.com.citasvet.modelo.Medico;

public class MedicoDAOImpl implements IMedicoDAO {

    @Override
    public int insertar(Medico medico) {
        String sql = "insert into medico(dni, nombre, especialidad) VALUES(?, ?, ?)";
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement cmd = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            cmd.setString(1, medico.getDni());
            cmd.setString(2, medico.getNombre());
            cmd.setString(3, medico.getEspecialidad());
            
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
    public boolean modificar(Medico medico) {
        String sql = "update medico set dni = ?, nombre = ?, especialidad = ? where id = ?";
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement cmd = conn.prepareStatement(sql)) {
            
            cmd.setString(1, medico.getDni());
            cmd.setString(2, medico.getNombre());
            cmd.setString(3, medico.getEspecialidad());
            cmd.setInt(4, medico.getId());
            
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
        String sql = "delete from medico where id = ?";
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            return ps.executeUpdate() > 0;
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
    public Medico buscar(int id) {
        String sql = "select * from medico where id = ?";
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement cmd = conn.prepareStatement(sql)) {
            
            cmd.setInt(1, id);
            try (ResultSet rs = cmd.executeQuery()) {
                if (!rs.next()) {
                    System.err.println("No se encontro la cuenta de usuario con id: " + id);
                    return null;
                }

                Medico medico = new Medico();
                medico.setId(rs.getInt("id"));
                medico.setDni(rs.getString("dni"));
                medico.setNombre(rs.getString("nombre"));
                medico.setEspecialidad(rs.getString("especialidad"));

                return medico;
            }
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
    public List<Medico> listar() {
        String sql = "select * from medico";
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement cmd = conn.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery()) {
            
            List<Medico> cuentas = new ArrayList<>();
            while (rs.next()) {
                Medico medico = new Medico();
                medico.setId(rs.getInt("id"));
                medico.setDni(rs.getString("dni"));
                medico.setNombre(rs.getString("nombre"));
                medico.setEspecialidad(rs.getString("especialidad"));
                cuentas.add(medico);
            }
            
            return cuentas;
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