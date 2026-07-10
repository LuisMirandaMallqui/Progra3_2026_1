package pucp.edu.pe.pokesoft.business.pokemon;

import pe.edu.pucp.pokesoft.model.Pokemon;
import pe.edu.pucp.pokesoft.persistance.pokemon.dao.PokemonDAO;
import pe.edu.pucp.pokesoft.persistance.pokemon.impl.PokemonImpl;

import java.util.List;

public class PokemoBOImpl implements IPokemonBO{
    private PokemonDAO daoPokemon;

    public PokemoBOImpl(){
        daoPokemon = new PokemonImpl();
    }

    public Pokemon getPokemon(int indice) throws Exception {
        if (indice < 0) {
            throw new Exception("El indice del pokemon debe ser mayor o igual que cero.");
        }
        return daoPokemon.getPokemon(indice);
    }

    public int getTotalCount(){
        // aca no hay operacion de control porque hace un llamado a la bd y en las operaciones CRUD es donde se valida la inserción de data
        // por lo que no hace falta volver a controlar data ya validada
        return daoPokemon.getTotalCount();
    }

    //
    @Override
    public int insertar(Pokemon objeto) throws Exception {
        return 0;
    }

    @Override
    public int modificar(Pokemon objeto) throws Exception {
        return 0;
    }

    @Override
    public int eliminar(int id) throws Exception {
        return 0;
    }

    @Override
    public List<Pokemon> listarTodos() throws Exception {
        return List.of();
    }

    @Override
    public Pokemon buscarPorId(int id) throws Exception {
        return null;
    }
}
