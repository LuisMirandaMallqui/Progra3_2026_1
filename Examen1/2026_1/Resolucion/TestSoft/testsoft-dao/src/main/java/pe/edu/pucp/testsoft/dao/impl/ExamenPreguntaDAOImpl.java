package pe.edu.pucp.testsoft.dao.impl;

import pe.edu.pucp.testsoft.dao.IExamenDAO;
import pe.edu.pucp.testsoft.dao.IExamenPreguntaDAO;
import pe.edu.pucp.testsoft.dao.manager.DBManager;
import pe.edu.pucp.testsoft.model.EstadoExamen;
import pe.edu.pucp.testsoft.model.Examen;
import pe.edu.pucp.testsoft.model.ExamenPregunta;
import pe.edu.pucp.testsoft.model.Pregunta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExamenPreguntaDAOImpl implements IExamenPreguntaDAO {
    public ArrayList<ExamenPregunta> obtenerPreguntas(int idExamen){
        ArrayList<ExamenPregunta> preguntasExamen = new ArrayList<ExamenPregunta>();
        String sql = "SELECT ep.id, ep.orden, ep.puntaje, p.id as id_pregunta, p.enunciado FROM examen_pregunta ep INNER JOIN pregunta p ON ep.id_pregunta = p.id WHERE ep.id_examen = ? order by ep.orden asc";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idExamen);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ExamenPregunta preguntaExamen = new ExamenPregunta();
                    preguntaExamen.setId(rs.getInt("id"));
                    preguntaExamen.setOrden(rs.getInt("orden"));
                    preguntaExamen.setPuntaje(rs.getInt("puntaje"));

                    Pregunta pregunta = new Pregunta();
                    pregunta.setId(rs.getInt("id_pregunta"));
                    pregunta.setEnunciado(rs.getString("enunciado"));
                    preguntaExamen.setPregunta(pregunta);
                    preguntasExamen.add(preguntaExamen);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return preguntasExamen;
    }
}
