package pe.edu.pe.estrategias;

import pe.edu.pucp.infraccion.Infraccion;
import pe.edu.pucp.infraccion.RegistroInfraccion;
import pe.edu.pucp.vehiculo.VehiculoConductor;

import java.util.List;

public class EstrategiaPuntosConFecha implements Estrategia{
    //    Estrategia 2: Calcula los puntos sumando solo las infracciones registradas posteriormente a la fecha de
    //    adquisición del vehículo por parte del conductor. Es decir, solo se toman en cuenta las infracciones
    //    cometidas después que el conductor adquirió el vehículo.
    @Override
    public int gestionarPuntos(VehiculoConductor vehiculoConductor, List<RegistroInfraccion> infracciones) {
        int puntosTotales = 0;
        for(RegistroInfraccion registroInfraccion : infracciones){
            if(registroInfraccion.getFecha().after(vehiculoConductor.getFechaAdquisicion())){
                puntosTotales += registroInfraccion.getInfraccion().getPuntos();
            }
        }
        vehiculoConductor.getConductor().setPuntosAcumulados(
                vehiculoConductor.getConductor().getPuntosAcumulados() + puntosTotales
        );
        return puntosTotales;
    }
}
