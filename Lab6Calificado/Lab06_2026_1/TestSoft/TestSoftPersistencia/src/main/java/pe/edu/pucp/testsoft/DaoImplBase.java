package pe.edu.pucp.testsoft;

import pe.edu.pucp.testsoft.db.DBManager;

import java.util.HashMap;
import java.util.Map;

public abstract class DaoImplBase {

    protected DBManager dbManager = DBManager.getInstance();

    // Cada dao determina su propia llamada para eliminar
    protected abstract String obtenerSPEliminar();

    // Cada Dao hereda esto
    public int eliminar(int id) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, id);
        return dbManager.ejecutarProcedimiento(
                obtenerSPEliminar(), parametrosEntrada, null);
    }
}