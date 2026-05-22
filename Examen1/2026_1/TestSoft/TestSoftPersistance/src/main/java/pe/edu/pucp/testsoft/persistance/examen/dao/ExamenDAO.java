package pe.edu.pucp.testsoft.persistance.examen.dao;

import pe.edu.pucp.testsoft.model.examen.Examen;
import pe.edu.pucp.testsoft.model.pregunta.Pregunta;
import pe.edu.pucp.testsoft.persistance.dao.IDAO;

import java.util.List;

public interface ExamenDAO extends IDAO<Examen> {
    List<Pregunta> listarPreguntasPorExamen(int idExamen);
}
