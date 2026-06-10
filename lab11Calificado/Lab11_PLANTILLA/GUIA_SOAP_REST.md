# Guía SOAP + REST — solo lo que los PDFs NO cuentan

> Los dos PDFs del curso ya cubren bien la teoría y el "hola mundo":
> **"Desarrollo de Servicios Web SOAP con GlassFish e IntelliJ"** y
> **"Creación de Servicios Web RESTFul con Jakarta REST"**.
>
> **Lo que YA está ahí (no lo repito):** conceptos (Jakarta EE, SOAP, WSDL, JAX-WS/RS),
> instalación de GlassFish + plugins en IntelliJ, tablas de anotaciones, `?Tester`/`?wsdl`,
> el asistente *Add Service Reference* de C#, pruebas en Postman, el CRUD REST con `ArrayList`
> en memoria, y el cliente C# de consola con `HttpWebRequest` + `Newtonsoft`.
>
> **Lo que ESTA guía agrega (el delta real del lab):** conectar el WS a las **capas reales**
> (BO→DAO→Stored Procedure→DBManager), serialización de DTOs, `datos.properties`/driver dentro del WAR,
> el cliente desde **Blazor** (no consola), el sobre SOAP manual, y las trampas que bajaron nota.

> **Convenciones del repo REAL del profe Paz (lo seguimos en esta solución):**
> - **UN solo WAR** `TestSoftServicios` con dos paquetes: `services.soap.<modulo>` y `services.rest.<modulo>` + `services.rest.RestApplication` (`@ApplicationPath("webresources")`).
> - Nombres: SOAP `AlumnoWS` / REST `AlumnoRS` (sufijos WS y RS); operaciones `listarAlumnosTodos`, `insertarAlumno`, `buscarAlumnoPorId`...
> - Los métodos del WS **retornan crudo** (`List`, `int`, DTO) y envuelven en `try/catch` con `println`. No usan `Response`.
> - Front: un `XxxRestService` por entidad (HttpClient) + base `http://localhost:8080/TestSoftServicios/` y rutas `webresources/AlumnoRS`; SOAP por *Add Service Reference*.

---

## 1. Lo único que el PDF NO hace: enganchar el WS a las capas

En los PDFs el servicio guarda en un `ArrayList` en memoria. En el lab el servicio debe llamar al **BO**.
El WS es una **cáscara**: cero lógica, cero SQL. Solo traduce HTTP/SOAP → llamada al `BO`.

```java
// REST real (lo que el PDF NO muestra: delega a Business). Estilo profe Paz: sufijo "RS",
// BO en el constructor, retorno crudo (List/int/DTO) y try/catch con println.
@Path("AlumnoRS")
@Produces(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON)
public class AlumnoRS {
    private IAlumnoBO bo;
    public AlumnoRS(){ this.bo = new AlumnoBOImpl(); }   // ← aquí entra TODA tu arquitectura
    @GET public List<Alumno> listarAlumnosTodos(){
        try { return bo.listarTodos(); } catch(Exception ex){ System.out.println(ex.getMessage()); return null; }
    }
}
```
```java
// SOAP real
@WebService(serviceName="AlumnoWS", targetNamespace="http://services.testsoft.pucp.edu.pe/")
public class AlumnoWS {
    private final IAlumnoBO bo = new AlumnoBOImpl();
    @WebMethod(operationName="buscarAlumnoPorId")
    public Alumno buscarAlumnoPorId(@WebParam(name="idAlumno") int idAlumno) throws Exception { return bo.buscarPorId(idAlumno); }
}
```
Cadena de dependencias del WAR: **WS → Business → Persistance → DBManager (+ driver) → Model**.
En el `pom` del WS basta declarar `TestSoftModel` y `TestSoftBusiness`; lo demás viaja transitivo.

---

## 2. La trampa #1 del WAR: `datos.properties` y el driver MySQL

Los PrincipalRS de los PDFs no tocan BD, por eso no avisan de esto. Cuando el WS sí usa `DBManager`:

- `ResourceBundle.getBundle("datos")` busca **`datos.properties` en el classpath del WAR**
  → va en `TestSoftServicios/src/main/resources/` (un solo WAR de servicios).
  Si no está → `MissingResourceException` al primer request.
- El **driver** (`mysql-connector-j`) debe estar en el WAR. Lo logramos con `scope compile` en el
  `pom` del **DBManager** (así se empaqueta al hacer el war). Si sale
  `No suitable driver` / `ClassNotFoundException: com.mysql.cj.jdbc.Driver` → el jar no llegó al war.
- **MySQL en AWS sin SSL:** en el esquema agrega los flags o el handshake falla:
  ```properties
  db.esquema=testsoft?useSSL=false&allowPublicKeyRetrieval=true
  ```

---

## 3. Serialización: lo que rompe al devolver objetos (no está en los PDFs)

Los ejemplos del PDF devuelven `String`, `double` o un `Area` con `boolean`. En el lab devuelves DTOs:

- **Constructor vacío OBLIGATORIO** en cada DTO. Sin él, JAXB (SOAP) y JSON-B (REST) no instancian → error feo.
- **Tipos que serializan limpio:** primitivos, `String`, `List<DTO>`, otros DTOs.
- **Tipos que dan dolor:** `Map`, `Optional`, interfaces como retorno. Evítalos en firmas de WS.
- **`char` / `enum` / `Date`:** los modelé como **String** en el Model (`estado="A"`, fechas como texto).
  Razón: SOAP serializa `char` como número y JSON-B lo trata distinto; `Date` arrastra zona horaria.
  String elimina el problema y el front lo reconvierte.
- **Mapeo de nombres JSON ↔ C#:** JSON-B usa el getter en minúscula (`getCodigo`→`"codigo"`).
  En C#, `GetFromJsonAsync` usa `JsonSerializerDefaults.Web` (**case-insensitive**), así `"codigo"`
  calza con la propiedad `Codigo` sin anotar nada. *(El PDF usa `[JsonProperty]` + Newtonsoft;
  con el `HttpClient` tipado moderno ya no hace falta.)*

---

## 4. Consumir el WS desde C# (Blazor)

El PDF lo hace en consola con `HttpWebRequest`. En Blazor: **REST con `HttpClient`** y
**SOAP con *Add Service Reference*** (la vía oficial; sin escribir XML a mano).

### 4.1 REST — `HttpClient` tipado + DI (ver `Services/Rest/AlumnoRestClient.cs`)
```csharp
// Program.cs — registrar. La base DEBE terminar en "/".
builder.Services.AddHttpClient<AlumnoRestClient>(c =>
    c.BaseAddress = new Uri("http://localhost:8080/TestSoftServicios/"));  // base = WAR; la ruta lleva "webresources/AlumnoRS"

// dentro del cliente
var lista = await http.GetFromJsonAsync<List<Alumno>>("webresources/AlumnoRS") ?? new();
await http.PostAsJsonAsync("webresources/AlumnoRS", alumno);   // envía el objeto como JSON, sin Newtonsoft
int id = await resp.Content.ReadFromJsonAsync<int>();          // el WS (estilo profe) devuelve el id crudo
```
> ⚠ En Blazor Server las llamadas a WS van **async** (`await ...`). Envuelve en try/catch para que,
> si GlassFish está caído, salga un mensaje y no se rompa el render.

### 4.2 SOAP en C# — Add Service Reference (esto es lo que harás, SÍ funciona)
Pasos (con GlassFish corriendo y el WSDL accesible):
1. VS → click derecho en el proyecto WA → **Add → Service Reference → WCF Web Service**.
2. URI del WSDL: `http://localhost:8080/TestSoftServicios/AlumnoWS?wsdl` → **Go**.
3. **Namespace**: `AlumnoWSRef` (¡DISTINTO del `serviceName` `AlumnoWS`, o chocan las clases!) → Finish.

**Qué te genera Visual Studio (sin que escribas nada):**
- Una clase **proxy** `AlumnoWSClient` con un método por cada operación, en versión `...Async`.
- Clases **espejo** de tus DTOs (un `Alumno` del lado C#, reconstruido desde el WSDL) — por eso no
  necesitas tu propio modelo para SOAP.

```csharp
// Registro (Program.cs)
builder.Services.AddScoped<AlumnoWSRef.AlumnoWSClient>();

// Uso en un componente
@inject AlumnoWSRef.AlumnoWSClient Soap
...
var lista = await Soap.listarAlumnosTodosAsync();     // recibir lista
var a     = await Soap.buscarAlumnoPorIdAsync(5);     // recibir 1 objeto
```

### 4.3 Insertar/Modificar OBJETOS por SOAP — qué pasa EXACTAMENTE
La duda típica: "¿cómo mando el alumno entero y qué ocurre de cada lado?". Paso a paso:

```csharp
// 1) En C# creas el objeto del proxy (la clase Alumno que generó el WSDL) y lo llenas:
var alumno = new AlumnoWSRef.Alumno {
    codigo = "A003", nombre = "Ana", apellidos = "Torres",
    correo = "ana.torres@pucp.pe", estado = "A", id = 0   // id=0 al insertar
};
// 2) Llamas la operación pasando el OBJETO COMPLETO. Devuelve el id generado:
int idGenerado = await Soap.insertarAlumnoAsync(alumno);
// Modificar: igual pero con el id real puesto
alumno.id = idGenerado; alumno.nombre = "Ana María";
int filas = await Soap.modificarAlumnoAsync(alumno);
```

**Qué hace el proxy POR DEBAJO (tú NO lo escribes):** convierte tu objeto en un XML SOAP donde el
parámetro `@WebParam(name="alumno")` se vuelve un nodo `<alumno>` con un hijo por cada campo:
```xml
<S:Body>
  <ns2:insertarAlumno xmlns:ns2="http://services.testsoft.pucp.edu.pe/">
    <alumno>
      <apellidos>Torres</apellidos><codigo>A003</codigo>
      <correo>ana.torres@pucp.pe</correo><estado>A</estado>
      <id>0</id><nombre>Ana</nombre>
    </alumno>
  </ns2:insertarAlumno>
</S:Body>
```
**Qué pasa en el lado Java (GlassFish):** JAX-WS recibe ese sobre, **JAXB reconstruye** un objeto
`Alumno` real (por eso el DTO necesita constructor vacío), tu `AlumnoWS.insertarAlumno(alumno)` lo
pasa al BO → DAO → SP, y el `int` que retornas vuelve serializado como `<return>5</return>`. El proxy
te lo entrega como `int`. **Resumen:** objeto C# → (proxy serializa) → XML → (JAXB deserializa) →
objeto Java → BO → `int` → XML → (proxy) → `int` en C#.

### 4.4 Probar con `?Tester` (la página de prueba de GlassFish) — qué SÍ y qué NO
Al abrir `http://localhost:8080/TestSoftServicios/AlumnoWS?Tester`, GlassFish arma un formulario web
de prueba. Pero ojo:
- **Operaciones con parámetros simples** (`saludar(String)`, `buscarAlumnoPorId(int)`,
  `eliminarAlumno(int)`): el `?Tester` te muestra una cajita de texto y funciona perfecto. Úsalo
  para humo rápido.
- **Operaciones que reciben un OBJETO** (`insertarAlumno(Alumno)`, `modificarAlumno(Alumno)`): el
  `?Tester` normalmente **no sabe** dibujar el formulario del objeto complejo (lo deja vacío o no lo
  lista). Eso es normal, no es que tu WS esté mal. Para probar insertar/modificar usa:
  - el **cliente C#** (el proxy, como arriba), o
  - **SoapUI** (carga el WSDL y te arma el sobre `<alumno>` editable).
- El `?wsdl` siempre debe abrir; si el WSDL carga, el servicio está bien publicado aunque el `?Tester`
  no pruebe los objetos.

> El proyecto incluye además un `Services/Soap/AlumnoSoapClient.cs` (sobre SOAP a mano) por si quieres
> ver el XML crudo o que la página demo corra antes de generar el proxy. En el lab basta con
> *Add Service Reference*; no necesitas escribir XML.

## 5. Dos estilos de retorno REST: crudo (profe) vs. Response (PDF)

**Estilo del profe Paz (el que seguimos):** el método devuelve el dato crudo y traga la excepción.
GlassFish responde 200 igual; el front interpreta el valor (`int` id, `List`, DTO o `null`).
```java
@POST public int insertarAlumno(Alumno a) {
    try { return bo.insertar(a); } catch(Exception ex){ System.out.println(ex.getMessage()); return 0; }
}
```
**Estilo del PDF (más robusto, también válido):** devuelve `Response` con código HTTP + motivo en el body.
Útil si el enunciado pide "manejar errores"/códigos correctos:
```java
@POST public Response insertar(Alumno a) {
    try { a.setId(bo.insertar(a)); return Response.status(Response.Status.CREATED).entity(a).build(); }
    catch (Exception ex) { return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build(); }
}
```
| Op | Verbo | OK | Falla |
|----|-------|----|-------|
| Listar | GET | 200+JSON | — |
| Por id | GET | 200 | 404 |
| Insertar | POST | 201 + entidad | 400 + mensaje |
| Modificar | PUT | 200 | 304 |
| Eliminar | DELETE | 204 | 404 |

En Postman: POST/PUT → Body → **raw → JSON**. Si sale **415**, te faltó `@Consumes(JSON)`.

---

## 6. Probar REST en Postman (SOAP NO se prueba aquí)

El correo dice claro: **REST se prueba con Postman**. Pasos en Postman:

1. Arriba: elige el **verbo** (GET / POST / PUT / DELETE) y escribe la **URL**.
2. Para **POST/PUT** ve a la pestaña **Body → raw → JSON** (el desplegable de la derecha en "raw").
   Al elegir raw+JSON, Postman pone solo el header `Content-Type: application/json`.
3. Pega el objeto en **JSON** (los nombres son los de los getters, en minúscula).

Ejemplos (base `http://localhost:8080/TestSoftServicios/webresources`):

```text
GET    /AlumnoRS                      -> 200 + lista JSON
GET    /AlumnoRS/5                    -> 200 (o 404 si no existe)
GET    /AlumnoRS/buscar?texto=ana     -> 200 + lista filtrada
POST   /AlumnoRS   (Body raw JSON)    -> 201/200 + id generado
PUT    /AlumnoRS   (Body raw JSON)    -> 200 (incluye "id" en el body)
DELETE /AlumnoRS/5                    -> 204
```
```json
// Body para POST /AlumnoRS  (en PUT agrega  "id": 5 )
{
  "codigo": "A003",
  "nombre": "Ana",
  "apellidos": "Torres",
  "correo": "ana.torres@pucp.pe",
  "estado": "A"
}
```

Errores típicos en Postman y qué significan:

- **415 Unsupported Media Type** → te olvidaste de Body **raw → JSON** (o falta `@Consumes(JSON)` en el WS).
- **404 Not Found** → ruta mal escrita, o el WAR no está desplegado, o el context path no es el que crees
  (mira el nombre real del `.war` en GlassFish).
- **500 Internal Server Error** → casi siempre la BD: revisa el **log de GlassFish** (`server.log`);
  suele ser el `datos.properties`/driver que no llegó al WAR o el `useSSL`.
- **Campos en `null` al insertar** → los nombres del JSON no coinciden con los getters (usa minúsculas:
  `codigo`, `nombre`, `estado`...).

> **SOAP no se prueba cómodo en Postman** (tendrías que armar el sobre XML + headers a mano). Para SOAP
> usa la página `?Tester` de GlassFish (solo parámetros simples) o **SoapUI** (carga el WSDL). Ver §4.4.

## 7. Trampas que bajaron nota en el lab final pasado (corrección textual)

- **Cliente RESTful no implementado = 0 pts.** Si piden cliente, el `HttpClient` es obligatorio.
- **POST/PUT separados cuando pedían un POST con *upsert*.** Lee el enunciado: si dice
  "si existe actualiza, si no inserta", resuélvelo en **un** método (busca por id/código → decide).
- **Devolver el padre sin sus hijos.** Si piden "propietario **con sus vehículos**", arma el objeto
  anidado (el BO/DAO carga la lista relacionada). Devolver solo el padre = puntos perdidos.
- **Capa de negocio vacía.** "Solo delegación al DAO, sin validaciones" baja nota. Pon reglas reales
  en el BO (obligatorios, formato de DNI/correo, no duplicar código, rangos de nota).
- **CRUD incompleto.** Dejar `eliminar` devolviendo `0` o sin su SP. Implementa los 5 métodos del DAO/BO.
- **SOAP sin `@WebParam(name=...)`.** Los parámetros salen como `arg0`, `arg1`, `arg2` en el WSDL y en
  el proxy C#. Ponlo SIEMPRE, sobre todo si el método tiene varios parámetros.
- **Listar mostrando un id en vez del nombre relacionado.** Si hay FK (tipo, área, propietario...),
  haz **JOIN** y trae el nombre, no solo el `fid_...`.
- **DAO con `con`/`cs`/`rs` como campos de instancia.** En un WS llegan varias peticiones a la vez →
  **condición de carrera** (datos cruzados, errores intermitentes). Usa variables **locales** o los
  métodos del `DBManager`.

---

## 8. La capa que NUNCA cambia (fragmentos del profe Paz, por si cae Pokémon/Vehículos)

### 7.1 DAO posicional (clave del Map = posición del `?`)
```java
Map<Integer,Object> in = new HashMap<>(), out = new HashMap<>();
out.put(1, Types.INTEGER);          // OUT _id va PRIMERO
in.put(2, alumno.getCodigo());      // IN _codigo
DBManager.getInstance().ejecutarProcedimiento("SP_INSERTAR_ALUMNO", in, out);
alumno.setId((int) out.get(1));     // recuperar auto_increment
```
Lectura → SIEMPRE try-with-resources:
```java
try (DBManager.ResultadoConsulta rc = DBManager.getInstance().ejecutarProcedimientoLectura("SP_...", in)) {
    ResultSet rs = rc.getRs();
    while (rs.next()) lista.add(mapear(rs));
}
```

### 7.2 SELECT INTO (el patrón "raro" del Lab7 Pokémon)
El SP no devuelve filas: mete el valor en un OUT. **No** uses lectura, usa el de OUT:
```sql
CREATE PROCEDURE obtener_tipo_pokemon_por_nombre(IN p_nombre VARCHAR(50), OUT p_id_tipo INT)
BEGIN SELECT id_tipo INTO p_id_tipo FROM tipo_pokemon WHERE nombre=p_nombre LIMIT 1; END$$
```
```java
in.put(1, nombre); out.put(2, Types.INTEGER);
DBManager.getInstance().ejecutarProcedimiento("obtener_tipo_pokemon_por_nombre", in, out);
Object v = out.get(2);   // getInt sobre NULL → 0 → trátalo como "no encontrado"
```

### 7.3 Tipos raros a mano (enum/char/fecha)
```java
// JAVA al leer ResultSet
x.setGenero(rs.getString("genero").charAt(0));
x.setCategoria(Categoria.valueOf(rs.getString("categoria")));
```
```csharp
// C# — campos NULL revientan: IsDBNull primero
if (!lector.IsDBNull("id")) x.Id = lector.GetInt32("id");
x.Categoria = (Categoria)Enum.Parse(typeof(Categoria), lector.GetString("categoria"));
```

---

## 9. GlassFish 8.0.2 — lo que sí o sí se reinstala en el lab (las PCs se resetean)

- `set AS_JAVA=C:\Program Files\Java\jdk-25.0.2` en `glassfish\config\asenv.bat` (lo dice el PDF SOAP §2.5).
- En IntelliJ: **delegar build/run a Maven** (fija de Corcuera) y deploy del artefacto `:war exploded`.
- `<finalName>TestSoftServicios</finalName>` en el `pom` → context path corto y URLs estables.

### Si GlassFish se traba (run + stop rápido lo deja en bucle)
```
netstat -ano | findstr :4848
taskkill /pid <PID> /f
```
Arranque manual del dominio si el plugin falla con `NoClassDefFoundError ... LocalStringsImpl`:
```
<glassfish>\bin\asadmin.bat start-domain domain1
```

---

## 10. Checklist de 60 segundos antes de entregar

- [ ] `datos.properties` dentro de cada WAR + `useSSL=false&allowPublicKeyRetrieval=true`.
- [ ] `mysql-connector` viaja al war (DBManager scope compile).
- [ ] DTOs con **constructor vacío**; firmas sin `Map`/`Optional`/interfaces.
- [ ] SOAP: `@WebParam(name=...)` en todo; el WSDL abre en el navegador.
- [ ] REST: `@ApplicationPath`, `@Consumes/@Produces JSON`, códigos HTTP correctos.
- [ ] Si piden **cliente**, está hecho (REST `HttpClient` / SOAP Add Reference o sobre manual).
- [ ] Validaciones en el **BO**; POST **upsert** si lo piden; entidad **con su relación** si lo piden.
