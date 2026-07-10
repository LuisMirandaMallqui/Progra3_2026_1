using Newtonsoft.Json;

namespace FifaSoftWA.Components.Servicios
{
    // DTO del FRONT: refleja la clase 'Seleccion' del backend Java.
    // [JsonProperty("xxx")] = nombre EXACTO del campo tal como llega en el JSON del
    // REST (SeleccionRS). Si el nombre no coincide con el JSON, ese campo llega null/0.
    public class Seleccion
    {
        [JsonProperty("idSeleccion")]
        public int IdSeleccion { get; set; }

        [JsonProperty("nombre")]
        public string Nombre { get; set; } = string.Empty;

        [JsonProperty("confederacion")]
        public string Confederacion { get; set; } = string.Empty;

        // Java manda el grupo como "A", "B"... (un solo carácter); Newtonsoft lo convierte a char.
        [JsonProperty("grupo")]
        public char Grupo { get; set; }

        [JsonProperty("rankingFifa")]
        public int RankingFIFA { get; set; }

        [JsonProperty("urlBandera")]
        public string UrlBandera { get; set; } = string.Empty;

        [JsonProperty("clasificado")]
        public bool Clasificado { get; set; }

        // En el JSON de SeleccionRS, el directorTecnico viene con SOLO el id.
        // El resto (nombre, nacionalidad, edad) se completa después llamando a
        // DirectorTecnicoRS/{id} dentro de SeleccionStateService.
        //   ▸ ANTES esta línea era:  set => directorTecnico;   ← NO compilaba (error CS0201:
        //     un 'set' con cuerpo de expresión debe ser una ASIGNACIÓN). Ese era el bug
        //     que costó los 2 puntos de "errores al compilar y ejecutar".
        [JsonProperty("directorTecnico")]
        public DirectorTecnico DirectorTecnico { get; set; } = new();
    }
}
