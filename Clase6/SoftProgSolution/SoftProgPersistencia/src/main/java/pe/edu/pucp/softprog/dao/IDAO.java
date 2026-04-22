package pe.edu.pucp.softprog.dao;

import pe.edu.pucp.softprog.rrhh.model.Area;

import java.util.List;

public interface IDAO <T> {
    int insertar(T objeto);
    int modificar(T objeto);
    int eliminar(int id);
    T buscarPorId(int id);
    List<T> listarTodos();
}
