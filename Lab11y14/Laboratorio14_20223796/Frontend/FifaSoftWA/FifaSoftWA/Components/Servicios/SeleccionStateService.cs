namespace FifaSoftWA.Components.Servicios;

using CSharpRestClient;   // helper genérico get<T>()/post<T>() → archivo HttpClientUtils.cs

// ═══════════════════════════════════════════════════════════════════════════════
//  PASO 3 del flujo del FRONT  (viene de Selecciones.razor → PASO 2)
//  Servicio de estado (registrado como Singleton en Program.cs). CONSUME el REST de
//  Java y deja la lista lista para que la página solo la pinte.
//
//  ¿Quién llama a quién?
//    Selecciones.razor
//      → SeleccionStateService.CargarDatosAsync()   (ESTE archivo)
//        → HttpClientUtils<T>.get(url)              (HttpClientUtils.cs)
//          → [HTTP GET, JSON]
//            → SeleccionRS / DirectorTecnicoRS (Java) → BO → DAO → MySQL
//
//  Endpoints REST de Java (ver RestApplication → @ApplicationPath("webresources")):
//    • GET  webresources/SeleccionRS             → lista de selecciones
//    • GET  webresources/DirectorTecnicoRS/{id}  → un director técnico por id
// ═══════════════════════════════════════════════════════════════════════════════
public class SeleccionStateService
{
    // La página lee esta lista después de llamar a CargarDatosAsync().
    public List<Seleccion> Selecciones { get; private set; } = new();
    public bool EstaCargado { get; private set; } = false;

    // ⚠ Si el back corre en EC2, reemplaza "localhost" por la IP pública de la instancia.
    //   (Recuerda: sin Elastic IP, esa IP cambia cada vez que apagas/prendes la instancia.)
    private const string BASE_URL = "http://localhost:8080/fifasoft_webservices/webresources/";

    public async Task CargarDatosAsync(HttpClient http)
    {
        if (EstaCargado) return;   // se carga una sola vez (el servicio es Singleton)

        // (Opcional) Para probar la vista SIN levantar Java, descomenta la línea de abajo
        // y comenta los pasos 1 y 2:
        // Selecciones = DatosPrueba.ObtenerSelecciones();

        // ── PASO 1: traer TODAS las selecciones (SeleccionRS.listarTodos) ────────────
        //    Cada Seleccion llega con su DirectorTecnico trayendo SOLO el id.
        var clienteSelecciones = new HttpClientUtils<List<Seleccion>>();
        Selecciones = clienteSelecciones.get($"{BASE_URL}SeleccionRS") ?? new List<Seleccion>();

        // ── PASO 2: completar cada DT llamando a DirectorTecnicoRS/{id} ──────────────
        //    (SeleccionRS solo da el id del DT; aquí traemos nombre, nacionalidad, edad.)
        var clienteDT = new HttpClientUtils<DirectorTecnico>();
        foreach (var seleccion in Selecciones)
        {
            int idDT = seleccion.DirectorTecnico.IdDirectorTecnico;
            seleccion.DirectorTecnico =
                clienteDT.get($"{BASE_URL}DirectorTecnicoRS/{idDT}") ?? new DirectorTecnico();
        }

        EstaCargado = true;
        await Task.CompletedTask;   // HttpClientUtils es síncrono; conservamos la firma async
    }
}
