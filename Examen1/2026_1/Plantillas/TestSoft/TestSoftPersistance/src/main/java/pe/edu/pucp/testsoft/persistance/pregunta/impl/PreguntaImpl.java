package pe.edu.pucp.testsoft.persistance.pregunta.impl;

import pe.edu.pucp.testsoft.config.DBManager;
import pe.edu.pucp.testsoft.model.pregunta.Pregunta;
import pe.edu.pucp.testsoft.persistance.pregunta.dao.PreguntaDAO;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreguntaImpl implements PreguntaDAO {
    private final DBManager dbManager = DBManager.getInstance();

    // SP: insertar_pregunta(IN p_enunciado, OUT p_id)
    @Override
    public int insertar(Pregunta pregunta) {
        Map<Integer, Object> entrada = new HashMap<>();
        Map<Integer, Object> salida = new HashMap<>();
        entrada.put(1, pregunta.getEnunciado());
        salida.put(2, Types.INTEGER);

        dbManager.ejecutarProcedimiento("insertar_pregunta", entrada, salida);
        pregunta.setId((int) salida.get(2));
        return pregunta.getId();
    }

    // SP: buscar_alumno_por_id... no, para pregunta no hay SP de buscar por id
    // pero lo implementamos por si acaso con listar y filtrar, o se puede crear el SP
    @Override
    public Pregunta buscarPorId(int id) {
        // Nota: se podría crear un SP buscar_pregunta_por_id si se necesita
        return null;
    }

    // SP: listar_preguntas()
    @Override
    public List<Pregunta> listarTodos() {
        List<Pregunta> lista = null;
        try (DBManager.ResultadoConsulta rc = dbManager.ejecutarProcedimientoLectura(
                "listar_preguntas", null)) {
            while (rc.getRs().next()) {
                if (lista == null) lista = new ArrayList<>();
                Pregunta p = new Pregunta();
                p.setId(rc.getRs().getInt("id"));
                p.setEnunciado(rc.getRs().getString("enunciado"));
                lista.add(p);
            }
        } catch (Exception ex) {
            System.out.println("Error al listar preguntas: " + ex.getMessage());
        }
        return lista;
    }

    // SP: modificar_pregunta(IN p_id, IN p_enunciado)
    @Override
    public int modificar(Pregunta pregunta) {
        Map<Integer, Object> entrada = new HashMap<>();
        entrada.put(1, pregunta.getId());
        entrada.put(2, pregunta.getEnunciado());
        return dbManager.ejecutarProcedimiento("modificar_pregunta", entrada, null);
    }

    // SP: eliminar_pregunta(IN p_id)
    @Override
    public int eliminar(int id) {
        Map<Integer, Object> entrada = new HashMap<>();
        entrada.put(1, id);
        return dbManager.ejecutarProcedimiento("eliminar_pregunta", entrada, null);
    }

}
