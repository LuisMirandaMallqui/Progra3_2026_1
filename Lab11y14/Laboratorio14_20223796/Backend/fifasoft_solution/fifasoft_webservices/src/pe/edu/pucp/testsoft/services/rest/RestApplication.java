package pe.edu.pucp.testsoft.services.rest;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

// Activa JAX-RS. Base de TODOS los recursos REST:
//   http://localhost:8080/TestSoftServicios/webresources/...
@ApplicationPath("webresources")
public class RestApplication extends Application {
}
