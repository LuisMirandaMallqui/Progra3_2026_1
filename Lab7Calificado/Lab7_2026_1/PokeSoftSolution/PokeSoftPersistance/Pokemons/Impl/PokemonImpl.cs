using MySql.Data.MySqlClient;
using MySqlX.XDevAPI;
using PokeSoftDBManager;
using PokeSoftModel;
using PokeSoftPersistance.PokemonsDAO.DAO;
using System;
using System.Collections.Generic;
using System.Data;
using System.Text;

namespace PokeSoftPersistance.PokemonsDAO.Impl
{
    public class PokemonImpl : PokemonDAO
    {
        private MySqlConnection con;
        private MySqlCommand cmd;
        private MySqlDataReader lector;
        public Pokemon BuscarPorId(int id)
        {
            throw new NotImplementedException();
        }

        public Pokemon BuscarPorNombre(string nombre)
        {
            Pokemon pokemon = null;
            try
            {
                con = DBManager.Instance.GetConnection();
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.CommandText = "obtener_pokemon_por_nombre";
                cmd.Parameters.AddWithValue("p_nombre", nombre);

                cmd.Parameters.Add("p_id_tipo", MySqlDbType.Int32).Direction = ParameterDirection.Output;

                object valor = cmd.ExecuteNonQuery();
                if (valor != null && valor != DBNull.Value)
                {
                    pokemon = new Pokemon();
                    pokemon.IdPokemon = Int32.Parse(valor.ToString());
                    pokemon.Nombre = nombre;
                }

                con.Close();
            }
            catch (Exception ex)
            { Console.WriteLine("El tipo pokemon no se encontro: " + ex.Message); }
            return pokemon;
        }

        public int Eliminar(int id)
        {
            throw new NotImplementedException();
        }

        //CREATE PROCEDURE insertar_pokemon(
        //OUT p_id_pokemon INT,
        //IN p_nombre VARCHAR(80),
        //IN p_fid_tipo INT,
        //IN p_altura DECIMAL(5, 2),
        //IN p_peso DECIMAL(5,2),
        //IN p_estado_evolutivo ENUM('BASICO','INTERMEDIO','FINAL'),
        //IN p_descripcion VARCHAR(255)
        public int Insertar(Pokemon objeto)
        {
            int resultado = 0;
            try
            {
                con = DBManager.Instance.GetConnection();
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.CommandText = "insertar_pokemon";
                cmd.Parameters.Add("p_id_pokemon", MySqlDbType.Int32).Direction = ParameterDirection.Output;
                cmd.Parameters.AddWithValue("p_nombre", objeto.Nombre);
                cmd.Parameters.AddWithValue("p_fid_tipo", objeto.TipoPokemon.IdTipoPokemon); //
                cmd.Parameters.AddWithValue("p_altura", objeto.Altura);
                cmd.Parameters.AddWithValue("p_peso", objeto.Peso);
                cmd.Parameters.AddWithValue("p_estado_evolutivo", objeto.EstadoEvolutivo); //
                cmd.Parameters.AddWithValue("p_descripcion", objeto.Descripcion);
                cmd.ExecuteNonQuery();
                objeto.IdPokemon = Int32.Parse(cmd.Parameters["p_id_pokemon"].Value.ToString());
                resultado = objeto.IdPokemon;
                con.Close();
            }
            catch (Exception ex)
            { Console.WriteLine("Error al insertar pokemon: " + ex.Message); }
            return resultado;
        }

        public List<Pokemon> ListarTodos()
        {
            throw new NotImplementedException();
        }

        public int Modificar(Pokemon objeto)
        {
            throw new NotImplementedException();
        }
    }
}
