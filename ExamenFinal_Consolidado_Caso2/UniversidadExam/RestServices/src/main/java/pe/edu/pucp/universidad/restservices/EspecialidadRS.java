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

import pe.edu.pucp.universidad.business.EspecialidadBO;
import pe.edu.pucp.universidad.business.implementsBO.EspecialidadImplementsBO;
import pe.edu.pucp.universidad.model.Especialidad;

import java.util.List;

@Path("especialidades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EspecialidadRS {

    private EspecialidadBO especialidadBO;

    public EspecialidadRS() {
        especialidadBO = new EspecialidadImplementsBO();
    }

    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "REST de especialidades funcionando";
    }

    @GET
    public List<Especialidad> listarTodos() {
        return especialidadBO.listarTodos();
    }

    @GET
    @Path("{id}")
    public Especialidad buscarPorId(@PathParam("id") int id) {
        return especialidadBO.buscarPorId(id);
    }

    @POST
    public int insertar(Especialidad elemento) {
        return especialidadBO.insertar(elemento);
    }

    @PUT
    @Path("{id}")
    public int modificar(@PathParam("id") int id, Especialidad elemento) {
        elemento.setId((long) id);
        return especialidadBO.modificar(elemento);
    }

    @DELETE
    @Path("{id}")
    public int eliminar(@PathParam("id") int id) {
        return especialidadBO.eliminar(id);
    }

}
