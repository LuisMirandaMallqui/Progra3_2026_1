package pe.edu.pucp.universidad.restservices;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import pe.edu.pucp.universidad.business.MatriculaHorarioBO;
import pe.edu.pucp.universidad.business.implementsBO.MatriculaHorarioImplementsBO;
import pe.edu.pucp.universidad.model.MatriculaHorario;

@Path("matriculas-horarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MatriculaHorarioRS {
    private MatriculaHorarioBO bo;

    public MatriculaHorarioRS() {
        bo = new MatriculaHorarioImplementsBO();
    }

    @GET
    public List<MatriculaHorario> listarTodos() {
        return bo.listarTodos();
    }

    @GET
    @Path("{idMatricula}/{idHorarioCurso}")
    public MatriculaHorario buscarPorId(@PathParam("idMatricula") int idMatricula,
                                        @PathParam("idHorarioCurso") int idHorarioCurso) {
        return bo.buscarPorId(idMatricula, idHorarioCurso);
    }

    @POST
    public int insertar(MatriculaHorario elemento) {
        return bo.insertar(elemento);
    }

    @POST
    @Path("validado")
    public int insertarValidado(MatriculaHorario elemento) {
        return bo.insertarValidado(elemento);
    }

    @PUT
    @Path("{idMatricula}/{idHorarioCurso}")
    public int modificar(@PathParam("idMatricula") int idMatricula,
                         @PathParam("idHorarioCurso") int idHorarioCurso,
                         MatriculaHorario elemento) {
        if (elemento.getMatricula() != null) elemento.getMatricula().setId((long) idMatricula);
        if (elemento.getHorarioCurso() != null) elemento.getHorarioCurso().setId((long) idHorarioCurso);
        return bo.modificar(elemento);
    }

    @DELETE
    @Path("{idMatricula}/{idHorarioCurso}")
    public int eliminar(@PathParam("idMatricula") int idMatricula,
                        @PathParam("idHorarioCurso") int idHorarioCurso) {
        return bo.eliminar(idMatricula, idHorarioCurso);
    }
}
