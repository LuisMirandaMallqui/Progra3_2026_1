package pe.edu.pucp.testsoft.dao.impl;

import pe.edu.pucp.testsoft.dao.IExamenDAO;
import pe.edu.pucp.testsoft.dao.manager.DBManager;
import pe.edu.pucp.testsoft.dao.manager.DBManagerPAZ;
import pe.edu.pucp.testsoft.dao.manager.TransactionContext;
import pe.edu.pucp.testsoft.model.Alumno;
import pe.edu.pucp.testsoft.model.EstadoExamen;
import pe.edu.pucp.testsoft.model.Examen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExamenDAOImpl implements IExamenDAO {
    private final DBManagerPAZ dbManager = DBManagerPAZ.getInstance();

    public ArrayList<Examen> listarPendientesPorAlumno(Alumno alumno){
        ArrayList<Examen> examenes = new ArrayList<>();
        String sql = "SELECT id, titulo, fechaCreacion, fechaResolucion, estado, nota, id_alumno FROM examen WHERE id_alumno = ? AND estado = 'PENDIENTE'";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, alumno.getId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Examen examen = new Examen();
                    examen.setId(rs.getInt("id"));
                    examen.setTitulo(rs.getString("titulo"));
                    examen.setFechaCreacion(rs.getDate("fechaCreacion"));
                    examen.setFechaResolucion(rs.getDate("fechaResolucion"));
                    examen.setEstado(EstadoExamen.valueOf(rs.getString("estado")));
                    examen.setNota(rs.getInt("nota"));
                    examen.setAlumno(alumno);
                    examenes.add(examen);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return examenes;
    }

    public Examen obtenerExamen(int id, Alumno alumno){
        String sql = "SELECT id, titulo, fechaCreacion, fechaResolucion, estado, nota, id_alumno FROM examen WHERE id = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, alumno.getId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Examen examen = new Examen();
                    examen.setId(rs.getInt("id"));
                    examen.setTitulo(rs.getString("titulo"));
                    examen.setFechaCreacion(rs.getDate("fechaCreacion"));
                    examen.setFechaResolucion(rs.getDate("fechaResolucion"));
                    examen.setEstado(EstadoExamen.valueOf(rs.getString("estado")));
                    examen.setNota(rs.getInt("nota"));
                    examen.setAlumno(alumno);
                    return examen;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public int modificar(Examen examen) {
        Map<Integer, Object> entrada = new HashMap<>();
        entrada.put(1, examen.getEstado().toString());
        entrada.put(2, examen.getNota());
        entrada.put(3, examen.getId());
        return dbManager.ejecutarProcedimiento("SP_ACTUALIZAR_RESULTADO_EXAMEN", entrada, null);
    }
}