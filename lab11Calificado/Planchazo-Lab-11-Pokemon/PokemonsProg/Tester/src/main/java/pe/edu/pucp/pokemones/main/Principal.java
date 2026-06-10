package pe.edu.pucp.pokemones.main;

import pe.edu.pucp.pokemones.bo.PokemonBO;
import pe.edu.pucp.pokemones.bo.TipoPokemonBO;
import pe.edu.pucp.pokemones.bo.implementsBO.PokemonImplementsBO;
import pe.edu.pucp.pokemones.bo.implementsBO.TipoPokemonImplementsBO;
import pe.edu.pucp.pokemones.model.Pokemon;
import pe.edu.pucp.pokemones.model.TipoPokemon;
import pe.edu.pucp.pokemones.model.enums.EstadoEvolutivo;

public class Principal {
    public static void main(String[] args) {
        // Aquí empieza la ejecución del programa

        //Variables de la BO
        PokemonBO pokemonBO = new PokemonImplementsBO();
        TipoPokemonBO tipoPokemonBO = new TipoPokemonImplementsBO();

        TipoPokemon tipoPokemon1 = new TipoPokemon();
        TipoPokemon tipoPokemon2 = new TipoPokemon();

        Pokemon pokemon1 = new Pokemon();
        Pokemon pokemon2 = new Pokemon();

        tipoPokemon1.setNombre("Electrico");
        tipoPokemon2.setNombre("Fuego");

        pokemon1.setNombre("Pikachu");
        pokemon1.setStringTipoPokemon("Electrico");
        pokemon1.setAltura(0.40);
        pokemon1.setPeso(6.00);
        pokemon1.setEstadoEvolutivo(EstadoEvolutivo.BASICO);
        pokemon1.setDescripcion("PIKA PIKA");

        pokemon2.setNombre("Charizard");
        pokemon2.setStringTipoPokemon("Agua");
        pokemon2.setAltura(1.70);
        pokemon2.setPeso(90.50);
        pokemon2.setEstadoEvolutivo(EstadoEvolutivo.FINAL);
        pokemon2.setDescripcion("FUEGOOOOO");

        tipoPokemonBO.insertar(tipoPokemon1);
        tipoPokemonBO.insertar(tipoPokemon2);

        pokemonBO.insertar(pokemon1);
        pokemonBO.insertar(pokemon2);

    }
}
