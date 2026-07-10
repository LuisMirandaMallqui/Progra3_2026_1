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

import pe.edu.pucp.universidad.business.EstudianteBO;
import pe.edu.pucp.universidad.business.implementsBO.EstudianteImplementsBO;
import pe.edu.pucp.universidad.model.Estudiante;

import java.util.List;

@Path("estudiantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstudianteRS {

    private EstudianteBO estudianteBO;

    public EstudianteRS() {
        estudianteBO = new EstudianteImplementsBO();
    }

    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "REST de estudiantes funcionando";
    }

    @GET
    public List<Estudiante> listarTodos() {
        return estudianteBO.listarTodos();
    }

    @GET
    @Path("{id}")
    public Estudiante buscarPorId(@PathParam("id") int id) {
        return estudianteBO.buscarPorId(id);
    }

    @POST
    public int insertar(Estudiante elemento) {
        return estudianteBO.insertar(elemento);
    }

    @PUT
    @Path("{id}")
    public int modificar(@PathParam("id") int id, Estudiante elemento) {
        elemento.setId((long) id);
        return estudianteBO.modificar(elemento);
    }

    @DELETE
    @Path("{id}")
    public int eliminar(@PathParam("id") int id) {
        return estudianteBO.eliminar(id);
    }

}
