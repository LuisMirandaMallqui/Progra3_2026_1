package pe.edu.pucp.universidad.baseDAO;

import java.util.ArrayList;

public interface BaseDAO<T> {
    Integer insertar(T modelo) throws Exception;
    int modificar(T modelo) throws Exception;
    int eliminar(long id) throws Exception;
    T obtenerPorId(long id) throws Exception;
    ArrayList<T> listarTodos() throws Exception;
}
