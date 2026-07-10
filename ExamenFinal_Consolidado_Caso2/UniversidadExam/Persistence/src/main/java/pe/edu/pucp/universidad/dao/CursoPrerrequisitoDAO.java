package pe.edu.pucp.universidad.dao;

import java.util.ArrayList;
import pe.edu.pucp.universidad.model.CursoPrerrequisito;

public interface CursoPrerrequisitoDAO {
    int insertar(CursoPrerrequisito elemento) throws Exception;
    int modificar(CursoPrerrequisito elemento) throws Exception;
    int eliminar(long idCurso, long idCursoPrerreq) throws Exception;
    CursoPrerrequisito obtenerPorId(long idCurso, long idCursoPrerreq) throws Exception;
    ArrayList<CursoPrerrequisito> listarTodos() throws Exception;
}
