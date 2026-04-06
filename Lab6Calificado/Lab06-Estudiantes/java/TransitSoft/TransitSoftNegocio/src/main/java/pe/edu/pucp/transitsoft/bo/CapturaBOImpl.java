package pe.edu.pucp.transitsoft.bo;

import java.util.List;
import pe.edu.pucp.transitsoft.dao.CapturaDAO;
import pe.edu.pucp.transitsoft.daoimpl.CapturaDAOImpl;
import pe.edu.pucp.transitsoft.modelo.Captura;

public class CapturaBOImpl implements CapturaBO {
    private final CapturaDAO capturaDao;
    
    public CapturaBOImpl() {
        this.capturaDao = new CapturaDAOImpl();
    }
    
    @Override
    public List<Captura> obtenerCapturasConExcesoDeVelocidad() {
        // TODO: LLamar a capturaDao.leerTodos() y luego
        // devolver solo las capturas donde la velocidad excede
        // el limite permitido
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void actualizar(Captura modelo) {
        // TODO: Llamar a capturaDao.actualizar(modelo)
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
