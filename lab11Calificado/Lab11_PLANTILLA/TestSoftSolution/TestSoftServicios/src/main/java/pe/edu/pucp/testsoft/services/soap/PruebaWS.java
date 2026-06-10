package pe.edu.pucp.testsoft.services.soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

// Primer servicio de humo. WSDL: http://localhost:8080/TestSoftServicios/PruebaWS?wsdl
@WebService(serviceName = "PruebaWS", targetNamespace = "http://services.testsoft.pucp.edu.pe/")
public class PruebaWS {
    @WebMethod(operationName = "saludar")
    public String saludar(@WebParam(name = "nombre") String nombre) {
        return "Hola " + nombre;
    }
}
