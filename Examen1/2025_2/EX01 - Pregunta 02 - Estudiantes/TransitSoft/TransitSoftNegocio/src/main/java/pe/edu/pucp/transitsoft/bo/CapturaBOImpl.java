package pe.edu.pucp.transitsoft.bo;

import java.util.ArrayList;
import java.util.List;

import pe.edu.pucp.inf30.transitsoftpersistencia.captura.dao.CapturaDAO;
import pe.edu.pucp.inf30.transitsoftpersistencia.captura.impl.CapturaDAOImpl;
import pe.edu.pucp.transitsoft.estrategias.AnalizadorDeVelocidad;
import pe.edu.pucp.transitsoft.estrategias.Estrategia;
import pe.edu.pucp.transitsoft.estrategias.EstrategiaViaRapida;
import pe.edu.pucp.transitsoft.estrategias.EstrategiaViaRegular;
import pe.edu.pucp.transitsoft.modelo.Captura;

public class CapturaBOImpl implements CapturaBO {
    // TODO: Declarar el attributo CapturaDAO
    private CapturaDAO capturaDAO; // lo puse yo
    private final EstrategiaViaRapida estrategiaViaRapida; //ya venia asi
    private final EstrategiaViaRegular estrategiaViaRegular; // ya venia asi
    private static final List<String> CAMARAS_VIA_RAPIDA = 
            List.of("CAM-LIM-002");//ya venia asi
    
    public CapturaBOImpl() {
        // TODO: Instanciar al CapturaDAO
        this.capturaDAO = new CapturaDAOImpl(); // lo puse yo, lo demas venia asi
        this.estrategiaViaRapida = new EstrategiaViaRapida();
        this.estrategiaViaRegular = new EstrategiaViaRegular();
    }
    
    @Override
    public List<Captura> obtenerCapturasConExcesoDeVelocidad() {
        // TODO: Implementar la obtencion de capturas con exceso de velocidad
        // usando el patron estrategia. Lo hice todo yo
        List<Captura> capturas = capturaDAO.listarTodos();
        List<Captura> capturasConExceso = new ArrayList<>();
        for(Captura captura : capturas) {
            AnalizadorDeVelocidad analizador = analizador(captura);
            analizador.evaluarExceso(captura);
        }
        return capturasConExceso;
    }
    
    @Override
    public void actualizar(Captura captura) {
        // TODO: Implementar la actualizacion del estado de una captura a procesado
        //lo hice todo yo
        int resultado = capturaDAO.actualizar(captura);
        if(resultado>0){
            System.out.println("Modificación exitosa");
        }
    }
    
    protected AnalizadorDeVelocidad analizador(Captura captura) {
        String codigo = captura.getCamara().getCodigoSerie();
        Estrategia estrategia = CAMARAS_VIA_RAPIDA.contains(codigo)
                ? estrategiaViaRapida
                : estrategiaViaRegular;

        return new AnalizadorDeVelocidad(estrategia);
    }
}
