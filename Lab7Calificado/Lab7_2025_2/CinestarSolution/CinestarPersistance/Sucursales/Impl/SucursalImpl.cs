using CinestarDBManager;
using CinestarModel.Pelicula;
using CinestarModel.Sucursal;
using CinestarPersistance.Sucursales.DAO;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data;
using System.Text;

namespace CinestarPersistance.Sucursales.Impl
{
    public class SucursalImpl : SucursalDAO
    {
        private MySqlConnection con;
        private MySqlCommand cmd;
        private MySqlDataReader lector;

        public Sucursal BuscarPorId(int id)
        {
            throw new NotImplementedException();
        }

        public int Eliminar(int id)
        {
            throw new NotImplementedException();
        }


        //OUT _id_sucursal INT,
        //IN _nombre_sucursal VARCHAR(150)
        public int Insertar(Sucursal sucursal) // PROCEDURE
        {
            int resultado = 0;
            try
            {
                con = DBManager.Instance.GetConnection();
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.CommandText = "INSERTAR_SUCURSAL";
                cmd.Parameters.Add("_id_sucursal", MySqlDbType.Int32).Direction = ParameterDirection.Output;
                cmd.Parameters.AddWithValue("_nombre_sucursal", sucursal.NombreSucursal);
                cmd.ExecuteNonQuery();
                sucursal.IdSucursal= Int32.Parse(cmd.Parameters["_id_sucursal"].Value.ToString());
                resultado = sucursal.IdSucursal;
                con.Close();
            }
            catch (Exception ex)
            { System.Console.WriteLine(ex.Message); }
            return resultado;
        }

        public List<Sucursal> ListarTodos()
        {
            throw new NotImplementedException();
        }

        public int Modificar(Sucursal objeto)
        {
            throw new NotImplementedException();
        }
    }
}
