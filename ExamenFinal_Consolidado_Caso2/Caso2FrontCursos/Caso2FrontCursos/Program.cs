using CSharpRestClient;
using Caso2FrontCursos.Components;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddRazorComponents()
    .AddInteractiveServerComponents();

// ── Managers que la vista puede inyectar ───────────────────────────────────────
// Por defecto la app corre con REST (funciona sin generar nada extra).
builder.Services.AddScoped<UniversidadRSManager>();

// Para la pregunta SOAP:
//   1) Renombra Managers/UniversidadSOAPManager.cs.txt  →  .cs
//   2) Agrega los "Connected Services" (WSDL) en Visual Studio; cada uno debe
//      llamarse <Entidad>WSReference (ej. FacultadWSReference) para que compile.
//   3) Descomenta la línea de abajo y cambia el @inject en Universidad.razor.
// builder.Services.AddScoped<UniversidadSOAPManager>();

var app = builder.Build();

if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Error", createScopeForErrors: true);
    app.UseHsts();
}

app.UseStatusCodePagesWithReExecute("/not-found", createScopeForStatusCodePages: true);
app.UseHttpsRedirection();
app.UseAntiforgery();

app.MapStaticAssets();
app.MapRazorComponents<App>()
    .AddInteractiveServerRenderMode();

app.Run();
