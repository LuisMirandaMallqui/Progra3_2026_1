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

        public Pelicula BuscarPorId(int id)
        {
            throw new NotImplementedException();
        }

        public int Eliminar(int id)
        {
            throw new NotImplementedException();
        }

        //    CREATE PROCEDURE INSERTAR_PELICULA(
        //    OUT _id_pelicula INT,
        //    IN _nombre_pelicula VARCHAR(150),
        //    IN _genero_pelicula ENUM('ACCION','ANIMACION','DRAMA','CIENCIA FICCION')
        //    )
        public int Insertar(Pelicula pelicula) // PROCEDURE
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
            { System.Console.WriteLine(ex.Message); }
            return resultado;
        }

        public List<Pelicula> ListarTodos()
        {
            throw new NotImplementedException();
        }

        public int Modificar(Pelicula objeto)
        {
            throw new NotImplementedException();
        }
    }
}
