// =====================================================================
// AlumnoRestClient.cs — CLIENTE REST del front (consume AlumnoRS del WS Java)
// =====================================================================
// Mismo patrón que el profe Paz (Servicios/REST/AreaRestService.cs): una clase por
// entidad que envuelve un HttpClient y usa GetFromJsonAsync / PostAsJsonAsync.
//
// Base (registrada en Program.cs): http://localhost:8080/TestSoftServicios/
// Por eso las rutas empiezan con "webresources/AlumnoRS" (el @Path del recurso Java).
//
// Reutilizamos el modelo Alumno del proyecto. Java manda
//   {"id":1,"codigo":"A001","nombre":"Juan","apellidos":"Pérez","correo":"...","estado":"A"}
// y GetFromJsonAsync (JsonSerializerDefaults.Web, case-insensitive) lo mapea solo.
//
// NOTA: el profe usa llamadas bloqueantes (.GetAwaiter().GetResult()); aquí van async
//       (mejor para Blazor Server). Cualquiera de las dos es válida.
// =====================================================================
using System.Net.Http.Json;
using GestionAlumnosModel.Alumno;

namespace GestionAlumnosWebApp.Services.Rest
{
    public class AlumnoRestClient
    {
        private readonly HttpClient _http;
        public AlumnoRestClient(HttpClient http) { _http = http; }

        // GET /webresources/AlumnoRS
        public async Task<List<Alumno>> ListarTodos()
            => await _http.GetFromJsonAsync<List<Alumno>>("webresources/AlumnoRS") ?? new List<Alumno>();

        // GET /webresources/AlumnoRS/buscar?texto=...
        public async Task<List<Alumno>> BuscarPorNombreApellido(string texto)
            => await _http.GetFromJsonAsync<List<Alumno>>($"webresources/AlumnoRS/buscar?texto={Uri.EscapeDataString(texto)}")
               ?? new List<Alumno>();

        // GET /webresources/AlumnoRS/5  (el WS devuelve null si no existe)
        public async Task<Alumno?> BuscarPorId(int id)
            => await _http.GetFromJsonAsync<Alumno?>($"webresources/AlumnoRS/{id}");

        // POST /webresources/AlumnoRS  -> el WS devuelve el id generado (int crudo)
        public async Task<int> Insertar(Alumno alumno)
        {
            HttpResponseMessage resp = await _http.PostAsJsonAsync("webresources/AlumnoRS", alumno);
            resp.EnsureSuccessStatusCode();
            return await resp.Content.ReadFromJsonAsync<int>();
        }

        // PUT /webresources/AlumnoRS  -> filas afectadas
        public async Task<int> Modificar(Alumno alumno)
        {
            HttpResponseMessage resp = await _http.PutAsJsonAsync("webresources/AlumnoRS", alumno);
            resp.EnsureSuccessStatusCode();
            return await resp.Content.ReadFromJsonAsync<int>();
        }

        // DELETE /webresources/AlumnoRS/5
        public async Task<int> Eliminar(int id)
        {
            HttpResponseMessage resp = await _http.DeleteAsync($"webresources/AlumnoRS/{id}");
            resp.EnsureSuccessStatusCode();
            return await resp.Content.ReadFromJsonAsync<int>();
        }
    }
}
