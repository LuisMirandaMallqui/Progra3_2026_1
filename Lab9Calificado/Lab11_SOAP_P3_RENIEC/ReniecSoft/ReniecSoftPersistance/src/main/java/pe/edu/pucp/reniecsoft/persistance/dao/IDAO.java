package pe.edu.pucp.reniecsoft.persistance.dao;

import java.util.List;

public interface IDAO <T>{
      int insertar(T objeto);
      int modificar(T objeto);
      int eliminar(String dni);
      T buscarPorDni(String dni);
      List<T> listarTodos();
}
