package pucp.edu.pe.pokesoft.business.pokemon;

import pe.edu.pucp.pokesoft.model.Pokemon;
import pucp.edu.pe.pokesoft.business.bo.IBaseBO;

public interface IPokemonBO extends IBaseBO<Pokemon> {
    public Pokemon getPokemon(int indice) throws Exception;
    public int getTotalCount();
}
