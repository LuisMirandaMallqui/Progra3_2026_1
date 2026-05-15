package pe.edu.pe.estrategias;

import pe.edu.pucp.conductor.Conductor;
import pe.edu.pucp.infraccion.RegistroInfraccion;
import pe.edu.pucp.vehiculo.Vehiculo;
import pe.edu.pucp.vehiculo.VehiculoConductor;

import java.util.Date;
import java.util.List;

public class EstrategiaPuntosSinFecha implements Estrategia{
    /*
    Estrategia 1: Calcula los puntos acumulados sin considerar la fecha de adquisición del vehículo. Es decir,
    toda infracción registrada al vehículo sumará puntos al conductor, sin filtrar por fecha.
    */
    @Override
    public int gestionarPuntos(VehiculoConductor vehiculoConductor, List<RegistroInfraccion> infracciones) {
        int puntosTotales = 0;
        for(RegistroInfraccion registroInfraccion : infracciones){
            puntosTotales += registroInfraccion.getInfraccion().getPuntos();
        }
        vehiculoConductor.getConductor().setPuntosAcumulados(
                vehiculoConductor.getConductor().getPuntosAcumulados() + puntosTotales
        );
        return puntosTotales;
    }
}
