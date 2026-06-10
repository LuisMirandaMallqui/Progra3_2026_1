package pe.edu.pucp.pokemones.dao;

import pe.edu.pucp.pokemones.dao.base.BaseDAO;
import pe.edu.pucp.pokemones.model.TipoPokemon;

public interface TipoPokemonDAO extends BaseDAO<TipoPokemon> {
    TipoPokemon buscarPorNombre(String nombre);

}
