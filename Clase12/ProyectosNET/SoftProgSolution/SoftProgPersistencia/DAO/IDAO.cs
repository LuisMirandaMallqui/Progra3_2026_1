using System;
using System.Collections.Generic;
using System.Text;

namespace SoftProgPersistencia.DAO
{
    public interface IDAO <T>
    {
        int insertar(T objeto);
        int modificar(T objeto);
        int eliminar(int id);
        T buscarPorId(int id);
        List<T> listarTodos();
    }
}
