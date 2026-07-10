using System.Globalization;
using PokemonWebViewer.Code.Utils;

namespace PokemonWebViewer.Code.Managers;

// ═══════════════════════════════════════════════════════════════════════════════
//  IMPLEMENTACIÓN ALTERNATIVA — origen de datos LOCAL (archivo CSV).
//
//  ❗ NO se usa en el laboratorio. El lab exige consumir el Web Service SOAP, por eso
//     Home.razor instancia 'PokemonWSManager' y NO esta clase.
//     Se deja SOLO como referencia: muestra cómo el MISMO contrato (PokemonManager)
//     puede tener otra fuente de datos sin tocar la vista (Home.razor).
//
//  (La versión original de este archivo estaba rota: llamaba a wsManager antes de
//   inicializarlo. Aquí queda una versión limpia y correcta que lee el CSV.)
// ═══════════════════════════════════════════════════════════════════════════════
public class PokemonMemoryManager : PokemonManager
{
    private readonly List<Pokemon> pokemons;

    public PokemonMemoryManager()
    {
        pokemons = LoadPokemons();
    }

    public int GetTotalCount() => pokemons.Count;

    public Pokemon GetPokemon(int index)
    {
        if (index >= 0 && index < pokemons.Count)
            return pokemons[index];
        throw new ArgumentOutOfRangeException(nameof(index));
    }

    // Lee data/151_pokemon_data.csv (se copia al output al compilar).
    // Cabecera: nombre_pokemon,altura,peso,estado_evolutivo,nombre_tipo,descripcion_pokemon,imagen_url
    private List<Pokemon> LoadPokemons()
    {
        var list = new List<Pokemon>();

        var filePath = Path.Combine(AppDomain.CurrentDomain.BaseDirectory, "data", "151_pokemon_data.csv");
        if (!File.Exists(filePath))
            filePath = Path.Combine(Directory.GetCurrentDirectory(), "data", "151_pokemon_data.csv");
        if (!File.Exists(filePath))
            return list;

        var lines = File.ReadAllLines(filePath);
        for (int i = 1; i < lines.Length; i++)   // i = 1 → se salta la cabecera
        {
            if (string.IsNullOrWhiteSpace(lines[i])) continue;

            var col = CSVUtils.ParseCsvLine(lines[i]);   // respeta las comillas de cada campo
            if (col.Count < 7) continue;

            list.Add(new Pokemon
            {
                Nombre          = col[0],
                Altura          = double.Parse(col[1], CultureInfo.InvariantCulture),
                Peso            = double.Parse(col[2], CultureInfo.InvariantCulture),
                EstadoEvolutivo = col[3],
                Tipo            = col[4],
                Descripcion     = col[5],
                ImagenUrl       = col[6]
            });
        }
        return list;
    }
}
