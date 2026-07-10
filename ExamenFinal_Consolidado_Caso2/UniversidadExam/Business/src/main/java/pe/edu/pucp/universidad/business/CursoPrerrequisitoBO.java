package pe.edu.pucp.universidad.business;

import java.util.List;
import pe.edu.pucp.universidad.model.CursoPrerrequisito;

public interface CursoPrerrequisitoBO {
    int insertar(CursoPrerrequisito elemento);
    int modificar(CursoPrerrequisito elemento);
    int eliminar(int idCurso, int idCursoPrerreq);
    CursoPrerrequisito buscarPorId(int idCurso, int idCursoPrerreq);
    List<CursoPrerrequisito> listarTodos();
}
