// =====================================================================
// AlumnoSoapClient.cs — CLIENTE SOAP del front C# que consume AlumnoWS (Java)
// =====================================================================
//
//  HAY DOS CAMINOS PARA CONSUMIR SOAP EN C#. El profe acepta cualquiera:
//
//  ── CAMINO 1 (oficial): Add Service Reference ────────────────────────────
//  1. GlassFish corriendo + WSDL: http://localhost:8080/TestSoftServicios/AlumnoWS?wsdl
//  2. VS: click derecho en el proyecto WA -> Add -> Service Reference -> WCF Web Service
//        -> pega el WSDL -> Namespace: "AlumnoWSRef" (DISTINTO al serviceName) -> Finish.
//  3. Uso (VS genera *Async y el tipo Alumno proxy):
//        var c = new AlumnoWSRef.AlumnoWSClient();
//        var lista = await c.listarTodosAsync();
//        int id    = await c.insertarAsync(alumno);     // <-- enviar el OBJETO completo
//        await c.modificarAsync(alumno);
//
//  ── CAMINO 2 (manual): armar el sobre SOAP a mano ────────────────────────
//  SOAP = POST de un XML. Abajo se muestran las 3 formas que pide el lab:
//   - recibir 1 objeto         -> BuscarPorId (parsea <return> con sus campos)
//   - ENVIAR 1 objeto entero   -> Insertar / Modificar (serializan el <alumno> anidado)
// =====================================================================
using System.Text;
using System.Xml.Linq;
using GestionAlumnosModel.Alumno;

namespace GestionAlumnosWebApp.Services.Soap
{
    public class AlumnoSoapClient
    {
        private readonly HttpClient _http;
        private const string EndpointUrl = "http://localhost:8080/TestSoftServicios/AlumnoWS";
        // == targetNamespace del @WebService en Java. Si no coincide -> fault "no operation".
        private static readonly XNamespace ns = "http://services.testsoft.pucp.edu.pe/";

        public AlumnoSoapClient(HttpClient http) { _http = http; }

        // ---- RECIBIR un objeto: buscarPorId(id) -> Alumno -----------------
        public async Task<Alumno?> BuscarPorId(int id)
        {
            string body = $"<ns:buscarAlumnoPorId><idAlumno>{id}</idAlumno></ns:buscarAlumnoPorId>";
            XElement? ret = await EnviarSobre(body, "buscarAlumnoPorId");
            if (ret == null) return null;
            return LeerAlumno(ret);
        }

        // ---- ENVIAR un objeto entero: insertar(alumno) -> id generado -----
        public async Task<int> Insertar(Alumno alumno)
        {
            // El parámetro se llama "alumno" por el @WebParam(name="alumno") del Java.
            // Dentro van los campos con el NOMBRE de la propiedad JAXB (minúscula).
            string body = $"<ns:insertarAlumno>{SerializarAlumno(alumno)}</ns:insertarAlumno>";
            XElement? ret = await EnviarSobre(body, "insertarAlumno");
            return ParseInt(ret?.Value);     // el WS devuelve el id en <return>
        }

        // ---- ENVIAR un objeto entero: modificar(alumno) -> filas afectadas -
        public async Task<int> Modificar(Alumno alumno)
        {
            string body = $"<ns:modificarAlumno>{SerializarAlumno(alumno)}</ns:modificarAlumno>";
            XElement? ret = await EnviarSobre(body, "modificarAlumno");
            return ParseInt(ret?.Value);
        }

        // =================================================================
        // Helpers
        // =================================================================

        // Construye el bloque <alumno>...</alumno> con TODOS los campos.
        // OJO: orden alfabético de los campos (por seguridad con el propOrder por defecto de JAXB).
        // 'id' va aunque sea 0: en modificar el WS lo necesita para saber qué fila tocar.
        private static string SerializarAlumno(Alumno a) =>
            "<alumno>" +
                $"<apellidos>{Esc(a.Apellidos)}</apellidos>" +
                $"<codigo>{Esc(a.Codigo)}</codigo>" +
                $"<correo>{Esc(a.Correo)}</correo>" +
                $"<estado>{a.Estado}</estado>" +
                $"<id>{a.Id}</id>" +
                $"<nombre>{Esc(a.Nombre)}</nombre>" +
            "</alumno>";

        private static Alumno LeerAlumno(XElement ret)
        {
            string V(string tag) => ret.Elements().FirstOrDefault(e => e.Name.LocalName == tag)?.Value ?? "";
            return new Alumno
            {
                Id        = ParseInt(V("id")),
                Codigo    = V("codigo"),
                Nombre    = V("nombre"),
                Apellidos = V("apellidos"),
                Correo    = V("correo"),
                Estado    = string.IsNullOrEmpty(V("estado")) ? 'A' : V("estado")[0]
            };
        }

        // POST del sobre + devuelve el <return> (o null si hubo fault/HTTP error).
        private async Task<XElement?> EnviarSobre(string operationXml, string op)
        {
            string envelope =
$@"<?xml version=""1.0"" encoding=""UTF-8""?>
<soap:Envelope xmlns:soap=""http://schemas.xmlsoap.org/soap/envelope/"" xmlns:ns=""{ns}"">
  <soap:Body>{operationXml}</soap:Body>
</soap:Envelope>";

            using var content = new StringContent(envelope, Encoding.UTF8, "text/xml");
            content.Headers.Add("SOAPAction", "\"\"");
            HttpResponseMessage resp = await _http.PostAsync(EndpointUrl, content);
            string xml = await resp.Content.ReadAsStringAsync();
            if (!resp.IsSuccessStatusCode)
            {
                // Si el BO lanzó Exception, GlassFish responde 500 con un <soap:Fault><faultstring>.
                XElement? fault = TryParse(xml)?.Descendants().FirstOrDefault(e => e.Name.LocalName == "faultstring");
                throw new Exception(fault?.Value ?? $"SOAP {op} HTTP {(int)resp.StatusCode}");
            }
            return TryParse(xml)?.Descendants().FirstOrDefault(e => e.Name.LocalName == "return");
        }

        private static XDocument? TryParse(string xml) { try { return XDocument.Parse(xml); } catch { return null; } }
        private static int ParseInt(string? s) => int.TryParse(s, out int n) ? n : 0;
        // Escape mínimo de XML para no romper el sobre con &, <, >.
        private static string Esc(string? s) => System.Security.SecurityElement.Escape(s ?? "");
    }
}
