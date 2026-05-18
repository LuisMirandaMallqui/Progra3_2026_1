using EventMasterSoftModel.Evento;
using EventMasterSoftModel.GestionProductora;
using EventMasterSoftPersistance.GestProductora.DAO;
using SoftProgDBManager;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Common;
using System.Text;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace EventMasterSoftPersistance.GestProductora.Impl
{
    public class ProductoraImpl : ProductoraDAO
    {
        public Productora BuscarPorId(int id)
        {
            throw new NotImplementedException();
        }

        public int Eliminar(Productora objeto)
        {
            throw new NotImplementedException();
        }

        public int Insertar(Productora objeto)
        {
            throw new NotImplementedException();
        }

        public List<Productora> ListarTodos()
        {
            List<Productora> productoras = new List<Productora>();
            using DbDataReader lector = DBManager.Instance.EjecutarProcedimientoLectura("LISTAR_PRODUCTORAS_TODAS", null);
            while (lector.Read())
            {
                Productora productora = new Productora();
                if (!lector.IsDBNull(lector.GetOrdinal("id_productora"))) productora.IdProductora = lector.GetInt32(lector.GetOrdinal("id_productora"));
                if (!lector.IsDBNull(lector.GetOrdinal("nombre"))) productora.Nombre = lector.GetString(lector.GetOrdinal("nombre"));
                if (!lector.IsDBNull(lector.GetOrdinal("activa"))) productora.Activa = lector.GetBoolean(lector.GetOrdinal("activa"));
                productoras.Add(productora);
            }
            return productoras;
        }

        public int Modificar(Productora objeto)
        {
            throw new NotImplementedException();
        }
    }
}
