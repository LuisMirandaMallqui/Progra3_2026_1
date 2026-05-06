using SoftProgModel.Ventas;
using SoftProgNegocio.Ventas.BOI;
using SoftProgPersistencia.Ventas.DAO;
using SoftProgPersistencia.Ventas.Impl;
using System;
using System.Collections.Generic;
using System.Text;

namespace SoftProgNegocio.Ventas.BO
{
    public class OrdenVentaBOImpl : IOrdenVentaBO
    {
        private OrdenVentaDAO daoOrdenVenta;
        public OrdenVentaBOImpl()
        {
            daoOrdenVenta = new OrdenVentaImpl();
        }
        public OrdenVenta buscarPorId(int idOrdenVenta)
        {
            throw new NotImplementedException();
        }

        public int eliminar(int idObjeto)
        {
            throw new NotImplementedException();
        }

        public int insertar(OrdenVenta ordenVenta)
        {
            return daoOrdenVenta.insertar(ordenVenta);
        }

        public List<OrdenVenta> listarTodos()
        {
            throw new NotImplementedException();
        }

        public int modificar(OrdenVenta objeto)
        {
            throw new NotImplementedException();
        }
    }
}
