package pe.edu.pucp.universidad.restservices;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import pe.edu.pucp.universidad.business.CursoPrerrequisitoBO;
import pe.edu.pucp.universidad.business.implementsBO.CursoPrerrequisitoImplementsBO;
import pe.edu.pucp.universidad.model.CursoPrerrequisito;

@Path("cursos-prerrequisitos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CursoPrerrequisitoRS {
    private CursoPrerrequisitoBO bo;

    public CursoPrerrequisitoRS() {
        bo = new CursoPrerrequisitoImplementsBO();
    }

    @GET
    public List<CursoPrerrequisito> listarTodos() {
        return bo.listarTodos();
    }

    @GET
    @Path("{idCurso}/{idCursoPrerreq}")
    public CursoPrerrequisito buscarPorId(@PathParam("idCurso") int idCurso,
                                          @PathParam("idCursoPrerreq") int idCursoPrerreq) {
        return bo.buscarPorId(idCurso, idCursoPrerreq);
    }

    @POST
    public int insertar(CursoPrerrequisito elemento) {
        return bo.insertar(elemento);
    }

    @PUT
    @Path("{idCurso}/{idCursoPrerreq}")
    public int modificar(@PathParam("idCurso") int idCurso,
                         @PathParam("idCursoPrerreq") int idCursoPrerreq,
                         CursoPrerrequisito elemento) {
        if (elemento.getCurso() != null) elemento.getCurso().setId((long) idCurso);
        if (elemento.getCursoPrerreq() != null) elemento.getCursoPrerreq().setId((long) idCursoPrerreq);
        return bo.modificar(elemento);
    }

    @DELETE
    @Path("{idCurso}/{idCursoPrerreq}")
    public int eliminar(@PathParam("idCurso") int idCurso,
                        @PathParam("idCursoPrerreq") int idCursoPrerreq) {
        return bo.eliminar(idCurso, idCursoPrerreq);
    }
}
