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

import pe.edu.pucp.universidad.business.HorarioCursoBO;
import pe.edu.pucp.universidad.business.implementsBO.HorarioCursoImplementsBO;
import pe.edu.pucp.universidad.model.HorarioCurso;

import java.util.List;

@Path("horarios-curso")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HorarioCursoRS {

    private HorarioCursoBO horarioCursoBO;

    public HorarioCursoRS() {
        horarioCursoBO = new HorarioCursoImplementsBO();
    }

    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "REST de horarios-curso funcionando";
    }

    @GET
    public List<HorarioCurso> listarTodos() {
        return horarioCursoBO.listarTodos();
    }

    @GET
    @Path("{id}")
    public HorarioCurso buscarPorId(@PathParam("id") int id) {
        return horarioCursoBO.buscarPorId(id);
    }

    @POST
    public int insertar(HorarioCurso elemento) {
        return horarioCursoBO.insertar(elemento);
    }

    @PUT
    @Path("{id}")
    public int modificar(@PathParam("id") int id, HorarioCurso elemento) {
        elemento.setId((long) id);
        return horarioCursoBO.modificar(elemento);
    }

    @DELETE
    @Path("{id}")
    public int eliminar(@PathParam("id") int id) {
        return horarioCursoBO.eliminar(id);
    }

}
