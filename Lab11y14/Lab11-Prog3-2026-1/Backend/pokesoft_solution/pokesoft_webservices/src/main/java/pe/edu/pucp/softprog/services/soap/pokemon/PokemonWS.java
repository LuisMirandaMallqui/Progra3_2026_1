package main.java.pe.edu.pucp.softprog.services.soap.pokemon;


import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import pe.edu.pucp.pokesoft.model.Pokemon;
import pucp.edu.pe.pokesoft.business.pokemon.IPokemonBO;
import pucp.edu.pe.pokesoft.business.pokemon.PokemoBOImpl;

@WebService(
        serviceName = "PokemonWS",
        targetNamespace = "http://services.pokesoft.pucp.edu.pe/"
)
public class PokemonWS {
    private IPokemonBO pokemonBO;

    public PokemonWS(){
        pokemonBO = new PokemoBOImpl();
    }


    @WebMethod(operationName = "getTotalCount")
    public int getTotalCount(){
        int total = 0;
        try{
            total = pokemonBO.getTotalCount();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return total;
    }

    @WebMethod(operationName = "getPokemon")
    public Pokemon getPokemon(@WebParam(name = "index") int index){
        Pokemon pokemon = null;
        try{
            pokemon = pokemonBO.getPokemon(index);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return pokemon;
    }
}


