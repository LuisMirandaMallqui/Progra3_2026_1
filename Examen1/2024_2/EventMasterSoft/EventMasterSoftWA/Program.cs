using EventMasterSoftBusiness.GestEvento.BO;
using EventMasterSoftBusiness.GestEvento.BOI;
using EventMasterSoftBusiness.GestProductora.BO;
using EventMasterSoftBusiness.GestProductora.BOI;
using EventMasterSoftWA.Components;
using SoftProgDBManager;

// Capturar cualquier excepción no manejada antes del crash
AppDomain.CurrentDomain.UnhandledException += (sender, args) =>
{
    var msg = args.ExceptionObject?.ToString() ?? "Error desconocido";
    File.WriteAllText("crash_log.txt", msg);
    Console.WriteLine("=== CRASH DETECTADO ===");
    Console.WriteLine(msg);
};

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddRazorComponents()
    .AddInteractiveServerComponents()
    .AddHubOptions(options =>
    {
        options.MaximumReceiveMessageSize = 10 * 1024 * 1024; // 10MB para imágenes
    });

// Registro de los Servicios
builder.Services.AddScoped<IEventoBO,EventoBOImpl>();
builder.Services.AddScoped<IProductoraBO, ProductoraBOImpl>();
//builder.Services.AddScoped<ServicioNotificacion>();

// Inicialización del DBManager
IConfiguration configuration = new ConfigurationBuilder()
            .SetBasePath(Directory.GetCurrentDirectory())
            .AddJsonFile("appsettings.json")
            .Build();

string tipoMotorBD = configuration.GetConnectionString("tipoBD") ?? throw new Exception("No se encontró la configuración tipoBD.");

string connectionString;

if (tipoMotorBD == "mysql")
    connectionString = configuration.GetConnectionString("MySqlConnection") ?? throw new Exception("No se encontró la cadena MySqlConnection.");
else if (tipoMotorBD == "mssql")
    connectionString = configuration.GetConnectionString("MSSQLConnection") ?? throw new Exception("No se encontró la cadena MSSQLConnection.");
else
    throw new Exception("El tipo de base de datos no es válido.");

System.Console.WriteLine(connectionString);

DBManager.Initialize(connectionString, tipoMotorBD);

var app = builder.Build();

// extra para no hacer un proyecto test y probar rapido que sirve el DBManager
try
{
    DBManager.Instance.AbrirConexion().Close(); // Verificar que se pueda abrir la conexión
    Console.WriteLine("Conexión a BD exitosa.");
}
catch (Exception ex)
{
    Console.WriteLine($"Error al conectar a BD: {ex.Message}");
    throw; // para que la app no inicie si no hay BD
}
// fin extra

// Configure the HTTP request pipeline.
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
