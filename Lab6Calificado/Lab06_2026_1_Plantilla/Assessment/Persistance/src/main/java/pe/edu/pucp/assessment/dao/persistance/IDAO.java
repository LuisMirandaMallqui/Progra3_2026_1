package pe.edu.pucp.assessment.dao.persistance;

import java.util.List;

public interface IDAO <T> {
    int insertar(T objeto);
    int modificar(T objeto);
    int eliminar(int id);
    T buscarPorId(int id);
    List<T> listarTodos();
}
