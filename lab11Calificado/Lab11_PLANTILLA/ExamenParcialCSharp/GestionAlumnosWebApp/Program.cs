using GestionAlumnosWebApp.Components;
using GestionAlumnosWebApp.Components.Layout;
using Microsoft.Win32;
using Org.BouncyCastle.Pqc.Crypto.Lms;
using static System.Net.Mime.MediaTypeNames;

//MyBlazorApp /
//├── wwwroot /                  ← Static assets(CSS, images, JS, FontAwesome)
//├── Components /
//│   ├── Pages /                ← Routable pages(.razor files)
//│   └── Layout /               ← MainLayout.razor, NavMenu.razor
//├── appsettings.json          ← App configuration
//└── Program.cs                ← Entry point; registers services

using GestionAlumnosWebApp.Components;
using GestionAlumnosDBManager;
using GestionAlumnosBusiness.Alumnos.BOI;
using GestionAlumnosBusiness.Alumnos.BO;
// ===== INTEGRACIÓN WS =====
using GestionAlumnosWebApp.Services.Rest;
using GestionAlumnosWebApp.Services.Soap;

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

// ===== INTEGRACIÓN WS (SOAP + REST) =====
// El front puede leer de la BD por DOS vías:
//   (a) Local: IAlumnoBO -> Business -> DBManager  (lo de arriba, sin red)
//   (b) Remoto: consumiendo el WS Java desplegado en GlassFish (REST/SOAP)
//
// REST: HttpClient TIPADO. La BaseAddress apunta al @ApplicationPath("webresources") del WAR Java.
//        OJO: la base DEBE terminar en "/" para que la ruta relativa ("webresources/AlumnoRS") se concatene bien.
builder.Services.AddHttpClient<AlumnoRestClient>(c =>
{
    c.BaseAddress = new Uri("http://localhost:8080/TestSoftServicios/");
});
// SOAP (camino manual): HttpClient simple; la URL del endpoint vive dentro del cliente.
builder.Services.AddHttpClient<AlumnoSoapClient>();
// SOAP (camino oficial con Add Service Reference): se registraría el proxy generado, p.ej.
//   builder.Services.AddScoped<AlumnoWSRef.AlumnoWSClient>();

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
        ?? throw 