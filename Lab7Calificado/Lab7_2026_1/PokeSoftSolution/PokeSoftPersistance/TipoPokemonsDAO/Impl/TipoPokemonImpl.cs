using MySql.Data.MySqlClient;
using MySqlX.XDevAPI;
using PokeSoftDBManager;
using PokeSoftModel;
using PokeSoftPersistance.TipoPokemonsDAO.DAO;
using System;
using System.Collections.Generic;
using System.Data;
using System.Text;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace PokeSoftPersistance.TipoPokemonsDAO.Impl
{
    public class TipoPokemonImpl : TipoPokemonDAO
    {
        private MySqlConnection con;
        private MySqlCommand cmd;
        private MySqlDataReader lector;

        public TipoPokemon BuscarPorId(int id)
        {
            throw new NotImplementedException();
        }

        public TipoPokemon BuscarPorNombre(string nombre)
        {
            TipoPokemon tipoPokemon = null;
            try
            {
                con = DBManager.Instance.GetConnection();
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.CommandText = "obtener_tipo_pokemon_por_nombre";
                cmd.Parameters.AddWithValue("p_nombre", nombre);

                cmd.Parameters.Add("p_id_tipo", MySqlDbType.Int32).Direction = ParameterDirection.Output;

                //en lugar de lector = cmd.ExecuteReader(); 
                object valor = cmd.ExecuteNonQuery();
                if (valor != null && valor != DBNull.Value)
                {
                    tipoPokemon = new TipoPokemon();
                    tipoPokemon.IdTipoPokemon = Int32.Parse(valor.ToString());
                    tipoPokemon.Nombre = nombre;
                }
                
                con.Close();
            }
            catch (Exception ex)
            { Console.WriteLine("Error al buscar tipo pokemon: " + ex.Message); }
            return tipoPokemon;
        }

        public int Eliminar(int id)
        {
            throw new NotImplementedException();
        }


        //        CREATE PROCEDURE insertar_tipo_pokemon(
        //    IN p_nombre VARCHAR(50),
        //    OUT p_id_tipo INT
        //)
        //BEGIN
        //    INSERT INTO tipo_pokemon(nombre)
        //    VALUES(p_nombre);

        //        SET p_id_tipo = LAST_INSERT_ID();
        //        END$$
        public int Insertar(TipoPokemon tipoPokemon)
        {
            int resultado = 0;
            try
            {
                con = DBManager.Instance.GetConnection();
                con.Open();
                cmd = new MySqlCommand();
                cmd.Connection = con;
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.CommandText = "insertar_tipo_pokemon";
                cmd.Parameters.AddWithValue("p_nombre", tipoPokemon.Nombre);
                cmd.Parameters.Add("p_id_tipo", MySqlDbType.Int32).Direction = ParameterDirection.Output;
                cmd.ExecuteNonQuery();
                tipoPokemon.IdTipoPokemon = Int32.Parse(cmd.Parameters["p_id_tipo"].Value.ToString());
                resultado = tipoPokemon.IdTipoPokemon;
                con.Close();
            }
            catch (Exception ex)
            { Console.WriteLine("Error al insertar sucursal: " + ex.Message); }
            return resultado;
        }

        public List<TipoPokemon> ListarTodos()
        {
            throw new NotImplementedException();
        }

        public int Modificar(TipoPokemon objeto)
        {
            throw new NotImplementedException();
        }
    }
}
