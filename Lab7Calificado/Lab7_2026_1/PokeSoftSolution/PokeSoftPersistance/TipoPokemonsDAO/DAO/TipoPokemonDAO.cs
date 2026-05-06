using PokeSoftModel;
using PokeSoftPersistance.DAO;
using System;
using System.Collections.Generic;
using System.Text;

namespace PokeSoftPersistance.TipoPokemonsDAO.DAO
{
    public interface TipoPokemonDAO : IDAO<TipoPokemon>
    {
        TipoPokemon BuscarPorNombre(String nombre);
    }
}
