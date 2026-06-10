package pe.edu.pucp.testsoft.services.rest.academico;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pe.edu.pucp.testsoft.business.examen.bo.IExamenBO;
import pe.edu.pucp.testsoft.business.examen.impl.ExamenBOImpl;
import pe.edu.pucp.testsoft.model.Examen;

import java.util.ArrayList;
import java.util.List;

// Recurso de Examen: filtro por alumno (@QueryParam) y actualización de resultado.
//   GET /ExamenRS/pendientes?idAlumno=1
//   GET /ExamenRS/{idExamen}
//   PUT /ExamenRS/{idExamen}/resultado?estado=RESUELTO&nota=16
@Path("ExamenRS")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExamenRS {

    private IExamenBO examenBO;

    public ExamenRS() {
        this.examenBO = new ExamenBOImpl();
    }

    @GET
    @Path("pendientes")
    public List<Examen> listarPendientesPorAlumno(@QueryParam("idAlumno") int idAlumno) {
        List<Examen> examenes = new ArrayList<>();
        try { examenes = examenBO.listarPendientesPorAlumno(idAlumno); }
        catch (Exception ex) { System.out.println(ex.getMessage()); }
        return examenes;
    }

    @GET
    @Path("{idExamen}")
    public Examen buscarExamenPorId(@PathParam("idExamen") int idExamen) {
        Examen examen = null;
        try { examen = examenBO.buscarPorId(idExamen); }
        catch (Exception ex) { System.out.println(ex.getMessage()); }
        return examen;
    }

    @PUT
    @Path("{idExamen}/resultado")
    public int actualizarResultado(@PathParam("idExamen") int idExamen,
                                   @QueryParam("estado") String estado,
                                   @QueryParam("nota") int nota) {
        int resultado = 0;
        try { resultado = examenBO.actualizarResultado(idExamen, estado, nota); }
        catch (Exception ex) { System.out.println(ex.getMessage()); }
        return resultado;
    }
}
