package pe.edu.pucp.universidad.dao;

import java.util.ArrayList;
import pe.edu.pucp.universidad.model.MatriculaHorario;

public interface MatriculaHorarioDAO {
    int insertar(MatriculaHorario elemento) throws Exception;
    int insertarValidado(MatriculaHorario elemento) throws Exception;
    int modificar(MatriculaHorario elemento) throws Exception;
    int eliminar(long idMatricula, long idHorarioCurso) throws Exception;
    MatriculaHorario obtenerPorId(long idMatricula, long idHorarioCurso) throws Exception;
    ArrayList<MatriculaHorario> listarTodos() throws Exception;
}
