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

import pe.edu.pucp.universidad.business.NotaBO;
import pe.edu.pucp.universidad.business.implementsBO.NotaImplementsBO;
import pe.edu.pucp.universidad.model.Nota;

import java.util.List;

@Path("notas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotaRS {

    private NotaBO notaBO;

    public NotaRS() {
        notaBO = new NotaImplementsBO();
    }

    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "REST de notas funcionando";
    }

    @GET
    public List<Nota> listarTodos() {
        return notaBO.listarTodos();
    }

    @GET
    @Path("{id}")
    public Nota buscarPorId(@PathParam("id") int id) {
        return notaBO.buscarPorId(id);
    }

    @POST
    public int insertar(Nota elemento) {
        return notaBO.insertar(elemento);
    }

    @PUT
    @Path("{id}")
    public int modificar(@PathParam("id") int id, Nota elemento) {
        elemento.setId((long) id);
        return notaBO.modificar(elemento);
    }

    @DELETE
    @Path("{id}")
    public int eliminar(@PathParam("id") int id) {
        return notaBO.eliminar(id);
    }

    @GET
    @Path("promedio/{idMatricula}/{idHorarioCurso}")
    @Produces(MediaType.TEXT_PLAIN)
    public double calcularPromedioFinal(@PathParam("idMatricula") int idMatricula,
                                        @PathParam("idHorarioCurso") int idHorarioCurso) {
        return notaBO.calcularPromedioFinal(idMatricula, idHorarioCurso);
    }

}
