using System;
using System.Collections.Generic;
using System.Text;

namespace SoftProgNegocio.BO
{
    public interface IBaseBO<T>
    {
        int insertar(T objeto);
        int modificar(T objeto);
        int eliminar(int idObjeto);
        List<T> listarTodos();
        T buscarPorId(int id);
    }
}
