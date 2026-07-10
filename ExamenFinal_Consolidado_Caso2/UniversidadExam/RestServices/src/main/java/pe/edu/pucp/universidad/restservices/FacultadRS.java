package pe.edu.pucp.universidad.restservices;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;

import pe.edu.pucp.universidad.business.FacultadBO;
import pe.edu.pucp.universidad.business.implementsBO.FacultadImplementsBO;
import pe.edu.pucp.universidad.model.Facultad;

import java.util.List;

@Path("facultades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FacultadRS {

    private FacultadBO facultadBO;

    public FacultadRS() {
        facultadBO = new FacultadImplementsBO();
    }

    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "REST de facultades funcionando";
    }

    @GET
    public List<Facultad> listarTodos() {
        return facultadBO.listarTodos();
    }

    @GET
    @Path("{id}")
    public Facultad buscarPorId(@PathParam("id") int id) {
        return facultadBO.buscarPorId(id);
    }

    @POST
    public int insertar(Facultad elemento) {
        return facultadBO.insertar(elemento);
    }

    @PUT
    @Path("{id}")
    public int modificar(@PathParam("id") int id, Facultad elemento) {
        elemento.setId((long) id);
        return facultadBO.modificar(elemento);
    }

    @DELETE
    @Path("{id}")
    public int eliminar(@PathParam("id") int id) {
        return facultadBO.eliminar(id);
    }

}
