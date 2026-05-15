package pe.edu.pe.estrategias;

import pe.edu.pucp.infraccion.Infraccion;
import pe.edu.pucp.infraccion.RegistroInfraccion;
import pe.edu.pucp.vehiculo.VehiculoConductor;

import java.util.List;

public interface Estrategia {

    int gestionarPuntos(VehiculoConductor vehiculoConductor, List<RegistroInfraccion> infracciones);
}