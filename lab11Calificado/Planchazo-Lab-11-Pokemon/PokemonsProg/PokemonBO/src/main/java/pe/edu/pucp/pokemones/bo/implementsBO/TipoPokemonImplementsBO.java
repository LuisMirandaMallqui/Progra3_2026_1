package pe.edu.pucp.pokemones.bo.implementsBO;

import pe.edu.pucp.pokemones.bo.TipoPokemonBO;
import pe.edu.pucp.pokemones.dao.Implements.TipoPokemonImplementsDAO;
import pe.edu.pucp.pokemones.dao.TipoPokemonDAO;
import pe.edu.pucp.pokemones.model.TipoPokemon;

import java.util.List;

public class TipoPokemonImplementsBO implements TipoPokemonBO {

    //Creamos la referencia al DAO
    private TipoPokemonDAO tipoPokemonDAO;

    public TipoPokemonImplementsBO(){
        tipoPokemonDAO = new TipoPokemonImplementsDAO();
    }

    //Métodos heredados de la interfaz que ha implementado
    @Override
    public int insertar(TipoPokemon elemento) {

        //Para que no se inserte repetido
        TipoPokemon resultadoBusqueda = tipoPokemonDAO.buscarPorNombre(elemento.getNombre());
        if(resultadoBusqueda != null) {
            System.out.println("El tipo de pokemon que quieres insertar ya existe");
            return 0;
        }
        return tipoPokemonDAO.insertar(elemento);
    }

    @Override
    public int modificar(TipoPokemon elemento) {
        return tipoPokemonDAO.modificar(elemento);
    }

    @Override
    public int eliminar(int idElemento) {
        return tipoPokemonDAO.eliminar(idElemento);
    }

    @Override
    public TipoPokemon buscarPorId(int idElemento) {
        return tipoPokemonDAO.buscarPorId(idElemento);
    }

    @Override
    public List<TipoPokemon> listarTodos() {
        return tipoPokemonDAO.listarTodos();
    }

    @Override
    public TipoPokemon buscarPorNombre(String nombre) {
        return tipoPokemonDAO.buscarPorNombre(nombre);
    }
}
