using PUCP.TransitSoft.Modelo;
using PUCP.TransitSoft.Persistencia.DAO;
using PUCP.TransitSoft.Db;
using System;
using System.Collections.Generic;
using System.Data.Common;

namespace PUCP.TransitSoft.Persistencia.DAOImpl {
    public class InfraccionDAOImpl : IInfraccionDAO {
        public int Crear(Infraccion modelo) {
            DBManager dbManager = DBFactoryProvider.GetManager();
            using (DbConnection conn = dbManager.GetConnection()) {
                conn.Open();

                // TODO: Realizar la creacion de la infraccion en la BD
            }

            throw new NotImplementedException();
        }

        public List<Infraccion> LeerTodos() {
            DBManager dbManager = DBFactoryProvider.GetManager();
            using (DbConnection conn = dbManager.GetConnection()) {
                conn.Open();

                // TODO: Realiza la lectura de las infracciones en la BD
            }

            throw new NotImplementedException();
        }
    }
}
