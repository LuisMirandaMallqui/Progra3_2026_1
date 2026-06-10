package pe.edu.pucp.testsoft.services.rest.academico;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pe.edu.pucp.testsoft.business.alumno.bo.IAlumnoBO;
import pe.edu.pucp.testsoft.business.alumno.impl.AlumnoBOImpl;
import pe.edu.pucp.testsoft.model.Alumno;

import java.util.ArrayList;
import java.util.List;

// Estilo del profe Paz (AreaRS): @Path con sufijo "RS", BO en el constructor,
// retornos crudos (List/int/DTO), try/catch con println.
// Rutas (base /TestSoftServicios/webresources):
//   GET    /AlumnoRS                 -> listar
//   GET    /AlumnoRS/{idAlumno}      -> por id
//   GET    /AlumnoRS/buscar?texto=.. -> filtrar
//   POST   /AlumnoRS  (body JSON)    -> insertar (devuelve id)
//   PUT    /AlumnoRS  (body JSON)    -> modificar (devuelve filas)
//   DELETE /AlumnoRS/{idAlumno}      -> eliminar
//
// PROBAR EN POSTMAN (REST se prueba aquí, NO SOAP):
//   - Elegir verbo + URL. Para POST/PUT: pestaña Body -> raw -> JSON.
//   - POST body de ejemplo (nombres = getters en minúscula):
//       { "codigo":"A003", "nombre":"Ana", "apellidos":"Torres",
//         "correo":"ana.torres@pucp.pe", "estado":"A" }
//   - 415 = olvidaste Body raw/JSON ; 404 = ruta/WAR mal ; 500 = revisar log (BD).
@Path("AlumnoRS")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlumnoRS {

    private IAlumnoBO alumnoBO;

    public AlumnoRS() {
        this.alumnoBO = new AlumnoBOImpl();
    }

    @GET
    public List<Alumno> listarAlumnosTodos() {
        List<Alumno> alumnos = new ArrayList<>();
        try { alumnos = alumnoBO.listarTodos(); }
        catch (Exception ex) { System.out.println(ex.getMessage()); }
        return alumnos;
    }

    @GET
    @Path("buscar")
    public List<Alumno> buscarAlumnosPorNombreApellido(@QueryParam("texto") String texto) {
        List<Alumno> alumnos = new ArrayList<>();
        try { alumnos = alumnoBO.buscarPorNombreApellido(texto); }
        catch (Exception ex) { System.out.println(ex.getMessage()); }
        return alumnos;
    }

    @GET
    @Path("{idAlumno}")
    public Alumno buscarAlumnoPorId(@PathParam("idAlumno") int idAlumno) {
        Alumno alumno = null;
        try { alumno = alumnoBO.buscarPorId(idAlumno); }
        catch (Exception ex) { System.out.println(ex.getMessage()); }
        return alumno;
    }

    @POST
    public int insertarAlumno(Alumno alumno) {        // el objeto llega como JSON en el body
        int resultado = 0;
        try { resultado = alumnoBO.insertar(alumno); }
        catch (Exception ex) { System.out.println(ex.getMessage()); }
        return resultado;
    }

    @PUT
    public int modificarAlumno(Alumno alumno) {
        int resultado = 0;
        try { resultado = alumnoBO.modificar(alumno); }
        catch (Exception ex) { System.out.println(ex.getMessage()); }
        return resultado;
    }

    @DELETE
    @Path("{idAlumno}")
    public int eliminarAlumno(@PathParam("idAlumno") int idAlumno) {
        int resultado = 0;
        try { resultado = alumnoBO.eliminar(idAlumno); }
        catch (Exception ex) { System.out.println(ex.getMessage()); }
        return resultado;
    }
}
