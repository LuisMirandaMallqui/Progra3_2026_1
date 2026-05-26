package pe.edu.pucp.reniecsoft.business.bo;

import java.util.List;

public interface IBaseBO <T>{
    int insertar(T objeto) throws Exception;
    int modificar(T objeto) throws Exception;
    int eliminar(String dni) throws Exception;
    List<T> listarTodos(T objeto) throws Exception;
    T buscarPorDni(String dni) throws Exception;
}
