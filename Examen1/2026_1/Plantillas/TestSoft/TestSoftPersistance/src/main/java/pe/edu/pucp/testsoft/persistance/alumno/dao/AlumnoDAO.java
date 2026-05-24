package pe.edu.pucp.testsoft.persistance.alumno.dao;

import pe.edu.pucp.testsoft.persistance.dao.IDAO;
import pe.edu.pucp.testsoft.model.alumno.Alumno;

public interface AlumnoDAO extends IDAO<Alumno> {
    // Patron SELECT INTO: busca por codigo y retorna el id (0 si no existe)
    int buscarPorCodigo(String codigo);
}
