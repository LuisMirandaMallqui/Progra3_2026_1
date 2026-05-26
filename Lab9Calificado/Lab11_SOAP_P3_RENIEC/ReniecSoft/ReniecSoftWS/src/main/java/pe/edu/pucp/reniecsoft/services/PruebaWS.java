package pe.edu.pucp.reniecsoft.services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService( // indica la clase que sera publicada como un servicio web SOAP simple access object protocol
        serviceName = "PruebaWS", // Define el nombre del servicio web que aparecera en el WSDL
        targetNamespace = "https://services.reniecsoft.pucp.edu.pe/" // determina espacio de nombres XML usado por el WS
)

public class PruebaWS {
    @WebMethod( // indica que el metodo sera expuesto como una operación del servicio web
            operationName = "saludar" // operationName: Define el nombre de la operación SOAP expuesta en el servicio
    )
    public String Saludar(
            @WebParam(name = "nombre") // define nombre y configuración de un parametro de entrada de una operación SOAP
            String nombre){
        return "Hola " + nombre;
    }
}
