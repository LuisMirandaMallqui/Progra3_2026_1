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

        // SP: INSERTAR_SUCURSAL(OUT _id_sucursal, IN _nombre_sucursal)
        public int Insertar(Sucursal sucursal)
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
                sucursal.IdSucursal = Int32.Parse(cmd.Parameters["_id_sucursal"].Value.ToString());
                resultado = sucursal.IdSucursal;
                con.Close();
            }
            catch (Exception ex)
            { Console.WriteLine("Error al insertar sucursal: " + ex.Message); }
            return resultado;
        }

        // SP: MODIFICAR_SUCURSAL(IN _id_sucursal, IN _nombre_sucursal)
        public int Modificar(Sucursal sucursal)
        {
            int resultado = 0;
            try
            {
                con = DBManager.Instance.GetConnection();
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.CommandText = "MODIFICAR_SUCURSAL";
                cmd.Parameters.AddWithValue("_id_sucursal", sucursal.IdSucursal);
                cmd.Parameters.AddWithValue("_nombre_sucursal", sucursal.NombreSucursal);
                resultado = cmd.ExecuteNonQuery();
                con.Close();
            }
            catch (Exception ex)
            { Console.WriteLine("Error al modificar sucursal: " + ex.Message); }
            return resultado;
        }

        // SP: ELIMINAR_SUCURSAL(IN _id_sucursal)
        public int Eliminar(int id)
        {
            int resultado = 0;
            try
            {
                con = DBManager.Instance.GetConnection();
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.CommandText = "ELIMINAR_SUCURSAL";
                cmd.Parameters.AddWithValue("_id_sucursal", id);
                resultado = cmd.ExecuteNonQuery();
                con.Close();
            }
            catch (Exception ex)
            { Console.WriteLine("Error al eliminar sucursal: " + ex.Message); }
            return resultado;
        }

        // SP: BUSCAR_SUCURSAL_POR_ID(IN _id_sucursal)
        public Sucursal BuscarPorId(int id)
        {
            Sucursal sucursal = null;
            try
            {
                con = DBManager.Instance.GetConnection();
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.CommandText = "BUSCAR_SUCURSAL_POR_ID";
                cmd.Parameters.AddWithValue("_id_sucursal", id);
                lector = cmd.ExecuteReader();
                if (lector.Read())
                {
                    sucursal = new Sucursal();
                    sucursal.IdSucursal = lector.GetInt32("id_sucursal");
                    sucursal.NombreSucursal = lector.GetString("nombre_sucursal");
                }
                con.Close();
            }
            catch (Exception ex)
            { Console.WriteLine("Error al buscar sucursal: " + ex.Message); }
            return sucursal;
        }

        // SP: LISTAR_SUCURSALES_TODAS()
        public List<Sucursal> ListarTodos()
        {
            List<Sucursal> lista = null;
            try
            {
                con = DBManager.Instance.GetConnection();
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.CommandText = "LISTAR_SUCURSALES_TODAS";
                lector = cmd.ExecuteReader();
                while (lector.Read())
                {
                    if (lista == null) lista = new List<Sucursal>();
                    Sucursal sucursal = new Sucursal();
                    sucursal.IdSucursal = lector.GetInt32("id_sucursal");
                    sucursal.NombreSucursal = lector.GetString("nombre_sucursal");
                    lista.Add(sucursal);
                }
                con.Close();
            }
            catch (Exception ex)
            { Console.WriteLine("Error al listar sucursales: " + ex.Message); }
            return lista;
        }
    }
}
