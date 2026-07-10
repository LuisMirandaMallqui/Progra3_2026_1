package pe.edu.pucp.universidad.business;

import java.util.List;
import pe.edu.pucp.universidad.model.MatriculaHorario;

public interface MatriculaHorarioBO {
    int insertar(MatriculaHorario elemento);
    int insertarValidado(MatriculaHorario elemento);
    int modificar(MatriculaHorario elemento);
    int eliminar(int idMatricula, int idHorarioCurso);
    MatriculaHorario buscarPorId(int idMatricula, int idHorarioCurso);
    List<MatriculaHorario> listarTodos();
}
