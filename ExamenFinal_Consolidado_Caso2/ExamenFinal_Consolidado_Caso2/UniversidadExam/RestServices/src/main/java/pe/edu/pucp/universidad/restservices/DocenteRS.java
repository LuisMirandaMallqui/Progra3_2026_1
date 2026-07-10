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

import pe.edu.pucp.universidad.business.DocenteBO;
import pe.edu.pucp.universidad.business.implementsBO.DocenteImplementsBO;
import pe.edu.pucp.universidad.model.Docente;

import java.util.List;

@Path("docentes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DocenteRS {

    private DocenteBO docenteBO;

    public DocenteRS() {
        docenteBO = new DocenteImplementsBO();
    }

    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "REST de docentes funcionando";
    }

    @GET
    public List<Docente> listarTodos() {
        return docenteBO.listarTodos();
    }

    @GET
    @Path("{id}")
    public Docente buscarPorId(@PathParam("id") int id) {
        return docenteBO.buscarPorId(id);
    }

    @POST
    public int insertar(Docente elemento) {
        return docenteBO.insertar(elemento);
    }

    @PUT
    @Path("{id}")
    public int modificar(@PathParam("id") int id, Docente elemento) {
        elemento.setId((long) id);
        return docenteBO.modificar(elemento);
    }

    @DELETE
    @Path("{id}")
    public int eliminar(@PathParam("id") int id) {
        return docenteBO.eliminar(id);
    }

}
