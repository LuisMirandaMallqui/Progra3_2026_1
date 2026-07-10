using Newtonsoft.Json;

namespace FifaSoftWA.Components.Servicios
{
    // DTO del FRONT: refleja la clase 'DirectorTecnico' del backend Java.
    // Se llena desde el REST  GET webresources/DirectorTecnicoRS/{id}.
    public class DirectorTecnico
    {
        [JsonProperty("idDirectorTecnico")]
        public int IdDirectorTecnico { get; set; }

        [JsonProperty("nombre")]
        public string Nombre { get; set; } = string.Empty;

        [JsonProperty("nacionalidad")]
        public string Nacionalidad { get; set; } = string.Empty;

        [JsonProperty("edad")]
        public int Edad { get; set; }
    }
}
