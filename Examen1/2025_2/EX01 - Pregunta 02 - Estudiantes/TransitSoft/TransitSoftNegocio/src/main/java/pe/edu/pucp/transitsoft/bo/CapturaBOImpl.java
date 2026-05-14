package pe.edu.pucp.transitsoft.bo;

import java.util.List;
import pe.edu.pucp.transitsoft.estrategias.AnalizadorDeVelocidad;
import pe.edu.pucp.transitsoft.estrategias.Estrategia;
import pe.edu.pucp.transitsoft.estrategias.EstrategiaViaRapida;
import pe.edu.pucp.transitsoft.estrategias.EstrategiaViaRegular;
import pe.edu.pucp.transitsoft.modelo.Captura;

public class CapturaBOImpl implements CapturaBO {
    // TODO: Declarar el attributo CapturaDAO
    private final EstrategiaViaRapida estrategiaViaRapida;
    private final EstrategiaViaRegular estrategiaViaRegular;
    private static final List<String> CAMARAS_VIA_RAPIDA = 
            List.of("CAM-LIM-002");
    
    public CapturaBOImpl() {
        // TODO: Instanciar al CapturaDAO
        this.estrategiaViaRapida = new EstrategiaViaRapida();
        this.estrategiaViaRegular = new EstrategiaViaRegular();
    }
    
    @Override
    public List<Captura> obtenerCapturasConExcesoDeVelocidad() {
        // TODO: Implementar la obtencion de capturas con exceso de velocidad
        // usando el patron estrategia.
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void actualizar(Captura captura) {
        // TODO: Implementar la actualizacion del estado de una captura a procesado
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    protected AnalizadorDeVelocidad analizador(Captura captura) {
        String codigo = captura.getCamara().getCodigoSerie();
        Estrategia estrategia = CAMARAS_VIA_RAPIDA.contains(codigo)
                ? estrategiaViaRapida
                : estrategiaViaRegular;

        return new AnalizadorDeVelocidad(estrategia);
    }
}
