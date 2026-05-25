using GestionAlumnosWebApp.Components;
using GestionAlumnosDBManager;
using GestionAlumnosBusiness.Alumnos.BOI;
using GestionAlumnosBusiness.Alumnos.BO;

var builder = WebApplication.CreateBuilder(args);

// Blazor Server
// Add services to the container.
builder.Services.AddRazorComponents()
    .AddInteractiveServerComponents();

// INYECCIÓN DE DEPENDENCIAS
// Es un patrón donde no instancias las clases directamente (new AlumnoBOImpl())
// sino que le dices al framework "cuando alguien pida IAlumnoBO, dale un AlumnoBOImpl"
// y el framework se encarga de crear y administrar la instancia.
// SIRVE:
// Desacoplamieanto: Componentes Razor solo conocen la INTERFAZ, no la clase
// Testeabilida
// Testeabilidad 
// Ciclo de vida controlado: Scoped = una instanca por request
//

builder.Services.AddScoped<IAlumnoBO, AlumnoBOImpl>();

// DB
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

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Error", createScopeForErrors: true);
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}
app.UseStatusCodePagesWithReExecute("/not-found", createScopeForStatusCodePages: true);
app.UseHttpsRedirection();

app.UseAntiforgery();

app.MapStaticAssets();
app.MapRazorComponents<App>()
    .AddInteractiveServerRenderMode();

app.Run();
