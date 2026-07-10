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

import pe.edu.pucp.universidad.business.DepartamentoBO;
import pe.edu.pucp.universidad.business.implementsBO.DepartamentoImplementsBO;
import pe.edu.pucp.universidad.model.Departamento;

import java.util.List;

@Path("departamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepartamentoRS {

    private DepartamentoBO departamentoBO;

    public DepartamentoRS() {
        departamentoBO = new DepartamentoImplementsBO();
    }

    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "REST de departamentos funcionando";
    }

    @GET
    public List<Departamento> listarTodos() {
        return departamentoBO.listarTodos();
    }

    @GET
    @Path("{id}")
    public Departamento buscarPorId(@PathParam("id") int id) {
        return departamentoBO.buscarPorId(id);
    }

    @POST
    public int insertar(Departamento elemento) {
        return departamentoBO.insertar(elemento);
    }

    @PUT
    @Path("{id}")
    public int modificar(@PathParam("id") int id, Departamento elemento) {
        elemento.setId((long) id);
        return departamentoBO.modificar(elemento);
    }

    @DELETE
    @Path("{id}")
    public int eliminar(@PathParam("id") int id) {
        return departamentoBO.eliminar(id);
    }

}
