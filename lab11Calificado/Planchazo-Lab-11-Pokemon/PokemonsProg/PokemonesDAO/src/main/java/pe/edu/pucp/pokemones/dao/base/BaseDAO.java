package pe.edu.pucp.pokemones.dao.base;

import java.util.List;

public interface BaseDAO <T> {
    int insertar(T elemento);
    int modificar(T elemento);
    int eliminar(int idElemento);
    T buscarPorId(int idElemento);
    List<T> listarTodos();
}
