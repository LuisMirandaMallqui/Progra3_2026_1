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

import pe.edu.pucp.universidad.business.MatriculaBO;
import pe.edu.pucp.universidad.business.implementsBO.MatriculaImplementsBO;
import pe.edu.pucp.universidad.model.Matricula;

import java.util.List;

@Path("matriculas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MatriculaRS {

    private MatriculaBO matriculaBO;

    public MatriculaRS() {
        matriculaBO = new MatriculaImplementsBO();
    }

    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "REST de matriculas funcionando";
    }

    @GET
    public List<Matricula> listarTodos() {
        return matriculaBO.listarTodos();
    }

    @GET
    @Path("{id}")
    public Matricula buscarPorId(@PathParam("id") int id) {
        return matriculaBO.buscarPorId(id);
    }

    @POST
    public int insertar(Matricula elemento) {
        return matriculaBO.insertar(elemento);
    }

    @PUT
    @Path("{id}")
    public int modificar(@PathParam("id") int id, Matricula elemento) {
        elemento.setId((long) id);
        return matriculaBO.modificar(elemento);
    }

    @DELETE
    @Path("{id}")
    public int eliminar(@PathParam("id") int id) {
        return matriculaBO.eliminar(id);
    }

}
