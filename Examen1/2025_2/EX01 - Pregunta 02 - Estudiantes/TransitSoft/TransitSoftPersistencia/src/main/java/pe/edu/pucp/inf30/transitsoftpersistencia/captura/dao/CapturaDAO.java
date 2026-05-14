package pe.edu.pucp.inf30.transitsoftpersistencia.captura.dao;

import pe.edu.pucp.inf30.transitsoftpersistencia.dao.IDAO;
import pe.edu.pucp.transitsoft.modelo.Captura;

public interface CapturaDAO extends IDAO<Captura> {
    public int actualizar(Captura captura);
}
