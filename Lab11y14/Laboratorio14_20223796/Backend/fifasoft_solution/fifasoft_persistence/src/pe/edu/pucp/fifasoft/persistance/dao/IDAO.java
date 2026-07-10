package pe.edu.pucp.fifasoft.persistance.dao;

import java.util.List;

// Interfaz genérica CRUD. id como int (el dominio Alumno/Examen usa PK entera autoincrement).
public interface IDAO<T> {
    int insertar(T objeto);
    int modificar(T objeto);
    int eliminar(int id);
    T buscarPorId(int id);
    List<T> listarTodos();
}
