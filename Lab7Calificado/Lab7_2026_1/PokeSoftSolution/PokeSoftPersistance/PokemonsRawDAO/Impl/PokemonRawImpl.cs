using MySql.Data.MySqlClient;
using PokeSoftDBManager;
using PokeSoftModel;
using PokeSoftPersistance.PokemonsRawDAO.DAO;
using System;
using System.Collections.Generic;
using System.Data;
using System.Text;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace PokeSoftPersistance.PokemonsRawDAO.Impl
{
    public class PokemonRawImpl : PokemonRawDAO
    {
        private MySqlConnection con;
        private MySqlCommand cmd;
        private MySqlDataReader lector;

        public PokemonDTO BuscarPorId(int id)
        {
            throw new NotImplementedException();
        }

        public int Eliminar(int id)
        {
            throw new NotImplementedException();
        }

        public int Insertar(PokemonDTO objeto)
        {
            throw new NotImplementedException();
        }

        public List<PokemonDTO> ListarTodos()
        {
            List<PokemonDTO> pokemonesDTO = null;
            con = DBManager.Instance.GetConnection();
            con.Open();
            cmd = new MySqlCommand();
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Connection = con;
            cmd.CommandText = "listar_pokemon_tipo_raw";
            lector = cmd.ExecuteReader();
            while (lector.Read())
            {
                if (pokemonesDTO == null) pokemonesDTO = new List<PokemonDTO>();
                PokemonDTO pokemonDTO = new PokemonDTO();
                if (!lector.IsDBNull("id_raw")) pokemonDTO.IdRaw= lector.GetInt32("id_raw");
                if (!lector.IsDBNull("nombre_pokemon")) pokemonDTO.NombrePokemon = lector.GetString("nombre_pokemon");
                if (!lector.IsDBNull("altura")) pokemonDTO.Altura = lector.GetDouble("altura");
                if (!lector.IsDBNull("peso")) pokemonDTO.Peso = lector.GetDouble("peso");
                if (!lector.IsDBNull("estado_evolutivo")) pokemonDTO.EstadoEvolutivo = (EstadoEvolutivo)Enum.Parse(typeof(EstadoEvolutivo), lector.GetString("estado_evolutivo"));
                if (!lector.IsDBNull("nombre_tipo")) pokemonDTO.NombreTipo = lector.GetString("nombre_tipo");
                if (!lector.IsDBNull("descripcion_pokemon")) pokemonDTO.DescripcionPokemon = lector.GetString("descripcion_pokemon");
                pokemonesDTO.Add(pokemonDTO);
            }
            con.Close();
            return pokemonesDTO;
        }

        public int Modificar(PokemonDTO objeto)
        {
            throw new NotImplementedException();
        }
    }
}
