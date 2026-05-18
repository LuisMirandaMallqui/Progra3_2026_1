using System;
using System.Collections.Generic;
using System.Text;

namespace EventMasterSoftPersistance.DAO
{
    public interface IDAO <T>
    {
        int Insertar(T objeto);
        int Modificar(T objeto);
        int Eliminar(T objeto);
        T BuscarPorId(int id);
        List<T> ListarTodos();
    }
}
