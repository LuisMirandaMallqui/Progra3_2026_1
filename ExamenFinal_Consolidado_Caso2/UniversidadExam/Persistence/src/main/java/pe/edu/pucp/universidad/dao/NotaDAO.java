package pe.edu.pucp.universidad.dao;

import pe.edu.pucp.universidad.baseDAO.BaseDAO;
import pe.edu.pucp.universidad.model.Nota;

public interface NotaDAO extends BaseDAO<Nota> {
    double calcularPromedioFinal(long idMatricula, long idHorarioCurso) throws Exception;
}
