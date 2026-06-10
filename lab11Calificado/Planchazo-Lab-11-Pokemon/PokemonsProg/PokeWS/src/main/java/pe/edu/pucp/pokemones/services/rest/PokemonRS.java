package pe.edu.pucp.pokemones.services.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pe.edu.pucp.pokemones.bo.PokemonBO;
import pe.edu.pucp.pokemones.bo.implementsBO.PokemonImplementsBO;
import pe.edu.pucp.pokemones.model.Pokemon;
import pe.edu.pucp.pokemones.model.enums.EstadoEvolutivo;

import java.util.ArrayList;
import java.util.List;

// ============================================================================
// AGREGADO EN LA MEJORA: servicio REST de Pokemon (mismo BO que el SOAP).
// Se prueba en Postman. Rutas (base /PokeWS-1.0-SNAPSHOT/webresources):
//   GET    /PokemonRS                       -> listar
//   GET    /PokemonRS/{id}                   -> por id
//   GET    /PokemonRS/filtrar?nombre=&idTipo=&estado=  -> filtrar
//   POST   /PokemonRS   (body JSON)          -> insertar (devuelve id)
//   PUT    /PokemonRS   (body JSON)          -> modificar (filas afectadas)
//   DELETE /PokemonRS/{id}                   -> eliminar
// JSON-B serializa el enum EstadoEvolutivo como texto ("BASICO") y usa los
// nombres de getters: idPokemon, idTipoPokemon, stringTipoPokemon, nombre, etc.
//
// PROBAR EN POSTMAN (REST se prueba aquí, NO SOAP):
//   POST /PokemonRS -> Body -> raw -> JSON, p.ej.:
//     { "stringTipoPokemon":"ELECTRICO", "nombre":"Pikachu", "altura":0.4,
//       "peso":6.0, "estadoEvolutivo":"BASICO", "descripcion":"raton electrico" }
//   415 = falta Body raw/JSON ; 404 = ruta/WAR ; 500 = revisar log (BD).
// ============================================================================
@Path("PokemonRS")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PokemonRS {

    private final PokemonBO pokemonBO;

    public PokemonRS() {
        this.pokemonBO = new PokemonImplementsBO();
    }

    @GET
    public List<Pokemon> listarPokemones() {
        return pokemonBO.listarTodos();
    }

    @GET
    @Path("{id}")
    public Pokemon buscarPokemonPorId(@PathParam("id") int id) {
        return pokemonBO.buscarPorId(id);
    }

    // Filtro por nombre / tipo / estado (mismos criterios que el SOAP).
    @GET
    @Path("filtrar")
    public List<Pokemon> filtrarPokemones(@QueryParam("nombre") String nombre,
                                          @QueryParam("idTipo") int idTipo,
                                          @QueryParam("estado") String estadoTexto) {
        List<Pokemon> todos = pokemonBO.listarTodos();
        List<Pokemon> resultado = new ArrayList<>();
        if (todos == null) return resultado;

        EstadoEvolutivo estado = null;
        if (estadoTexto != null && !estadoTexto.isBlank())
            estado = EstadoEvolutivo.valueOf(estadoTexto);

        for (Pokemon p : todos) {
            boolean cumple = true;
            if (nombre != null && !nombre.isBlank())
                cumple = p.getNombre().toLowerCase().contains(nombre.toLowerCase());
            if (cumple && idTipo > 0) cumple = p.getIdTipoPokemon() == idTipo;
            if (cumple && estado != null) cumple = p.getEstadoEvolutivo() == estado;
            if (cumple) resultado.add(p);
        }
        return resultado;
    }

    @POST
    public int insertarPokemon(Pokemon pokemon) {     // el objeto llega como JSON en el body
        return pokemonBO.insertar(pokemon);           // el BO resuelve el tipo por nombre y valida
    }

    @PUT
    public int modificarPokemon(Pokemon pokemon) {
        return pokemonBO.modificar(pokemon);
    }

    @DELETE
    @Path("{id}")
    public int eliminarPokemon(@PathParam("id") int id) {
        return pokemonBO.eliminar(id);
    }
}
