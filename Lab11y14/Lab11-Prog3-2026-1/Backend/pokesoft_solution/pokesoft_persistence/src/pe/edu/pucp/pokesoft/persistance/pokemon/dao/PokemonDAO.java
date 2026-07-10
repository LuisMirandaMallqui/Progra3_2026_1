package pe.edu.pucp.pokesoft.persistance.pokemon.dao;

import pe.edu.pucp.pokesoft.model.Pokemon;
import pe.edu.pucp.pokesoft.persistance.dao.IDAO;

public interface PokemonDAO extends IDAO<Pokemon> {
    public Pokemon getPokemon(int indice);
    public int getTotalCount();
}
