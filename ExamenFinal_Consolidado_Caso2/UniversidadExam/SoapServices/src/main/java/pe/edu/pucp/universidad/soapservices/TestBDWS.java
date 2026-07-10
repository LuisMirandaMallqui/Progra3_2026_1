package pe.edu.pucp.universidad.soapservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import pe.edu.pucp.universidad.manager.DBManager;
import java.sql.Connection;

@WebService(
        serviceName = "TestBDWS",
        targetNamespace = "http://soapservices.universidad.pucp.edu.pe/test"
)
public class TestBDWS {
    @WebMethod(operationName = "pingTestBD")
    public String pingTestBD() {
        return "SOAP Caso 2 funcionando";
    }

    @WebMethod(operationName = "testConexionBD")
    public String testConexionBD() {
        try (Connection con = DBManager.getInstance().getConnection()) {
            return "Conexión OK: " + con.getCatalog();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error BD: " + ex.getMessage();
        }
    }
}
