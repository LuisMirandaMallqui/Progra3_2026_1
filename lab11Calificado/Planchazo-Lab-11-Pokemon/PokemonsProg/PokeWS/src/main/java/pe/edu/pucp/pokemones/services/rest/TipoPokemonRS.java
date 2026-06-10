package pe.edu.pucp.pokemones.services.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pe.edu.pucp.pokemones.bo.TipoPokemonBO;
import pe.edu.pucp.pokemones.bo.implementsBO.TipoPokemonImplementsBO;
import pe.edu.pucp.pokemones.model.TipoPokemon;

import java.util.List;

// AGREGADO EN LA MEJORA: servicio REST de TipoPokemon (se prueba en Postman).
//   GET    /TipoPokemonRS                 -> listar
//   GET    /TipoPokemonRS/{id}            -> por id
//   GET    /TipoPokemonRS/buscar?nombre=  -> por nombre
//   POST   /TipoPokemonRS (body JSON)     -> insertar (devuelve id; 0 si ya existe)
//   PUT    /TipoPokemonRS (body JSON)     -> modificar
//   DELETE /TipoPokemonRS/{id}            -> eliminar
@Path("TipoPokemonRS")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipoPokemonRS {

    private final TipoPokemonBO tipoPokemonBO;

    public TipoPokemonRS() {
        this.tipoPokemonBO = new TipoPokemonImplementsBO();
    }

    @GET
    public List<TipoPokemon> listarTipos() {
        return tipoPokemonBO.listarTodos();
    }

    @GET
    @Path("{id}")
    public TipoPokemon buscarPorId(@PathParam("id") int id) {
        return tipoPokemonBO.buscarPorId(id);
    }

    @GET
    @Path("buscar")
    public TipoPokemon buscarPorNombre(@QueryParam("nombre") String nombre) {
        return tipoPokemonBO.buscarPorNombre(nombre);
    }

    @POST
    public int insertarTipo(TipoPokemon tipo) {
        return tipoPokemonBO.insertar(tipo);
    }

    @PUT
    public int modificarTipo(TipoPokemon tipo) {
        return tipoPokemonBO.modificar(tipo);
    }

    @DELETE
    @Path("{id}")
    public int eliminarTipo(@PathParam("id") int id) {
        return tipoPokemonBO.eliminar(id);
    }
}
