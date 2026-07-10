package main.java.pe.edu.pucp.softprog.services.rest.pokemon;


import jakarta.jws.WebParam;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pe.edu.pucp.pokesoft.model.Pokemon;
import pucp.edu.pe.pokesoft.business.pokemon.IPokemonBO;
import pucp.edu.pe.pokesoft.business.pokemon.PokemoBOImpl;

@Path("PokemonRS")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PokemonRS {
    private IPokemonBO pokemonBO;

    public PokemonRS(){
        pokemonBO = new PokemoBOImpl();
    }

    @GET
    @Path("/obtenerPokemon/{index}")
    public Pokemon getPokemon(@PathParam("index") int index){
        Pokemon pokemon = null;
        try{
            pokemon = pokemonBO.getPokemon(index);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return pokemon;
    }

    @GET
    @Path("/obtenerTotal")
    public int getTotalCount(){
        int total = 0;
        try{
            total = pokemonBO.getTotalCount();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return total;
    }
}
