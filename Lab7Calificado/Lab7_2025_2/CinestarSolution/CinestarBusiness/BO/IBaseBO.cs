using System;
using System.Collections.Generic;
using System.Text;

namespace CinestarBusiness.BO
{
    public interface IBaseBO<T>
    {
        int Insertar(T objeto);
        int Modificar(T objeto);
        int Eliminar(int idObjeto);
        List<T> ListarTodos();
        T BuscarPorId(int id);
    }
}
