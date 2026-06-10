package pe.edu.pucp.testsoft.persistance.alumno.DAO;

import pe.edu.pucp.testsoft.model.Alumno;
import pe.edu.pucp.testsoft.persistance.dao.IDAO;
import java.util.List;

public interface AlumnoDAO extends IDAO<Alumno> {
    int buscarPorCodigo(String codigo);              // patrón SELECT INTO: devuelve id (0 si no existe)
    List<Alumno> buscarPorNombreApellido(String texto);
}
