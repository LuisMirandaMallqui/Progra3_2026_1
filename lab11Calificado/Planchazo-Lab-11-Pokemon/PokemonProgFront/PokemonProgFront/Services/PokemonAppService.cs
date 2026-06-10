using PokemonProgFront.Models;
using PokemonService;

namespace PokemonProgFront.Services;

public class PokemonAppService
{
    private readonly PokemonWSClient clienteSoap;

    public PokemonAppService()
    {
        clienteSoap = new PokemonWSClient();
    }

    public async Task<List<PokemonProgFront.Models.Pokemon>> Listar()
    {
        var respuesta = await clienteSoap.listarPokemonesAsync();
        var listaSoap = respuesta.@return;

        if (listaSoap == null)
        {
            return new List<PokemonProgFront.Models.Pokemon>();
        }

        return listaSoap.Select(p => ConvertirDesdeSoap(p)).ToList();
    }

    public async Task<int> Insertar(PokemonProgFront.Models.Pokemon pokemon)
    {
        var pokemonSoap = ConvertirHaciaSoap(pokemon);

        var respuesta = await clienteSoap.insertarPokemonAsync(pokemonSoap);

        return respuesta.@return;
    }

    public async Task<List<PokemonProgFront.Models.Pokemon>> Filtrar(
        string? nombre,
        int? idTipoPokemon,
        EstadoEvolutivo? estadoEvolutivo
    )
    {
        string nombreFiltro = nombre ?? string.Empty;
        int idTipoFiltro = idTipoPokemon ?? 0;
        string estadoFiltro = estadoEvolutivo.HasValue
            ? estadoEvolutivo.Value.ToString()
            : string.Empty;

        var respuesta = await clienteSoap.filtrarPokemonesAsync(
            nombreFiltro,
            idTipoFiltro,
            estadoFiltro
        );

        var listaSoap = respuesta.@return;

        if (listaSoap == null)
        {
            return new List<PokemonProgFront.Models.Pokemon>();
        }

        return listaSoap.Select(p => ConvertirDesdeSoap(p)).ToList();
    }

    private PokemonProgFront.Models.Pokemon ConvertirDesdeSoap(PokemonService.pokemon p)
    {
        return new PokemonProgFront.Models.Pokemon
        {
            IdPokemon = p.idPokemon,
            IdTipoPokemon = p.idTipoPokemon,
            StringTipoPokemon = p.stringTipoPokemon ?? string.Empty,
            Nombre = p.nombre ?? string.Empty,
            Altura = Convert.ToDecimal(p.altura),
            Peso = Convert.ToDecimal(p.peso),
            EstadoEvolutivo = Enum.Parse<EstadoEvolutivo>(p.estadoEvolutivo.ToString()),
            Descripcion = p.descripcion ?? string.Empty
        };
    }

    private PokemonService.pokemon ConvertirHaciaSoap(PokemonProgFront.Models.Pokemon p)
    {
        return new PokemonService.pokemon
        {
            idPokemon = p.IdPokemon,

            idTipoPokemon = 0,

            stringTipoPokemon = p.StringTipoPokemon,

            nombre = p.Nombre,

            altura = Convert.ToDouble(p.Altura),

            peso = Convert.ToDouble(p.Peso),

            estadoEvolutivo = Enum.Parse<PokemonService.estadoEvolutivo>(
                p.EstadoEvolutivo.ToString()
            ),

            estadoEvolutivoSpecified = true,

            descripcion = p.Descripcion
        };
    }
}