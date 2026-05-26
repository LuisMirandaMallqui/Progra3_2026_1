package pe.edu.pucp.reniecsoft.services.persona;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import pe.edu.pucp.reniecsoft.business.persona.bo.IPersonaBO;
import pe.edu.pucp.reniecsoft.business.persona.impl.PersonaBOImpl;
import pe.edu.pucp.reniecsoft.config.DBManager;
import pe.edu.pucp.reniecsoft.model.Persona;

@WebService(
        serviceName = "PersonaWS",
        targetNamespace = "https://persona.services.reniecsoft.pucp.edu.pe/"
)
public class PersonaWS {
    @WebMethod(
        operationName = "buscarPorDni"
    )
    public Persona buscarPorDni(
            @WebParam(name = "dni") String dni
    ) throws Exception {
        if(DBManager.getInstance().getConnection() != null){
            System.out.println("CONEXIÓN ESTABLECIDA");
        }
        IPersonaBO personaBO = new PersonaBOImpl();
        return personaBO.buscarPorDni(dni);
    }

    @WebMethod(
            operationName = "probarConexionBD"
    )
    public String probarConexionBD() {
        try {
            // Intentamos obtener una conexión directa usando tu DBManager
            java.sql.Connection con = pe.edu.pucp.reniecsoft.config.DBManager.getInstance().getConnection();
            if (con != null && !con.isClosed()) {
                con.close(); // La cerramos de inmediato, solo queríamos probar
                return "CONEXIÓN EXITOSA";
            } else {
                return "Error: La conexión retornó nula o cerrada.";
            }
        } catch (Exception e) {
            // Capturamos TODO el error y lo volvemos un texto largo para verlo en el Tester
            java.io.StringWriter sw = new java.io.StringWriter();
            java.io.PrintWriter pw = new java.io.PrintWriter(sw);
            e.printStackTrace(pw);
            return "FALLÓ LA CONEXIÓN. DETALLE DEL ERROR:\n" + sw.toString();
        }
    }
}
