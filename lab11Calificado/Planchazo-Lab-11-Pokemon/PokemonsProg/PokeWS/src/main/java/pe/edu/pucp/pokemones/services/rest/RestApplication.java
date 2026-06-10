package pe.edu.pucp.pokemones.services.rest;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

// ============================================================================
// AGREGADO EN LA MEJORA: faltaba REST por completo (el correo lo pide y se
// prueba en Postman). Esta clase activa JAX-RS. Base de TODAS las rutas REST:
//   http://localhost:8080/PokeWS-1.0-SNAPSHOT/webresources/...
// (el context path es PokeWS-1.0-SNAPSHOT porque el pom no define <finalName>)
// ============================================================================
@ApplicationPath("webresources")
public class RestApplication extends Application {
}
