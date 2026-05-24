package pe.edu.pucp.testsoft.dao.impl;

import pe.edu.pucp.testsoft.dao.IOpcionRespuestaDAO;
import pe.edu.pucp.testsoft.dao.manager.DBManager;
import pe.edu.pucp.testsoft.model.ExamenPregunta;
import pe.edu.pucp.testsoft.model.OpcionRespuesta;
import pe.edu.pucp.testsoft.model.Pregunta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OpcionRespuestaDAOImpl implements IOpcionRespuestaDAO {
    // solo las que coinciden con el id de la pregunta
    public ArrayList<OpcionRespuesta> obtenerOpciones (int idPregunta){
        // OPcionRespuesta model no tiene direccion a pregunta
        ArrayList<OpcionRespuesta> opciones = new ArrayList<OpcionRespuesta>();
        String sql = "SELECT id, texto_opcion, es_correcta, orden FROM opcion_respuesta WHERE id_pregunta = ? order by orden asc";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPregunta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OpcionRespuesta opcionRespuesta = new OpcionRespuesta();
                    opcionRespuesta.setId(rs.getInt("id"));
                    opcionRespuesta.setTextoOpcion(rs.getString("texto_opcion"));
                    opcionRespuesta.setEsCorrecta(rs.getBoolean("es_correcta"));
                    opcionRespuesta.setOrden(rs.getInt("orden"));
                    opciones.add(opcionRespuesta);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return opciones;
    }
}
