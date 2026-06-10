package pe.edu.pucp.testsoft.persistance.examen.DAO;

import pe.edu.pucp.testsoft.model.Examen;
import pe.edu.pucp.testsoft.persistance.dao.IDAO;
import java.util.List;

// Examen no necesita CRUD completo en el front: hereda IDAO pero lo relevante es la lectura.
public interface ExamenDAO extends IDAO<Examen> {
    List<Examen> listarPendientesPorAlumno(int idAlumno);
    int actualizarResultado(int idExamen, String estado, int nota);
}
