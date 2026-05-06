using CinestarDBManager;
using CinestarModel.Cliente;
using CinestarModel.Pelicula;
using CinestarPersistance.Peliculas.DAO;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data;
using System.Text;

namespace CinestarPersistance.Peliculas.Impl
{
    public class PeliculaImpl : PeliculaDAO
    {
        private MySqlConnection con;
        private MySqlCommand cmd;
        private MySqlDataReader lector;

        // SP: INSERTAR_PELICULA(OUT _id_pelicula, IN _nombre_pelicula, IN _genero_pelicula)
        public int Insertar(Pelicula pelicula)
        {
            int resultado = 0;
            try
            {
                con = DBManager.Instance.GetConnection();
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.CommandText = "INSERTAR_PELICULA";
                cmd.Parameters.Add("_id_pelicula", MySqlDbType.Int32).Direction = ParameterDirection.Output;
                cmd.Parameters.AddWithValue("_nombre_pelicula", pelicula.NombrePelicula);
                cmd.Parameters.AddWithValue("_genero_pelicula", pelicula.GeneroPelicula.ToString().Replace("_", " "));
                cmd.ExecuteNonQuery();
                pelicula.IdPelicula = Int32.Parse(cmd.Parameters["_id_pelicula"].Value.ToString());
                resultado = pelicula.IdPelicula;
                con.Close();
            }
            catch (Exception ex)
            { Console.WriteLine("Error al insertar pelicula: " + ex.Message); }
            return resultado;
        }

        // SP: MODIFICAR_PELICULA(IN _id_pelicula, IN _nombre_pelicula, IN _genero_pelicula)
        public int Modificar(Pelicula pelicula)
        {
            int resultado = 0;
            try
            {
                con = DBManager.Instance.GetConnection();
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.CommandText = "MODIFICAR_PELICULA";
                cmd.Parameters.AddWithValue("_id_pelicula", pelicula.IdPelicula);
                cmd.Parameters.AddWithValue("_nombre_pelicula", pelicula.NombrePelicula);
                cmd.Parameters.AddWithValue("_genero_pelicula", pelicula.GeneroPelicula.ToString().Replace("_", " "));
                resultado = cmd.ExecuteNonQuery();
                con.Close();
            }
            catch (Exception ex)
            { Console.WriteLine("Error al modificar pelicula: " + ex.Message); }
            return resultado;
        }

        // SP: ELIMINAR_PELICULA(IN _id_pelicula)
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
                cmd.CommandText = "ELIMINAR_PELICULA";
                cmd.Parameters.AddWithValue("_id_pelicula", id);
                resultado = cmd.ExecuteNonQuery();
                con.Close();
            }
            catch (Exception ex)
            { Console.WriteLine("Error al eliminar pelicula: " + ex.Message); }
            return resultado;
        }

        // SP: BUSCAR_PELICULA_POR_ID(IN _id_pelicula)
        public Pelicula BuscarPorId(int id)
        {
            Pelicula pelicula = null;
            try
            {
                con = DBManager.Instance.GetConnection();
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.CommandText = "BUSCAR_PELICULA_POR_ID";
                cmd.Parameters.AddWithValue("_id_pelicula", id);
                lector = cmd.ExecuteReader();
                if (lector.Read())
                {
                    pelicula = new Pelicula();
                    pelicula.IdPelicula = lector.GetInt32("id_pelicula");
                    pelicula.NombrePelicula = lector.GetString("nombre_pelicula");
                    string generoDb = lector.GetString("genero_pelicula");
                    pelicula.GeneroPelicula = (GeneroPelicula)Enum.Parse(
                        typeof(GeneroPelicula), generoDb.Replace(" ", "_"));
                }
                con.Close();
            }
            catch (Exception ex)
            { Console.WriteLine("Error al buscar pelicula: " + ex.Message); }
            return pelicula;
        }

        // SP: LISTAR_PELICULAS_TODAS()
        public List<Pelicula> ListarTodos()
        {
            List<Pelicula> lista = null;
            try
            {
                con = DBManager.Instance.GetConnection();
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.CommandText = "LISTAR_PELICULAS_TODAS";
                lector = cmd.ExecuteReader();
                while (lector.Read())
                {
                    if (lista == null) lista = new List<Pelicula>();
                    Pelicula pelicula = new Pelicula();
                    pelicula.IdPelicula = lector.GetInt32("id_pelicula");
                    pelicula.NombrePelicula = lector.GetString("nombre_pelicula");
                    string generoDb = lector.GetString("genero_pelicula");
                    pelicula.GeneroPelicula = (GeneroPelicula)Enum.Parse(
                        typeof(GeneroPelicula), generoDb.Replace(" ", "_"));
                    lista.Add(pelicula);
                }
                con.Close();
            }
            catch (Exception ex)
            { Console.WriteLine("Error al listar peliculas: " + ex.Message); }
            return lista;
        }
    }
}
