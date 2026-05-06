using PokeSoftModel;
using PokeSoftPersistance.DAO;
using System;
using System.Collections.Generic;
using System.Text;

namespace PokeSoftPersistance.PokemonsDAO.DAO
{
    public interface PokemonDAO : IDAO<Pokemon>
    {
        Pokemon BuscarPorNombre(string nombre);
    }
}
