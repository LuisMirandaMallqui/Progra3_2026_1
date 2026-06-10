package pe.edu.pucp.pokemones.bo.implementsBO;

import pe.edu.pucp.pokemones.bo.PokemonBO;
import pe.edu.pucp.pokemones.dao.Implements.PokemonImplementsDAO;
import pe.edu.pucp.pokemones.dao.Implements.TipoPokemonImplementsDAO;
import pe.edu.pucp.pokemones.dao.PokemonDAO;
import pe.edu.pucp.pokemones.dao.TipoPokemonDAO;
import pe.edu.pucp.pokemones.model.Pokemon;
import pe.edu.pucp.pokemones.model.TipoPokemon;

import java.util.List;

public class PokemonImplementsBO implements PokemonBO {

    //Creamos referencia al DAO
    private PokemonDAO pokemonDAO;
    private TipoPokemonDAO tipoPokemonDAO;

    public PokemonImplementsBO(){
        pokemonDAO = new PokemonImplementsDAO();
        tipoPokemonDAO = new TipoPokemonImplementsDAO();
    }

    //Métodos heredados de la interfaz implementada
    @Override
    public int insertar(Pokemon elemento) {
        //Si el tipo de pokemon no existe no se puede insertar
        TipoPokemon resultadoBusquedaTipo = tipoPokemonDAO.buscarPorNombre(elemento.getStringTipoPokemon());
        if(resultadoBusquedaTipo == null){
            System.out.println("El tipo de pokemon que quieres insertar no existe");
            return 0;
        }
        elemento.setIdTipoPokemon(resultadoBusquedaTipo.getIdTipoPokemon());
        return pokemonDAO.insertar(elemento);

    }

    @Override
    public int modificar(Pokemon elemento) {
        // MEJORA: igual que insertar, resolvemos el tipo por NOMBRE (el front manda el nombre,
        // no el id). Si no se mandó nombre, respetamos el idTipoPokemon que ya trae el objeto.
        if (elemento.getStringTipoPokemon() != null && !elemento.getStringTipoPokemon().isBlank()) {
            TipoPokemon tipo = tipoPokemonDAO.buscarPorNombre(elemento.getStringTipoPokemon());
            if (tipo == null) {
                System.out.println("El tipo de pokemon que quieres asignar no existe");
                return 0;
            }
            elemento.setIdTipoPokemon(tipo.getIdTipoPokemon());
        }
        return pokemonDAO.modificar(elemento);
    }

    @Override
    public int eliminar(int idElemento) {
        return pokemonDAO.eliminar(idElemento);
    }

    @Override
    public Pokemon buscarPorId(int idElemento) {
        return pokemonDAO.buscarPorId(idElemento);
    }

    @Override
    public List<Pokemon> listarTodos() {
        return pokemonDAO.listarTodos();
    }
}
