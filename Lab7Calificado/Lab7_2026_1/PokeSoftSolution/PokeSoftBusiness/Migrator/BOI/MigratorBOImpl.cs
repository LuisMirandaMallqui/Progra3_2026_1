using MySqlX.XDevAPI;
using PokeSoftBusiness.Migrator.BO;
using PokeSoftModel;
using PokeSoftPersistance.EstadosEvolutivosDAO.DAO;
using PokeSoftPersistance.EstadosEvolutivosDAO.Impl;
using PokeSoftPersistance.PokemonsDAO.DAO;
using PokeSoftPersistance.PokemonsDAO.Impl;
using PokeSoftPersistance.PokemonsRawDAO.DAO;
using PokeSoftPersistance.PokemonsRawDAO.Impl;
using PokeSoftPersistance.TipoPokemonsDAO.DAO;
using PokeSoftPersistance.TipoPokemonsDAO.Impl;
using System;
using System.Collections.Generic;
using System.Text;
using static Mysqlx.Expect.Open.Types.Condition.Types;

namespace PokeSoftBusiness.Migrator.BOI
{
    public class MigratorBOImpl : MigratorBO
    {
        public void run()
        {
            // Leer origen con su propia cadena de conexión
            PokemonRawDAO pokemonRawDAO = new PokemonRawImpl();
            List<PokemonDTO> pokemonesDTO = pokemonRawDAO.ListarTodos();
            Dictionary<string, int> tipoPokemonMap = new Dictionary<string, int>();
            PokemonDAO pokemonDAO = new PokemonImpl();
            TipoPokemonDAO tipoPokemonDAO = new TipoPokemonImpl();
            EstadoEvolutivoDAO estadoEvolutivoDAO = new EstadoEvolutivoImpl();
            foreach (PokemonDTO pokemonDTO in pokemonesDTO)
            {
                // TIPO POKEMON
                TipoPokemon tipoPokemon = null;

                if (tipoPokemonMap.ContainsKey(pokemonDTO.NombreTipo))
                {
                    // Ya existe, uso el id que guardé
                    //tipoPokemon.IdTipoPokemon = tipoPokemonMap[pokemonDTO.NombreTipo]; ;
                    //tipoPokemon.Nombre = pokemonDTO.NombreTipo;
                    tipoPokemon = tipoPokemonDAO.BuscarPorNombre(pokemonDTO.NombreTipo);
                }
                else
                {
                    tipoPokemon = tipoPokemonDAO.BuscarPorNombre(pokemonDTO.NombreTipo);
                    
                    if(tipoPokemon != null && tipoPokemon.IdTipoPokemon > 0) {
                        // Ya esta en BD (alguna ejecución previa del codigo capaz completo parcialmente todo
                        tipoPokemonMap[pokemonDTO.NombreTipo] = tipoPokemon.IdTipoPokemon;
                        Console.WriteLine($"Tipo '{pokemonDTO.NombreTipo}' encontrado en BD (ID: {tipoPokemon.IdTipoPokemon})");
                    }
                    else
                    {
                        tipoPokemon = new TipoPokemon();
                        tipoPokemon.Nombre = pokemonDTO.NombreTipo;
                        tipoPokemon.IdTipoPokemon = tipoPokemonDAO.Insertar(tipoPokemon);

                        if (tipoPokemon.IdTipoPokemon > 0)
                        {
                            tipoPokemonMap[pokemonDTO.NombreTipo] = tipoPokemon.IdTipoPokemon;
                            Console.WriteLine($"Tipo '{pokemonDTO.NombreTipo}' insertado (ID: {tipoPokemon.IdTipoPokemon})");
                        }
                    }

                    
                    // En caso este bien con dropear toda la bd y reiniciar el proceso entonces solo seria asi:
                    
                    // Primera vez, inserto y guardo el id en el mapa para evitar duplicados
                    //tipoPokemon = new TipoPokemon();
                    //tipoPokemon.Nombre = pokemonDTO.NombreTipo;
                    //tipoPokemon.IdTipoPokemon = tipoPokemonDAO.Insertar(tipoPokemon);
                    //tipoPokemonMap[tipoPokemon.Nombre] = tipoPokemon.IdTipoPokemon;
                }

                // POKEMON
                Pokemon pokemon = pokemonDAO.BuscarPorNombre(pokemonDTO.NombrePokemon);

                if (pokemon != null && pokemon.IdPokemon > 0)
                {
                    Console.WriteLine($"Pokemon '{pokemon.Nombre}' ya existe (ID: {pokemon.IdPokemon})");
                }
                else
                {
                    int idPokemon;
                    pokemon = new Pokemon();
                    pokemon.TipoPokemon = tipoPokemon;
                    pokemon.Nombre = pokemonDTO.NombrePokemon;
                    pokemon.Altura = pokemonDTO.Altura;
                    pokemon.Peso = pokemonDTO.Peso;
                    pokemon.EstadoEvolutivo = pokemonDTO.EstadoEvolutivo;
                    pokemon.Descripcion = pokemonDTO.DescripcionPokemon;

                    pokemon.IdPokemon = pokemonDAO.Insertar(pokemon);
                    // Para ver en consola:
                    if (pokemon.IdPokemon > 0)
                    {
                        Console.WriteLine($"Pokemon '{pokemonDTO.NombrePokemon}' insertado (ID: {pokemon.IdPokemon})");
                    }
                }
            }
        }
    }
}
