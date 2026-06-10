package pe.edu.pucp.pokemones.bo;

import pe.edu.pucp.pokemones.bo.base.BaseBO;
import pe.edu.pucp.pokemones.model.TipoPokemon;

public interface TipoPokemonBO extends BaseBO<TipoPokemon> {
    TipoPokemon buscarPorNombre(String nombre);
}
