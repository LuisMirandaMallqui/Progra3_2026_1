# GUÍA — Plantilla Consolidada · Examen Final Progra 3 (Caso 2: Universidad / Matrícula)

> **Recomendación corta:** para el examen, **elige el CASO 2 (Universidad/Matrícula)** y usa
> ESTA carpeta como base. Es la plantilla más completa y limpia de todas las de tus amigos:
> backend por capas + **SOAP** (con DTO y mapper) + **REST** (CRUD completo) + **front Blazor**
> con manager para ambos, ya actualizada a la BD oficial de matrícula.

---

## 1. Por qué CASO 2 y qué se consolidó

De los 6 proyectos de tus amigos, la calidad se reparte así:

| Proyecto (amigo) | Caso | SOAP | REST | Front | Estado |
|---|---|---|---|---|---|
| `Caso2SOAP` | 2 Universidad | ✅ 14 WS + DTO + **mapper** | (pom lo declara, faltaba módulo) | ✅ SOAP+REST managers | **Muy bueno** |
| `RESTCaso2` (BD Oficial) | 2 Universidad | ❌ | ✅ 14 RS + `RestApplication` | ✅ REST manager | **Muy bueno** |
| `Caso1SOAP` (EX2SOAP) | 1 SocialSoft | (nombre) | ✅ Maven limpio | — | Bueno |
| `RESTExamenFinalPlanchazo` | 1 SocialSoft | ❌ | ✅ + front | ✅ | Bueno |
| `Caso1_PlantillazoRESTSOAP` | 1 SocialSoft | parcial | ✅ | ✅ | Desordenado (carpeta `suscripciones.dao` con punto, DAOs duplicados) |
| `Caso1_PlantillaActualizadaxd` | 1 SocialSoft | ❌ | parcial | — | Desordenado (mismo bug) |

**Caso 2 gana** porque: (a) su código está más ordenado (sin carpetas duplicadas), (b) es el
**único** con SOAP y REST **completos** para el mismo caso, (c) su front trae manager para SOAP
**y** para REST, y (d) ya viene ajustado a la BD oficial con procedimientos especiales del caso
(conflicto de horario, promedio final, contar matriculados).

**Lo que hice para consolidar** (los dos backends de Caso 2 comparten módulos **idénticos**
`Model/DBManager/Persistence/Business`, y el `pom.xml` padre de `Caso2SOAP` ya listaba
`RestServices` y `SoapServices`, pero le faltaba físicamente la carpeta `RestServices`):

1. Tomé `Caso2SOAP` como base (backend con `SoapServices` + front con ambos managers).
2. Le copié el módulo **`RestServices`** desde `RESTCaso2` (BD Oficial).
3. Resultado: **un solo** `UniversidadExam` que compila SOAP **y** REST en un solo `mvn`.
4. Dejé el **front corriendo con REST por defecto** (funciona sin generar nada extra) y el
   camino SOAP listo para activar (ver §5).

---

## 2. Contenido de esta carpeta

```
ExamenFinal_Consolidado_Caso2/
├─ UniversidadExam/                 ← BACKEND (Maven multi-módulo, Java 25)
│  ├─ pom.xml                       ← padre: lista los 6 módulos
│  ├─ Model/                        ← POJOs (Facultad, Curso, Estudiante, Matricula, ...)
│  ├─ DBManager/                    ← conexión + llamada a procedimientos + transacciones
│  │   └─ src/main/resources/db.properties   ← DATOS DE CONEXIÓN (editar aquí)
│  ├─ Persistence/                  ← DAO (interfaces en /dao, impl en /impl) + BaseDAO
│  ├─ Business/                     ← BO (interfaces XxxBO, impl en /implementsBO) + BaseBO
│  ├─ RestServices/                 ← JAX-RS: XxxRS.java + RestApplication  → RestServicesCaso2.war
│  └─ SoapServices/                 ← JAX-WS: XxxWS.java + /dto + /mapper    → SoapServicesCaso2.war
├─ Caso2FrontCursos/                ← FRONT Blazor Server (net10.0)
│  └─ Caso2FrontCursos/
│     ├─ Program.cs                 ← registra el manager (REST por defecto)
│     ├─ DTO/UniversidadDTO.cs      ← DTOs del front (emparejan con el JSON del back)
│     ├─ Utils/HttpClientUtils.cs   ← cliente REST genérico get/post/put/delete
│     ├─ Managers/
│     │   ├─ UniversidadRSManager.cs        ← ACTIVO (consume REST)
│     │   └─ UniversidadSOAPManager.cs.txt  ← plantilla SOAP (renombrar a .cs para usar)
│     └─ Components/Pages/Universidad.razor ← la vista (tabs, tablas, KPIs)
├─ Scripts_SQL/01_PROCEDURES_Caso2_Matricula_schemaCaso2.sql
├─ README_backend_BDoficial.txt / README_SOAP.txt   ← notas originales del autor
└─ GUIA_CONSOLIDADA.md             ← este archivo
```

---

## 3. Arquitectura y FLUJO (lo que el profe evalúa)

**Capas del backend** (regla de oro: cada capa solo habla con la de abajo):

```
Servicio (SOAP XxxWS / REST XxxRS)   ← expone las operaciones
        │
     Business (XxxBO → XxxImplementsBO)   ← LÓGICA y TRANSACCIONES van aquí
        │
   Persistence (XxxDAO → XxxDAOImpl)       ← arma parámetros y llama al procedimiento
        │
     DBManager                              ← abre conexión, ejecuta el SP, mapea salida
        │
      MySQL (procedimientos almacenados)
```

**Flujo REST** (lo que corre por defecto en el front):

```
Universidad.razor
  → UniversidadRSManager        (Managers/UniversidadRSManager.cs)
    → HttpClientUtils<T>.get/post/put/delete   (Utils/HttpClientUtils.cs)
      → HTTP JSON → http://localhost:8080/RestServicesCaso2/webresources/facultades
        → FacultadRS  (@Path("facultades"), @GET/@POST/@PUT/@DELETE)
          → FacultadBO → FacultadDAOImpl → DBManager → MySQL
```

**Flujo SOAP** (pregunta 1 del examen):

```
Universidad.razor
  → UniversidadSOAPManager      (renombrar .cs.txt → .cs)
    → FacultadWSReference.FacultadWSClient   (Connected Service que generas en VS desde el WSDL)
      → SOAP → http://localhost:8080/SoapServicesCaso2/FacultadWS
        → FacultadWS (@WebService/@WebMethod) → UniversidadSoapMapper (Model↔DTO)
          → FacultadBO → FacultadDAOImpl → DBManager → MySQL
```

> **Diferencia importante:** los **REST** devuelven el **modelo** directo como JSON; los **SOAP**
> devuelven **DTO** (por eso hay `/dto` y `UniversidadSoapMapper`). Ambos patrones son válidos;
> el DTO+mapper evita problemas serializando fechas/objetos anidados en SOAP.

---

## 4. Puesta en marcha (orden EXACTO)

**A. Base de datos (MySQL en tu RDS/AWS Academy)**
1. `CREATE DATABASE schemaCaso2;  USE schemaCaso2;`
2. Ejecuta el **DDL oficial** del caso (el que da el profe).
3. Ejecuta el **DML oficial** (datos iniciales).
4. Ejecuta `Scripts_SQL/01_PROCEDURES_Caso2_Matricula_schemaCaso2.sql` (procedimientos).

**B. Configuración** — edita `UniversidadExam/DBManager/src/main/resources/db.properties`:
```
db.hostMySQL=<tu-endpoint-rds>
db.esquema=schemaCaso2
db.usuario=admin
db.password=<tu-password>
db.puertoMySQL=3306
```

**C. Backend** — desde `UniversidadExam/`:
```
mvn clean package
```
Genera dos WAR:
- `RestServices/target/RestServicesCaso2.war`
- `SoapServices/target/SoapServicesCaso2.war`
Despliega ambos en **GlassFish**.

**D. Pruebas rápidas de humo (antes de tocar el front):**
- REST: `http://localhost:8080/RestServicesCaso2/webresources/facultades/ping`
- REST datos: `http://localhost:8080/RestServicesCaso2/webresources/facultades`
- SOAP: `http://localhost:8080/SoapServicesCaso2/FacultadWS?wsdl`

**E. Front** — abre `Caso2FrontCursos` en Visual Studio, corre (F5) y entra a `/universidad`.
Debe listar cursos/estudiantes/etc. desde REST. Si el backend está en EC2, cambia `localhost`
por la IP pública en `Managers/UniversidadRSManager.cs` (constante `BASE_URL`).

---

## 5. EL FRONT EN DETALLE (tu punto débil)

### 5.1 Cómo encajan los archivos
- **`Universidad.razor`** = solo pinta. Inyecta un *Manager* y llama `Manager.ListarCursos()`, etc.
- **`Managers/UniversidadRSManager.cs`** = por cada operación arma la URL y usa `HttpClientUtils`.
- **`Utils/HttpClientUtils.cs`** = cliente REST genérico (`get/post/put/delete`) con Newtonsoft.
- **`DTO/UniversidadDTO.cs`** = clases del front; los `[JsonProperty("...")]` deben coincidir con
  el nombre del campo en el JSON que manda Java (por eso `Id` ↔ `"id"`, `Nombre` ↔ `"nombre"`).
- **`Program.cs`** = registra el manager que se puede inyectar.

### 5.2 Camino REST (ya activo, no generas nada)
Ya dejé `Program.cs` registrando `UniversidadRSManager` y `Universidad.razor` inyectándolo.
Solo necesitas el `RestServicesCaso2.war` desplegado. **Esto es lo que corre por defecto.**

### 5.3 Camino SOAP (pregunta 1) — cómo activarlo
El manager SOAP está como `Managers/UniversidadSOAPManager.cs.txt` (fuera del build, para que
el proyecto compile sin él). Para usarlo:
1. Renómbralo a `UniversidadSOAPManager.cs`.
2. En VS: clic derecho al proyecto → **Add → Connected Service / Service Reference (WCF)** →
   pega el WSDL, p.ej. `http://localhost:8080/SoapServicesCaso2/FacultadWS?wsdl`.
   **El namespace de la referencia debe llamarse `FacultadWSReference`** (así lo espera el manager);
   repite por cada entidad que te pidan (`EstudianteWSReference`, `CursoWSReference`, ...).
3. En `Program.cs` descomenta `AddScoped<UniversidadSOAPManager>();`.
4. En `Universidad.razor` cambia `@inject UniversidadRSManager Manager` por
   `@inject UniversidadSOAPManager Manager`. **La página no cambia más**: ambos managers exponen
   los mismos métodos (`ListarFacultads`, `BuscarFacultadPorId`, `InsertarFacultad`, ...).

> En el examen normalmente piden **1–2 entidades**, así que solo agregas esas Connected Services;
> no las 14. Compara con tu **Lab 11**, que ya tiene un `ServiceReference1` funcionando de ejemplo.

---

## 6. Cómo mapearlo a lo que pida el enunciado

**Si piden un servicio SOAP para la entidad X:**
- Backend: mira `SoapServices/.../XWS.java` (anotaciones `@WebService`, `@WebMethod`, `@WebParam`),
  su DTO en `soapservices/dto/XDTO.java` y el `UniversidadSoapMapper` (Model↔DTO).
- Front: Connected Service `XWSReference` + método en `UniversidadSOAPManager`.

**Si piden un servicio REST para la entidad X:**
- Backend: mira `RestServices/.../XRS.java` (`@Path`, `@GET/@POST/@PUT/@DELETE`, `@PathParam`).
- Front: método en `UniversidadRSManager` con `HttpClientUtils<...>.get/post(...)`.

**Transacciones (si el enunciado lo pide):** inícialas y ciérralas en el **BO** (capa negocio),
nunca en el DAO. En `DBManager` tienes `iniciarTransaccion()`, `ejecutarProcedimientoTransaccion()`,
`confirmarTransaccion()`, `cancelarTransaccion()`. El BO coordina varios DAOs y hace commit/rollback.

**Datos de conexión:** siempre desde `db.properties` (archivo externo), nunca hardcodeados.

---

## 7. Relación con tus laboratorios (ya corregidos al 100%)

- **Lab 11 (SOAP, Pokémon)** = el ejemplo **mínimo** de SOAP de punta a punta. Ya te dejé el front
  consumiendo el WS (`PokemonWSManager` mapea el DTO del servicio al modelo del front). Úsalo para
  recordar cómo se agrega y consume un `ServiceReference`.
- **Lab 14 (REST, FIFA)** = el ejemplo **mínimo** de REST de punta a punta (arreglé el error de
  compilación y el consumo desde `SeleccionStateService` + `HttpClientUtils`).

Los dos labs son la versión "pequeña" del mismo patrón que ves aquí en grande. Si te pierdes en
esta plantilla, vuelve al lab correspondiente.

---

## 8. Checklist antes/durante el examen

- [ ] MySQL arriba, accesible desde cualquier IP (AWS Academy), `schemaCaso2` con DDL+DML+procedimientos.
- [ ] `db.properties` con tu endpoint/usuario/clave.
- [ ] `mvn clean package` OK → 2 WAR desplegados en GlassFish.
- [ ] Smoke test: `.../facultades/ping` y `...FacultadWS?wsdl` responden.
- [ ] Front `/universidad` lista datos por REST.
- [ ] Para SOAP: renombrar manager, agregar Connected Service `<Entidad>WSReference`, cambiar `@inject`.
- [ ] Implementar **solo** lo que pide el enunciado (el examen penaliza código de más).
