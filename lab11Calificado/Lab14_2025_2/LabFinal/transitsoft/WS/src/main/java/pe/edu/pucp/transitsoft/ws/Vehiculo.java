/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import pe.edu.pucp.transitsoft.business.VehiculoBO;
import pe.edu.pucp.transitsoft.model.VehiculoDTO;

@Path("Vehiculo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Vehiculo {

    private VehiculoBO vehiculoBO;

    public Vehiculo() {
        this.vehiculoBO = new VehiculoBO();
    }

    @POST
    public Response insertar(VehiculoDTO vehiculoDTO) {
        Integer respuesta = this.vehiculoBO.insertar(vehiculoDTO);
        if (respuesta == 0) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.CREATED).entity(vehiculoDTO).build();
    }

    @PUT
    public Response modificar(VehiculoDTO vehiculoDTO) {
        Integer respuesta = this.vehiculoBO.modificar(vehiculoDTO);
        if (respuesta == 0) {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
        return Response.ok(vehiculoDTO).build();
    }

    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") Integer vehiculoId) {
        Integer respuesta = this.vehiculoBO.eliminar(vehiculoId);
        if (respuesta > 0) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public ArrayList<VehiculoDTO> listarTodos() {
        return this.vehiculoBO.listarTodos();
    }

    @GET
    @Path("{id}")
    public Response obtenerPorId(@PathParam("id") Integer vehiculoId) {
        VehiculoDTO vehiculo = this.vehiculoBO.obtenerPorId(vehiculoId);
        if (vehiculo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(vehiculo).build();
    }
}
