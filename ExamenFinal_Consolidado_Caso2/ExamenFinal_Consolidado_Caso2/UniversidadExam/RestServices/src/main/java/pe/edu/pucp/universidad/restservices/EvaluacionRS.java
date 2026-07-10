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

import pe.edu.pucp.universidad.business.EvaluacionBO;
import pe.edu.pucp.universidad.business.implementsBO.EvaluacionImplementsBO;
import pe.edu.pucp.universidad.model.Evaluacion;

import java.util.List;

@Path("evaluaciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EvaluacionRS {

    private EvaluacionBO evaluacionBO;

    public EvaluacionRS() {
        evaluacionBO = new EvaluacionImplementsBO();
    }

    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "REST de evaluaciones funcionando";
    }

    @GET
    public List<Evaluacion> listarTodos() {
        return evaluacionBO.listarTodos();
    }

    @GET
    @Path("{id}")
    public Evaluacion buscarPorId(@PathParam("id") int id) {
        return evaluacionBO.buscarPorId(id);
    }

    @POST
    public int insertar(Evaluacion elemento) {
        return evaluacionBO.insertar(elemento);
    }

    @PUT
    @Path("{id}")
    public int modificar(@PathParam("id") int id, Evaluacion elemento) {
        elemento.setId((long) id);
        return evaluacionBO.modificar(elemento);
    }

    @DELETE
    @Path("{id}")
    public int eliminar(@PathParam("id") int id) {
        return evaluacionBO.eliminar(id);
    }

}
