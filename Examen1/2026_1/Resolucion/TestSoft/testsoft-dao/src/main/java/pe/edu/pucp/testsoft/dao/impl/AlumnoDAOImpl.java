package pe.edu.pucp.testsoft.dao.impl;

import pe.edu.pucp.testsoft.dao.IAlumnoDAO;
import pe.edu.pucp.testsoft.dao.manager.DBManager;
import pe.edu.pucp.testsoft.model.Alumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlumnoDAOImpl implements IAlumnoDAO {
    public Alumno buscarAlumno(int id){
        String sql = "select id, codigo, nombre, apellidos, correo, estado from alumno where id = ?";
        try(Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Alumno alumno = new Alumno();
                    alumno.setId(rs.getInt(1));
                    alumno.setCodigo(rs.getString(2));
                    alumno.setNombre(rs.getString(3));
                    alumno.setApellidos(rs.getString(4));
                    alumno.setCorreo(rs.getString(5));
                    alumno.setEstado(rs.getString(6).charAt(0));
                    return alumno;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
