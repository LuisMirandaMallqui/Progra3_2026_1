package pe.edu.pucp.testsoft.services.rest.fifasoft;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pe.edu.pucp.fifasoft.business.DirectorTecnico.bo.IDirectorTecnicoBO;
import pe.edu.pucp.fifasoft.business.DirectorTecnico.impl.DirectorTecnicoBOImpl;
import pe.edu.pucp.fifasoft.model.DirectorTecnico;

@Path("DirectorTecnicoRS")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DirectorTecnicoRS {
    private IDirectorTecnicoBO dtBO;

    public DirectorTecnicoRS(){
        this.dtBO = new DirectorTecnicoBOImpl();
    }

    @GET
    @Path("{idDirectorTecnico}")
    public DirectorTecnico buscarAlumnoPorId(@PathParam("idDirectorTecnico") int idDirectorTecnico) {
        DirectorTecnico dt = null;
        try { dt = dtBO.buscarPorId(idDirectorTecnico); }
        catch (Exception ex) { System.out.println(ex.getMessage()); }
        return dt;
    }
}
