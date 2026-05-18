using EventMasterSoftBusiness.GestProductora.BOI;
using EventMasterSoftModel.GestionProductora;
using EventMasterSoftPersistance.GestProductora.DAO;
using EventMasterSoftPersistance.GestProductora.Impl;
using System;
using System.Collections.Generic;
using System.Text;

namespace EventMasterSoftBusiness.GestProductora.BO
{
    public class ProductoraBOImpl : IProductoraBO
    {
        private ProductoraDAO daoProductora;

        public ProductoraBOImpl()
        {
            this.daoProductora = new ProductoraImpl();
        }

        public Productora buscarPorId(int idObjeto)
        {
            throw new NotImplementedException();
        }

        public int Eliminar(int idObjeto)
        {
            throw new NotImplementedException();
        }

        public int Insertar(Productora objeto)
        {
            throw new NotImplementedException();
        }

        public List<Productora> ListarTodos()
        {
            return daoProductora.ListarTodos();
        }

        public int Modificar(Productora objeto)
        {
            throw new NotImplementedException();
        }
    }
}
