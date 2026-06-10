package pe.edu.pucp.transitsoft.ws;


import com.mysql.cj.xdevapi.ExprUtil;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import pe.edu.pucp.transitsoft.business.PropietarioBO;
import pe.edu.pucp.transitsoft.model.PropietarioDTO;


@Path("Propietario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Propietario {
    
    private PropietarioBO propietarioBO;

    public Propietario() {
        this.propietarioBO = new PropietarioBO();
    }

    @POST
    public Response insertar(PropietarioDTO propietarioDTO) {
        Integer respuesta = this.propietarioBO.insertar(propietarioDTO);
        if (respuesta == 0) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.CREATED).entity(propietarioDTO).build();
    }

    @PUT
    public Response modificar(PropietarioDTO propietarioDTO) {
        Integer respuesta = this.propietarioBO.modificar(propietarioDTO);
        if (respuesta == 0) {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
        return Response.ok(propietarioDTO).build();
    }

    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") Integer propietarioId) {
        Integer respuesta = this.propietarioBO.eliminar(propietarioId);
        if (respuesta > 0) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public ArrayList<PropietarioDTO> listarTodos() {
        return this.propietarioBO.listarTodos();
    }

    @GET
    @Path("{id}")
    public Response obtenerPorId(@PathParam("id") Integer propietarioId) {
        PropietarioDTO propietario = this.propietarioBO.obtenerPorId(propietarioId);
        if (propietario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(propietario).build();
    }
}
