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

import pe.edu.pucp.universidad.business.HorarioCursoDetBO;
import pe.edu.pucp.universidad.business.implementsBO.HorarioCursoDetImplementsBO;
import pe.edu.pucp.universidad.model.HorarioCursoDet;

import java.util.List;

@Path("horarios-curso-det")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HorarioCursoDetRS {

    private HorarioCursoDetBO horarioCursoDetBO;

    public HorarioCursoDetRS() {
        horarioCursoDetBO = new HorarioCursoDetImplementsBO();
    }

    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "REST de horarios-curso-det funcionando";
    }

    @GET
    public List<HorarioCursoDet> listarTodos() {
        return horarioCursoDetBO.listarTodos();
    }

    @GET
    @Path("{id}")
    public HorarioCursoDet buscarPorId(@PathParam("id") int id) {
        return horarioCursoDetBO.buscarPorId(id);
    }

    @POST
    public int insertar(HorarioCursoDet elemento) {
        return horarioCursoDetBO.insertar(elemento);
    }

    @PUT
    @Path("{id}")
    public int modificar(@PathParam("id") int id, HorarioCursoDet elemento) {
        elemento.setId((long) id);
        return horarioCursoDetBO.modificar(elemento);
    }

    @DELETE
    @Path("{id}")
    public int eliminar(@PathParam("id") int id) {
        return horarioCursoDetBO.eliminar(id);
    }

}
