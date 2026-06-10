# Front desde cero (Blazor) — orientado a consumir SOAP y REST

> Para alguien que no ha tocado el front. Objetivo: entender cómo una página Blazor pide datos
> al WS Java (por REST o SOAP) y los muestra. Todo con los archivos que ya están en el proyecto
> `GestionAlumnosWebApp`.

---

## 1. ¿Qué es Blazor y qué es un componente Razor?

- **Blazor** = framework de Microsoft para hacer páginas web usando **C# en vez de JavaScript**.
- Un **componente Razor** es un archivo `.razor` que mezcla **HTML** (lo que se ve) con **C#**
  (la lógica), en el mismo archivo. Es el equivalente a una "página".
- "Blazor **Server**" (lo que usa el curso): el C# corre en el servidor y la página se sincroniza
  por una conexión en vivo (SignalR). Por eso, si el servidor o el WS fallan, lo notas al instante.

Mentalmente: **un `.razor` = un pedazo de HTML + un bloque `@code { }` con variables y métodos C#.**

---

## 2. Estructura del proyecto (qué hace cada archivo)

```
GestionAlumnosWebApp/
├── Program.cs                 ← arranque: registra servicios (DI) y configura la app
├── appsettings.json           ← configuración (cadenas de conexión, URLs)
├── Components/
│   ├── App.razor              ← el HTML raíz (<html>, <head> con CSS/Bootstrap/FontAwesome)
│   ├── Routes.razor           ← el enrutador (decide qué página mostrar según la URL)
│   ├── _Imports.razor         ← `@using` globales para todas las páginas
│   ├── Layout/MainLayout.razor← plantilla común (menú, marco) — @Body es "aquí va la página"
│   └── Pages/                 ← las páginas navegables (.razor con @page "/ruta")
│       ├── Home.razor                 (lista de alumnos, vía BO local)
│       ├── AlumnoRegistro.razor       (alta/edición, vía BO local)
│       └── AlumnosWS.razor            (DEMO: lista/inserta vía WS REST y SOAP)
└── Services/
    ├── Rest/AlumnoRestClient.cs       ← cliente REST (HttpClient)
    └── Soap/AlumnoSoapClient.cs       ← cliente SOAP (demo; en el lab usarás Add Service Reference)
```

---

## 3. Anatomía de una página `.razor`

```razor
@page "/alumnos-ws"          @* 1) la URL que activa esta página *@
@rendermode InteractiveServer @* 2) sin esto, los botones (@onclick) NO hacen nada *@

@using GestionAlumnosModel.Alumno              @* 3) tipos que usa la página *@
@inject AlumnoRestClient Rest                  @* 4) pide un servicio (DI) y lo guarda en "Rest" *@

<h1>Alumnos</h1>                               @* 5) HTML normal *@
<button @onclick="Cargar">Listar</button>      @* 6) al click llama un método C# *@
@if (alumnos != null) {                        @* 7) C# embebido con @ *@
    @foreach (var a in alumnos) { <p>@a.Nombre</p> }
}

@code {                                        @* 8) el cerebro: variables y métodos C# *@
    private List<Alumno>? alumnos;
    protected override async Task OnInitializedAsync() { } // se ejecuta al cargar la página
    private async Task Cargar() { alumnos = await Rest.ListarTodos(); }
}
```

Conceptos clave del recuadro:
- **`@page "/ruta"`**: hace la página navegable en esa URL. Sin él, no existe como página.
- **`@rendermode InteractiveServer`**: enciende la interactividad. **Si falta, los `@onclick`/`@bind`
  no responden** (la página queda como HTML estático). Es el error #1 de principiante.
- **`@inject Tipo nombre`**: te entrega un objeto que registraste en `Program.cs` (ver §5).
- **`@code { }`**: aquí viven las variables (estado de la página) y los métodos.
- **`OnInitializedAsync()`**: método del ciclo de vida; corre **una vez, al cargar** la página. Aquí
  pides los datos iniciales.

### Directivas que más usarás
- `@bind="variable"` → enlaza un `<input>` con una variable C# en **doble sentido** (lo que escribes
  se guarda en la variable y viceversa).
- `@onclick="Metodo"` → ejecuta un método al hacer click. Si necesitas pasar parámetro:
  `@onclick="() => Editar(a.Id)"` (con lambda).
- `@if (...) { } else { }` y `@foreach (...) { }` → lógica/repetición dentro del HTML.

---

## 4. ¿De dónde saca los datos el front? Dos caminos

```
(a) LOCAL  :  Página → IAlumnoBO (Business C#) → DBManager C# → MySQL      [sin red]
(b) REMOTO :  Página → AlumnoRestClient/Soap   → WS Java (GlassFish) → MySQL  [por red]
```
- **(a)** es lo que hace tu `Home.razor` / `AlumnoRegistro.razor` actuales: el front tiene su propia
  copia de las capas en C# y habla con la BD directo.
- **(b)** es el tema del lab de WS: el front **NO** toca la BD; le pide los datos al **WS Java**.
  Eso es `AlumnosWS.razor` + los clientes en `Services/`.

Cambiar de (a) a (b) es solo cambiar a quién le inyectas y a quién le llamas. La página se ve igual.

---

## 5. Inyección de dependencias (DI) — el pegamento

Es el patrón por el que **no haces `new` de los servicios**: los registras una vez en `Program.cs` y
el framework te los entrega cuando una página los pide con `@inject`.

```csharp
// Program.cs
// REST: registra un cliente con HttpClient apuntando al WAR de servicios
builder.Services.AddHttpClient<AlumnoRestClient>(c =>
    c.BaseAddress = new Uri("http://localhost:8080/TestSoftServicios/"));

// SOAP (vía oficial): registra el proxy que generó "Add Service Reference"
builder.Services.AddScoped<AlumnoWSRef.AlumnoWSClient>();
```
```razor
@inject AlumnoRestClient Rest          @* el framework crea/entrega la instancia *@
@inject AlumnoWSRef.AlumnoWSClient Soap
```
Por qué importa: la página solo conoce "necesito un cliente de alumnos", no cómo se construye.
`Scoped` = una instancia por petición/usuario.

---

## 6. Flujo completo, paso a paso

### REST (listar)
1. Abres `/alumnos-ws` → corre `OnInitializedAsync` o pulsas "Listar".
2. La página llama `await Rest.ListarTodos()`.
3. `AlumnoRestClient` hace `GET http://localhost:8080/TestSoftServicios/webresources/AlumnoRS`.
4. El WS Java responde un **JSON** con la lista; `GetFromJsonAsync` lo convierte en `List<Alumno>`.
5. La página guarda la lista en una variable y el `@foreach` la pinta en la tabla.

### REST (insertar un objeto)
1. Llenas los `<input>` (con `@bind` quedan en un objeto `Alumno`).
2. Pulsas "Insertar" → `await Rest.Insertar(nuevo)`.
3. El cliente hace `POST .../AlumnoRS` con el alumno como **JSON en el body**.
4. El WS lo recibe, lo inserta y devuelve el **id** (un `int`). La página lo muestra.

### SOAP (insertar un objeto, con el proxy de Add Service Reference)
1. Creas el objeto del proxy `new AlumnoWSRef.Alumno { codigo=..., nombre=... }`.
2. `await Soap.insertarAlumnoAsync(alumno)`.
3. El proxy **convierte tu objeto a XML SOAP** y lo postea (tú no escribes XML).
4. GlassFish reconstruye el `Alumno` en Java, llama al BO, devuelve el id como XML; el proxy te lo
   entrega como `int`. (Detalle del XML y del `?Tester`: ver `GUIA_SOAP_REST.md` §4.3 y §4.4.)

---

## 7. Errores de front que cuestan tiempo (sacados de tus propias páginas)

- **Falta `@rendermode InteractiveServer`** → los botones no hacen nada. Ponlo siempre que haya
  `@onclick`/`@bind`.
- **`@onclick` a un método que devuelve un valor** (ej. `Alumno`) → no compila. Debe apuntar a
  `void` o `Task`.
- **Pasar parámetro sin lambda**: `@onclick="Editar(5)"` ❌ → `@onclick="() => Editar(5)"` ✅.
- **`@bind` en dos inputs a la misma variable** → uno pisa al otro. Cada campo a su propia variable.
- **Comparar `char` con string**: `estado.Equals("A")` ❌ (siempre false) → `estado == 'A'` ✅.
- **Llamar al WS sin try/catch** → si GlassFish está caído, se rompe el render. Envuelve siempre.
- **Olvidar registrar el servicio en `Program.cs`** → al `@inject` lanza "no service for type...".

---

## 8. Para el examen, en orden

1. Crea/abre el proyecto WA (Blazor) y agrega referencia al **Model** del front.
2. `Program.cs`: registra el cliente REST (`AddHttpClient`) y, si piden SOAP, haz **Add Service
   Reference** y registra el proxy.
3. Una página `Listar` (GET) y una `Registrar/Editar` (POST/PUT) — copia el patrón de `AlumnosWS.razor`.
4. `@rendermode InteractiveServer`, `@inject` el cliente, `OnInitializedAsync` para cargar, `@bind`
   en los inputs, `@onclick` en los botones, `try/catch` en cada llamada al WS.
