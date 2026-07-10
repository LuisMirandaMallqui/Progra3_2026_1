using ServiceReference1;   // Proxy SOAP autogenerado: carpeta "Connected Services/ServiceReference1/Reference.cs"

namespace PokemonWebViewer.Code.Managers;

// ═══════════════════════════════════════════════════════════════════════════════
//  PASO 3 del flujo del FRONT  (viene de Home.razor → PASO 2)
//  Esta clase es el PUENTE entre el front Blazor y el Web Service SOAP en Java.
//
//  ¿Quién llama a quién?
//    Home.razor
//      → PokemonWSManager (ESTE archivo)
//        → PokemonWSClient        (generado en Reference.cs al "Add Connected Service")
//          → [SOAP sobre HTTP]
//            → PokemonWS.java  (@WebService, targetNamespace http://services.pokesoft.pucp.edu.pe/)
//              → IPokemonBO/PokemoBOImpl  →  PokemonDAO/PokemonImpl  →  MySQL
//
//  El proxy (Reference.cs) ya te da métodos SÍNCRONOS listos para usar:
//      • int                     ws.getTotalCount()
//      • ServiceReference1.pokemon ws.getPokemon(int index)
//  El ENDPOINT (a dónde apunta) está en Reference.cs → GetEndpointAddress():
//      http://localhost:8080/pokesoft_webservices/PokemonWS
//  Si mueves el back a EC2, cambia esa URL (o regenera el Connected Service desde el WSDL).
// ═══════════════════════════════════════════════════════════════════════════════
public class PokemonWSManager : PokemonManager
{
    // Proxy SOAP. Se crea UNA sola vez, en el constructor, y se reutiliza.
    private readonly PokemonWSClient ws;

    public PokemonWSManager()
    {
        // Usa el binding (BasicHttpBinding) y el endpoint por defecto definidos en Reference.cs.
        ws = new PokemonWSClient();
    }

    // Cuántos pokémon existen. El conteo lo resuelve el SERVICIO (la BD), no el front.
    public int GetTotalCount()
    {
        return ws.getTotalCount();
    }

    // Trae UN pokémon por índice (0 .. totalCount-1).
    //
    //  ⚠ OJO AL DETALLE QUE MÁS CONFUNDE:
    //    - 'Pokemon' (mayúscula) = modelo del FRONT  → PokemonWebViewer.Code.Pokemon (props: Nombre, Altura...)
    //    - 'pokemon' (minúscula) = DTO del SERVICIO  → ServiceReference1.pokemon      (props: nombre, altura...)
    //  Son DOS clases distintas. Por eso hay que MAPEAR campo por campo del DTO al modelo.
    //  (Esta conversión era justo lo que faltaba para "mostrar los pokémon del servicio".)
    public Pokemon GetPokemon(int index)
    {
        pokemon dto = ws.getPokemon(index);   // ← llamada SOAP real al método getPokemon de Java
        if (dto == null)
            return null!;

        return new Pokemon
        {
            Nombre          = dto.nombre,
            Altura          = dto.altura,
            Peso            = dto.peso,
            EstadoEvolutivo = dto.estadoEvolutivo,
            Tipo            = dto.tipo,
            Descripcion     = dto.descripcion,
            ImagenUrl       = dto.imagenUrl
        };
    }
}
