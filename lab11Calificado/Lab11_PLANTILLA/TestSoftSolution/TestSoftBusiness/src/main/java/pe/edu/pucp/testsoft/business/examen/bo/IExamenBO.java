package pe.edu.pucp.testsoft.business.examen.bo;

import pe.edu.pucp.testsoft.model.Examen;
import java.util.List;

public interface IExamenBO {
    List<Examen> listarPendientesPorAlumno(int idAlumno) throws Exception;
    Examen buscarPorId(int id) throws Exception;
    int actualizarResultado(int idExamen, String estado, int nota) throws Exception;
}
