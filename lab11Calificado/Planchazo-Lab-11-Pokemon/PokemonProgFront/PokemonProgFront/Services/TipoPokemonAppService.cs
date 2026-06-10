using PokemonProgFront.Models;
using TipoPokemonService;

namespace PokemonProgFront.Services;

public class TipoPokemonAppService
{
    private readonly TipoPokemonWSClient clienteSoap;

    public TipoPokemonAppService()
    {
        clienteSoap = new TipoPokemonWSClient();
    }

    public async Task<List<TipoPokemon>> Listar()
    {
        var respuesta = await clienteSoap.listarTiposPokemonAsync();
        var listaSoap = respuesta.@return;

        if (listaSoap == null)
        {
            return new List<TipoPokemon>();
        }

        return listaSoap.Select(t => new TipoPokemon
        {
            IdTipoPokemon = t.idTipoPokemon,
            Nombre = t.nombre ?? string.Empty
        }).ToList();
    }

    public async Task<TipoPokemon?> BuscarPorId(int idTipoPokemon)
    {
        var respuesta = await clienteSoap.buscarTipoPokemonPorIdAsync(idTipoPokemon);
        var tipoSoap = respuesta.@return;

        if (tipoSoap == null)
        {
            return null;
        }

        return new TipoPokemon
        {
            IdTipoPokemon = tipoSoap.idTipoPokemon,
            Nombre = tipoSoap.nombre ?? string.Empty
        };
    }

    public async Task<int> Insertar(TipoPokemon tipoPokemon)
    {
        var tipoSoap = new TipoPokemonService.tipoPokemon
        {
            idTipoPokemon = tipoPokemon.IdTipoPokemon,
            nombre = tipoPokemon.Nombre
        };

        var respuesta = await clienteSoap.insertarTipoPokemonAsync(tipoSoap);

        return respuesta.@return;
    }
}