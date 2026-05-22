// =====================================================================
// Program.cs — PUNTO DE ENTRADA de la aplicación Blazor Server
// =====================================================================
// Aquí se configuran 3 cosas fundamentales:
// 1. INYECCION DE DEPENDENCIAS (DI) — registrar servicios BO
// 2. INICIALIZACION DEL DBMANAGER — Singleton con cadena de conexión
// 3. PIPELINE HTTP — middleware, rutas, Blazor
// =====================================================================

// === IMPORTS ===
// Se importan las INTERFACES (BOI) y las IMPLEMENTACIONES (BO)
// de la capa de negocio para poder registrarlas en el contenedor DI
using TestSoftBusiness.Alumnos.BO;
using TestSoftBusiness.Alumnos.BOI;
using TestSoftBusiness.Preguntas.BO;
using TestSoftBusiness.Preguntas.BOI;
using TestSoftBusiness.Examenes.BO;
using TestSoftBusiness.Examenes.BOI;
using TestSoftWA.Components;
using SoftProgDBManager;  // namespace del DBManager (definido por el profe)

var builder = WebApplication.CreateBuilder(args);

// === BLAZOR SERVER ===
builder.Services.AddRazorComponents()
    .AddInteractiveServerComponents();

// =====================================================================
// 1. INYECCION DE DEPENDENCIAS (Dependency Injection - DI)
// =====================================================================
// ¿QUE ES?
//   Es un patrón donde NO instancias las clases directamente (new AlumnoBOImpl())
//   sino que le dices al framework "cuando alguien pida IAlumnoBO, dale un AlumnoBOImpl"
//   y el framework se encarga de crear y administrar la instancia.
//
// ¿POR QUE?
//   - Desacoplamiento: las páginas Razor solo conocen la INTERFAZ, no la clase
//   - Testabilidad: puedes cambiar la implementación sin tocar las páginas
//   - Ciclo de vida controlado: Scoped = una instancia por request
//
// SINTAXIS: builder.Services.AddScoped<IInterfaz, Implementacion>();
//
// TIPOS DE CICLO DE VIDA:
//   - AddScoped:     1 instancia por solicitud HTTP (lo más común en web)
//   - AddTransient:  1 instancia NUEVA cada vez que se pide
//   - AddSingleton:  1 instancia GLOBAL para toda la aplicación
//
// EN EL EXAMEN: casi siempre es AddScoped para los BO
// =====================================================================
builder.Services.AddScoped<IAlumnoBO, AlumnoBOImpl>();      // IAlumnoBO → AlumnoBOImpl
builder.Services.AddScoped<IPreguntaBO, PreguntaBOImpl>();  // IPreguntaBO → PreguntaBOImpl
builder.Services.AddScoped<IExamenBO, ExamenBOImpl>();      // IExamenBO → ExamenBOImpl

// =====================================================================
// 2. INICIALIZACION DEL DBMANAGER (Patron Singleton)
// =====================================================================
//
// FLUJO:
//   appsettings.json → IConfiguration → lee tipoBD + connectionString
//   → DBManager.Initialize(connectionString, tipoMotorBD)
//   → A partir de ahí: DBManager.Instance está disponible globalmente
// =====================================================================

// Lee la configuración del archivo appsettings.json
IConfiguration configuration = new ConfigurationBuilder()
            .SetBasePath(Directory.GetCurrentDirectory())
            .AddJsonFile("appsettings.json")
            .Build();

// Lee qué motor de BD usar (mysql o mssql)
string tipoMotorBD = configuration.GetConnectionString("tipoBD")
    ?? throw new Exception("No se encontro la configuracion tipoBD en appsettings.json.");

// Lee la cadena de conexión correspondiente al motor
string connectionString;
if (tipoMotorBD == "mysql")
    connectionString = configuration.GetConnectionString("MySqlConnection")
        ?? throw new Exception("No se encontro MySqlConnection en appsettings.json.");
else if (tipoMotorBD == "mssql")
    connectionString = configuration.GetConnectionString("MSSQLConnection")
        ?? throw new Exception("No se encontro MSSQLConnection en appsettings.json.");
else
    throw new Exception("tipoBD debe ser 'mysql' o 'mssql'.");

// Inicializa el Singleton — solo se ejecuta UNA VEZ
// Después de esto, cualquier DAO puede usar: DBManager.Instance.EjecutarProcedimiento(...)
Console.WriteLine($"Conectando a BD ({tipoMotorBD}): {connectionString}");
DBManager.Initialize(connectionString, tipoMotorBD);

var app = builder.Build();

// =====================================================================
// VERIFICAR CONEXION A BD AL INICIAR
// =====================================================================
// Abre y cierra una conexión para validar que la BD está accesible.
// Si falla, la app NO inicia (throw detiene el proceso).
try
{
    DBManager.Instance.AbrirConexion().Close();
    Console.WriteLine("Conexion a BD exitosa.");
}
catch (Exception ex)
{
    Console.WriteLine($"ERROR al conectar a BD: {ex.Message}");
    throw; // La app no debe iniciar si no hay BD
}

// =====================================================================
// 3. PIPELINE HTTP
// =====================================================================
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Error", createScopeForErrors: true);
}
app.UseStatusCodePagesWithReExecute("/not-found", createScopeForStatusCodePages: true);
app.UseAntiforgery();

app.MapStaticAssets();
app.MapRazorComponents<App>()
    .AddInteractiveServerRenderMode();

app.Run();
