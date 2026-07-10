package pe.edu.pucp.testsoft.services.rest.fifasoft;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pe.edu.pucp.fifasoft.business.Seleccion.bo.ISeleccionBO;
import pe.edu.pucp.fifasoft.business.Seleccion.impl.SeleccionBOImpl;
import pe.edu.pucp.fifasoft.model.Seleccion;

import java.util.ArrayList;
import java.util.List;



@Path("SeleccionRS")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SeleccionRS {

    private ISeleccionBO seleccionBO;

    public SeleccionRS(){
        this.seleccionBO = new SeleccionBOImpl();
    }

    @GET
    public List<Seleccion> listarAlumnosTodos() {
        List<Seleccion> selecciones = new ArrayList<>();
        try { selecciones = seleccionBO.listarTodos(); }
        catch (Exception ex) { System.out.println(ex.getMessage()); }
        return selecciones;
    }
}
