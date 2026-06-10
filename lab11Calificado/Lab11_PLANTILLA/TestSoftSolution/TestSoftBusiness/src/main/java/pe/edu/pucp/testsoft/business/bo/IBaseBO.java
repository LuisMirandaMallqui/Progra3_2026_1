package pe.edu.pucp.testsoft.business.bo;

import java.util.List;

// BO genérico. Lanza Exception: la capa de negocio valida ANTES de delegar al DAO.
public interface IBaseBO<T> {
    int insertar(T objeto) throws Exception;
    int modificar(T objeto) throws Exception;
    int eliminar(int id) throws Exception;
    T buscarPorId(int id) throws Exception;
    List<T> listarTodos() throws Exception;
}
