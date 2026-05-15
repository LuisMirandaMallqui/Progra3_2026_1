package pe.edu.pe.estrategias;

import pe.edu.pucp.infraccion.Infraccion;
import pe.edu.pucp.infraccion.RegistroInfraccion;
import pe.edu.pucp.vehiculo.VehiculoConductor;

import java.util.List;

public class EstrategiaPuntosDescuento implements Estrategia {
    //  Estrategia 3: Aplica un descuento del 50% sobre los puntos calculados (como en la Estrategia 2). Se
    //  asumen en esta estrategia que el conductor ha pagado la multa con anterioridad

    @Override
    public int gestionarPuntos(VehiculoConductor vehiculoConductor, List<RegistroInfraccion> infracciones) {
        int puntosTotales = 0;
        for (RegistroInfraccion registroInfraccion : infracciones) {
            if (registroInfraccion.getFecha().after(vehiculoConductor.getFechaAdquisicion())) {
                puntosTotales += registroInfraccion.getInfraccion().getPuntos();
            }
        }
        int puntosConDescuento = Math.round((float)puntosTotales / 2);
        vehiculoConductor.getConductor().setPuntosAcumulados(
                vehiculoConductor.getConductor().getPuntosAcumulados() + puntosConDescuento
        );
        return puntosConDescuento;
    }
}
