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

import pe.edu.pucp.universidad.business.CursoBO;
import pe.edu.pucp.universidad.business.implementsBO.CursoImplementsBO;
import pe.edu.pucp.universidad.model.Curso;

import java.util.List;

@Path("cursos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CursoRS {

    private CursoBO cursoBO;

    public CursoRS() {
        cursoBO = new CursoImplementsBO();
    }

    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "REST de cursos funcionando";
    }

    @GET
    public List<Curso> listarTodos() {
        return cursoBO.listarTodos();
    }

    @GET
    @Path("{id}")
    public Curso buscarPorId(@PathParam("id") int id) {
        return cursoBO.buscarPorId(id);
    }

    @POST
    public int insertar(Curso elemento) {
        return cursoBO.insertar(elemento);
    }

    @PUT
    @Path("{id}")
    public int modificar(@PathParam("id") int id, Curso elemento) {
        elemento.setId((long) id);
        return cursoBO.modificar(elemento);
    }

    @DELETE
    @Path("{id}")
    public int eliminar(@PathParam("id") int id) {
        return cursoBO.eliminar(id);
    }

}
