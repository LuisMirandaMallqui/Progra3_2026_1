package pe.edu.pucp.testsoft.services.soap.academico;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import pe.edu.pucp.testsoft.business.alumno.bo.IAlumnoBO;
import pe.edu.pucp.testsoft.business.alumno.impl.AlumnoBOImpl;
import pe.edu.pucp.testsoft.model.Alumno;

import java.util.List;

// Estilo del profe Paz (AreaWS): el BO se instancia en el constructor; cada operación
// envuelve la llamada en try/catch e imprime el error. Retornos crudos (List/int/DTO).
// WSDL: http://localhost:8080/TestSoftServicios/AlumnoWS?wsdl
@WebService(serviceName = "AlumnoWS", targetNamespace = "http://services.testsoft.pucp.edu.pe/")
public class AlumnoWS {

    private IAlumnoBO alumnoBO;

    public AlumnoWS() {
        alumnoBO = new AlumnoBOImpl();
    }

    @WebMethod(operationName = "listarAlumnosTodos")
    public List<Alumno> listarAlumnosTodos() {
        List<Alumno> alumnos = null;
        try { alumnos = alumnoBO.listarTodos(); }
        catch (Exception ex) { System.out.println(ex.getMessage()); }
        return alumnos;
    }

    @WebMethod(operationName = "buscarAlumnosPorNombreApellido")
    public List<Alumno> buscarAlumnosPorNombreApellido(@WebParam(name = "texto") String texto) {
        List<Alumno> alumnos = null;
        try { alumnos = alumnoBO.buscarPorNombreApellido(texto); }
        catch (Exception ex) { System.out.println(ex.getMessage()); }
        return alumnos;
    }

    @WebMethod(operationName = "buscarAlumnoPorId")
    public Alumno buscarAlumnoPorId(@WebParam(name = "idAlumno") int idAlumno) {
        Alumno alumno = null;
        try { alumno = alumnoBO.buscarPorId(idAlumno); }
        catch (Exception ex) { System.out.println(ex.getMessage()); }
        return alumno;
    }

    @WebMethod(operationName = "insertarAlumno")
    public int insertarAlumno(@WebParam(name = "alumno") Alumno alumno) {
        int resultado = 0;
        try { resultado = alumnoBO.insertar(alumno); }
        catch (Exception ex) { System.out.println(ex.getMessage()); }
        return resultado;
    }

    @WebMethod(operationName = "modificarAlumno")
    public int modificarAlumno(@WebParam(name = "alumno") Alumno alumno) {
        int resultado = 0;
        try { resultado = alumnoBO.modificar(alumno); }
        catch (Exception ex) { System.out.println(ex.getMessage()); }
        return resultado;
    }

    @WebMethod(operationName = "eliminarAlumno")
    public int eliminarAlumno(@WebParam(name = "idAlumno") int idAlumno) {
        int resultado = 0;
        try { resultado = alumnoBO.eliminar(idAlumno); }
        catch (Exception ex) { System.out.println(ex.getMessage()); }
        return resultado;
    }
}
