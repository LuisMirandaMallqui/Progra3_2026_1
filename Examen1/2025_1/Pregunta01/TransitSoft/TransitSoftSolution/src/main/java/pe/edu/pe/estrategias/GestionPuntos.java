package pe.edu.pe.estrategias;

import pe.edu.pucp.infraccion.RegistroInfraccion;
import pe.edu.pucp.vehiculo.VehiculoConductor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestionPuntos {
    private Estrategia estrategia;

    public GestionPuntos() {
    }

    public GestionPuntos(Estrategia estrategia) {
        this.estrategia = estrategia;
    }

    public int ejecutarGestion(VehiculoConductor vehiculoConductor, List<RegistroInfraccion> registroInfracciones) {
        List<RegistroInfraccion> registroInfraccionesProcesadas = new ArrayList<>();
        for (RegistroInfraccion ri : registroInfracciones) {
            if (ri.getConductor().getConductorId() == vehiculoConductor.getConductor().getConductorId()) {
                registroInfraccionesProcesadas.add(ri);
            }
        }
        return estrategia.gestionarPuntos(vehiculoConductor,registroInfraccionesProcesadas);
    }

    public void setEstrategia(Estrategia estrategia) {
        this.estrategia = estrategia;
    }
}
