package pe.edu.pucp.universidad.restservices;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import pe.edu.pucp.universidad.manager.DBManager;
import java.sql.Connection;

@Path("test")
public class TestBDRS {
    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "REST Caso 2 funcionando";
    }

    @GET
    @Path("bd")
    @Produces(MediaType.TEXT_PLAIN)
    public String testBD() {
        try (Connection con = DBManager.getInstance().getConnection()) {
            return "Conexión OK: " + con.getCatalog();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error BD: " + ex.getMessage();
        }
    }
}
