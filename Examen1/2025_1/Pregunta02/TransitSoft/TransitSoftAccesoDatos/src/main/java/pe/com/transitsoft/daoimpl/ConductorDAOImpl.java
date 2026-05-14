package pe.com.transitsoft.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import pe.com.transitsoft.dao.IConductorDAO;
import pe.com.transitsoft.modelo.Gravedad;

/**
 *
 * @author eric
 */
public class ConductorDAOImpl implements IConductorDAO {
    @Override
    public int obtenerPuntos(int idConductor, Gravedad gravedad) {
        // TODO: Implementar
        throw new RuntimeException("El metodo no ha sido implementado");
    }
    
    private CallableStatement configurarComandoObtenerPuntos(
            Connection conn, int id, Gravedad gravedad) throws SQLException {
        // TODO: Implementar
        throw new RuntimeException("El metodo no ha sido implementado");
    }
}