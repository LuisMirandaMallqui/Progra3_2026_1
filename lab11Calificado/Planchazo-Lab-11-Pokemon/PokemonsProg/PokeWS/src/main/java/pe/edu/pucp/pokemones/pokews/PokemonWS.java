package pe.edu.pucp.pokemones.pokews;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.util.ArrayList;
import java.util.List;

import pe.edu.pucp.pokemones.bo.PokemonBO;
import pe.edu.pucp.pokemones.bo.implementsBO.PokemonImplementsBO;
import pe.edu.pucp.pokemones.model.Pokemon;
import pe.edu.pucp.pokemones.model.enums.EstadoEvolutivo;

// NOTA: se QUITÓ @WebParam para que el WSDL use arg0/arg1/... y coincida con el proxy
// (Reference.cs) que ya tienes generado en el front. Así no necesitas regenerar el
// Connected Service: basta recompilar y redeployar este WAR.
// (Si algún día regeneras el proxy desde cero, puedes volver a poner @WebParam(name="..").)
@WebService(
        serviceName = "PokemonWS",
        targetNamespace = "http://services.pokemones.pucp.edu.pe/"
)
public class PokemonWS {

    private final PokemonBO pokemonBO;

    public PokemonWS() {
        this.pokemonBO = new PokemonImplementsBO();
    }

    @WebMethod(operationName = "listarPokemones")
    public List<Pokemon> listarPokemones() {
        return pokemonBO.listarTodos();
    }

    @WebMethod(operationName = "buscarPokemonPorId")
    public Pokemon buscarPokemonPorId(int idPokemon) {
        return pokemonBO.buscarPorId(idPokemon);
    }

    @WebMethod(operationName = "insertarPokemon")
    public int insertarPokemon(Pokemon pokemon) {
        return pokemonBO.insertar(pokemon);
    }

    @WebMethod(operationName = "filtrarPokemones")
    public List<Pokemon> filtrarPokemones(String nombre, int idTipoPokemon, String estadoEvolutivoTexto) {
        List<Pokemon> lista = pokemonBO.listarTodos();
        List<Pokemon> resultado = new ArrayList<>();

        EstadoEvolutivo estadoEvolutivo = null;
        if (estadoEvolutivoTexto != null && !estadoEvolutivoTexto.isBlank()) {
            estadoEvolutivo = EstadoEvolutivo.valueOf(estadoEvolutivoTexto);
        }

        for (Pokemon pokemon : lista) {
            boolean cumple = true;
            if (nombre != null && !nombre.isBlank()) {
                cumple = pokemon.getNombre().toLowerCase().contains(nombre.toLowerCase());
            }
            if (cumple && idTipoPokemon > 0) {
                cumple = pokemon.getIdTipoPokemon() == idTipoPokemon;
            }
            if (cumple && estadoEvolutivo != null) {
                cumple = pokemon.getEstadoEvolutivo() == estadoEvolutivo;
            }
            if (cumple) {
                resultado.add(pokemon);
            }
        }
        return resultado;
    }

    // AGREGADO EN LA MEJORA: CRUD completo por SOAP. (El proxy actual aún no los tiene;
    // aparecerán cuando regeneres el Connected Service. No estorban a lo existente.)
    @WebMethod(operationName = "modificarPokemon")
    public int modificarPokemon(Pokemon pokemon) {
        return pokemonBO.modificar(pokemon);
    }

    @WebMethod(operationName = "eliminarPokemon")
    public int eliminarPokemon(int idPokemon) {
        return pokemonBO.eliminar(idPokemon);
    }
}
