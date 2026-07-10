package pe.edu.pucp.universidad.business.base;

import java.util.List;

public interface BaseBO<T> {
    int insertar(T elemento);
    int modificar(T elemento);
    int eliminar(int idElemento);
    T buscarPorId(int idElemento);
    List<T> listarTodos();
}
