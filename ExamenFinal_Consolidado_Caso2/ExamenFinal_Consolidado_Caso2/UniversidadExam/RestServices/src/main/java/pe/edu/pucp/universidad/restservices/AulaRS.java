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

import pe.edu.pucp.universidad.business.AulaBO;
import pe.edu.pucp.universidad.business.implementsBO.AulaImplementsBO;
import pe.edu.pucp.universidad.model.Aula;

import java.util.List;

@Path("aulas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AulaRS {

    private AulaBO aulaBO;

    public AulaRS() {
        aulaBO = new AulaImplementsBO();
    }

    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "REST de aulas funcionando";
    }

    @GET
    public List<Aula> listarTodos() {
        return aulaBO.listarTodos();
    }

    @GET
    @Path("{id}")
    public Aula buscarPorId(@PathParam("id") int id) {
        return aulaBO.buscarPorId(id);
    }

    @POST
    public int insertar(Aula elemento) {
        return aulaBO.insertar(elemento);
    }

    @PUT
    @Path("{id}")
    public int modificar(@PathParam("id") int id, Aula elemento) {
        elemento.setId((long) id);
        return aulaBO.modificar(elemento);
    }

    @DELETE
    @Path("{id}")
    public int eliminar(@PathParam("id") int id) {
        return aulaBO.eliminar(id);
    }

}
