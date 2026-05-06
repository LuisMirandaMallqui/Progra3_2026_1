using SoftProgModel.Almacen;
using SoftProgNegocio.Almacen.BOI;
using SoftProgPersistencia.Almacen.DAO;
using SoftProgPersistencia.Almacen.Impl;
using System;
using System.Collections.Generic;
using System.Text;

namespace SoftProgNegocio.Almacen.BO
{
    public class ProductoBOImpl : IProductoBO
    {
        private ProductoDAO daoProducto;
        public ProductoBOImpl()
        {
            daoProducto = new ProductoImpl();
        }
        public Producto buscarPorId(int id)
        {
            throw new NotImplementedException();
        }

        public int eliminar(int idObjeto)
        {
            throw new NotImplementedException();
        }

        public int insertar(Producto objeto)
        {
            throw new NotImplementedException();
        }

        public List<Producto> listarTodos()
        {
            return daoProducto.listarTodos();
        }

        public int modificar(Producto objeto)
        {
            throw new NotImplementedException();
        }
    }
}
