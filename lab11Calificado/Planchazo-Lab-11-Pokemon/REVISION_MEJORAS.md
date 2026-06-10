# Revisión y mejoras — Planchazo Lab 11 (Pokémon)

Revisé el proyecto de tu amigo contra (a) el correo del profe, (b) los repos de Paz/Corcuera,
(c) la forma de evaluar del Lab7 (Pokémon) y del lab final. Abajo: qué estaba bien, qué faltaba,
qué cambié (todo comentado en el código con `MEJORA`) y qué debes hacer tú antes de entregar.

---

## TL;DR — lo más importante

> **El proyecto SOLO tenía SOAP. El correo pide SOAP *y* REST (REST probado en Postman).**
> Sin REST, en el lab te quedas sin la mitad del puntaje. Esa fue la mejora #1.

---

## 1. Lo que ya estaba bien (no lo toqué)

- Arquitectura por capas correcta: Model, DBManager, DAO, BO, WS, Tester.
- `DBManager` es el patrón Singleton de Paz (con transacciones y `ResultadoConsulta`).
- SOAP funcionando y **consumido en el front con *Add Service Reference*** (lo oficial), con
  conversión DTO↔proxy en `PokemonAppService` y llamadas `async`. Bien hecho.
- Reglas de negocio reales en los BO: no insertar pokémon si el tipo no existe, no duplicar tipo.
  (Esto importa: en el lab final bajaron nota por "BO sin validaciones, solo delegación".)
- Manejo de `enum` (`EstadoEvolutivo`) leído/escrito como texto.

## 2. Lo que faltaba o estaba flojo → lo que cambié

| # | Problema | Por qué importa (cómo evalúan) | Qué hice |
|---|----------|-------------------------------|----------|
| 1 | **No había REST** | El correo pide ejercicio REST probado en Postman | Agregué `services/rest/`: `RestApplication`, `PokemonRS`, `TipoPokemonRS` + dependencia `jakarta.ws.rs-api` en el `pom` del PokeWS |
| 2 | `listarPokemones` no traía el **nombre del tipo** (solo `fid_tipo`) | En el lab final restaron por "no incluir la info relacionada" (ej. los vehículos del propietario). Acá el análogo es mostrar el **nombre del tipo**, no un número | Nuevo `Procedimientos_Mejoras.sql`: `LISTAR/BUSCAR` con **JOIN** a `tipo_pokemon` (`nombre_tipo`); el DAO ahora lo carga en `stringTipoPokemon` |
| 3 | `eliminar` devolvía `0` (CRUD incompleto) | El CRUD completo suele valer puntos | Agregué SPs `ELIMINAR_POKEMON` / `ELIMINAR_TIPO_POKEMON` e implementé `eliminar` en los DAO |
| 4 | DAO guardaba `con/cs/rs` como **campos de instancia** | En un WS llegan varias peticiones a la vez → **condición de carrera** (datos cruzados/errores intermitentes) | Reescribí ambos DAO usando los métodos del `DBManager` (`ejecutarProcedimiento`/`...Lectura`) con **variables locales**, como el patrón de Paz |
| 5 | SOAP no exponía `modificar`/`eliminar` | CRUD incompleto desde el front | Agregué `modificarPokemon/eliminarPokemon` y `modificarTipoPokemon/eliminarTipoPokemon` a los WS |
| 6 | `BO.modificar` de Pokémon no resolvía el tipo por nombre | El front manda el **nombre** del tipo (no el id); sin esto, modificar mandaría `idTipo=0` | `PokemonImplementsBO.modificar` ahora resuelve el tipo por nombre, igual que `insertar` |
| 7 | URL MySQL solo `?useSSL=false` | En AWS RDS suele fallar con *"Public Key Retrieval is not allowed"* | Agregué `&allowPublicKeyRetrieval=true` |
| 8 | El SOAP **no usaba `@WebParam`** | Sin él, los parámetros salen como `arg0`, `arg1`... (feo en `filtrarPokemones`). Es buena práctica ponerlo | Lo probé pero **lo dejé SIN `@WebParam` (arg0)** a propósito: tu `Reference.cs` ya estaba generado con `arg0` y el *Update* de VS cachea el WSDL viejo, así que para no romper el front lo mantengo en `arg0`. Si regeneras el proxy **desde cero** (borrar y volver a Add Service Reference), puedes reactivar `@WebParam(name="..")` |

Todos los cambios en el código están marcados con un comentario que empieza con **`MEJORA`** /
**`AGREGADO EN LA MEJORA`** / **`REFACTORIZADO EN LA MEJORA`** para que los ubiques rápido.

## 3. Detalles menores (no críticos, por si quieres pulir)

- El `pom` padre declara `jakarta.xml.ws-api` como dependencia **global** (todos los módulos). No
  hace falta en Model/DAO; idealmente solo en el WS. No rompe nada, solo es desprolijo.
- `db.properties` vive en el módulo DBManager. Funciona porque ese jar entra al WAR y queda en el
  classpath (por eso `getBundle("db")` lo encuentra). El profe Paz lo pone dentro del WS; ambas valen.
- Los nombres de campos del Model (`id_Pokemon`, `string_tipo_Pokemon`) son poco convencionales, pero
  como los getters están limpios (`getIdPokemon`), el WSDL/JSON salen bien (`idPokemon`, etc.). Cosmético.
- `filtrarPokemones` filtra en memoria (trae todos y filtra en Java). Para el lab está bien; un SP con
  `WHERE` dinámico sería más eficiente.

## 4. Qué tienes que hacer TÚ antes de entregar (pasos manuales)

1. **Base de datos:** corre, en orden, `ScriptsSQL/CreacionTablas.sql`, `Procedimientos.sql` y luego
   el nuevo **`Procedimientos_Mejoras.sql`** (redefine LISTAR/BUSCAR con JOIN y agrega los ELIMINAR).
2. **Rebuild + deploy** del backend en GlassFish (`mvn clean install` en `PokemonsProg`, desplegar el WAR).
3. **REST en Postman** — base `http://localhost:8080/PokeWS-1.0-SNAPSHOT/webresources`:
   - `GET  /PokemonRS` y `GET /PokemonRS/1`
   - `GET  /PokemonRS/filtrar?nombre=pika&idTipo=0&estado=BASICO`
   - `POST /PokemonRS` con body JSON:
     ```json
     { "stringTipoPokemon": "ELECTRICO", "nombre": "Pikachu", "altura": 0.4,
       "peso": 6.0, "estadoEvolutivo": "BASICO", "descripcion": "raton electrico" }
     ```
   - `PUT /PokemonRS` (mismo JSON + `"idPokemon": N`), `DELETE /PokemonRS/N`
   - Igual para `/TipoPokemonRS`.
4. **SOAP en el front:** agregué operaciones nuevas (`modificar`/`eliminar`). Si las quieres usar desde
   el front, **regenera el Connected Service** (click derecho → Update) para que el proxy las incluya.
   Lo que ya existía (listar/insertar/filtrar/buscar) sigue funcionando sin tocar nada.

## 5. Checklist rápido de cara a la nota

- [ ] SOAP: WSDL abre en `?wsdl`, operaciones CRUD completas. ✔ (ya estaba + agregadas)
- [ ] REST: 5 verbos probados en Postman. ✔ (agregado — pruébalo)
- [ ] Listar muestra el **nombre del tipo**, no el id. ✔ (JOIN)
- [ ] BO con validaciones reales (tipo existe, no duplicar). ✔
- [ ] `datos/db.properties` + driver dentro del WAR; MySQL con `allowPublicKeyRetrieval`. ✔
- [ ] Front consume SOAP por Add Service Reference. ✔
