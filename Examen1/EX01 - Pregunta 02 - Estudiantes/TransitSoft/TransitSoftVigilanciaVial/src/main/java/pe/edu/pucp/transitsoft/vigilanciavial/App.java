package pe.edu.pucp.transitsoft.vigilanciavial;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import pe.edu.pucp.transitsoft.bo.CapturaBO;
import pe.edu.pucp.transitsoft.bo.InfraccionBO;
import pe.edu.pucp.transitsoft.bo.CapturaBOImpl;
import pe.edu.pucp.transitsoft.bo.InfraccionBOImpl;
import pe.edu.pucp.transitsoft.dto.Infraccion;
import pe.edu.pucp.transitsoft.modelo.Captura;

public class App {
    
    public static void main(String[] args) {
        CapturaBO capturaBO = new CapturaBOImpl();
        List<Captura> capturasConExceso = 
                capturaBO.obtenerCapturasConExcesoDeVelocidad();
        
        InfraccionBO infraccionBO = new InfraccionBOImpl();
        List<Infraccion> infracciones = 
                infraccionBO.crearInfracciones(capturasConExceso);
        
        capturasConExceso.forEach(capturaBO::actualizar);
        
        serializarInfracciones(infracciones);
        System.out.println("Las capturas fueron procesadas satisfactoriamente.");
    }
    
    public static void serializarInfracciones(List<Infraccion> infracciones) {
        try {
            Path directory = Paths.get("inbox");
            Files.createDirectories(directory);

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            for (Infraccion infraccion : infracciones) {
                String nombreArchivo = "infraccion_" + UUID.randomUUID().toString() + ".json";
                File file = new File(directory.toFile(), nombreArchivo);

                mapper.writeValue(file, infraccion);
                System.out.println("Infraccion serializada en: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("Error al serializar las infracciones: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }
}
