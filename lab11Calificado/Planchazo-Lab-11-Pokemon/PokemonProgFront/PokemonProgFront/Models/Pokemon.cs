namespace PokemonProgFront.Models;

public class Pokemon
{
    public int IdPokemon { get; set; }
    public int IdTipoPokemon { get; set; }
    public string StringTipoPokemon { get; set; } = string.Empty;
    public string Nombre { get; set; } = string.Empty;
    public decimal Altura { get; set; }
    public decimal Peso { get; set; }
    public EstadoEvolutivo EstadoEvolutivo { get; set; }
    public string Descripcion { get; set; } = string.Empty;
}