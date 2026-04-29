package pe.edu.pucp.assessment.dao.persistance;

import pe.edu.pucp.assessment.db.DBManager;

import java.util.HashMap;
import java.util.Map;

public abstract class DaoImplBase {

    protected DBManager dbManager = DBManager.getInstance();

    // Cada hijo dice qué SP usa para eliminar
    protected abstract String obtenerSPEliminar();

    // Template method — el hijo hereda esto sin tocar nada
    public int eliminar(int id) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, id);
        return dbManager.ejecutarProcedimiento(
                obtenerSPEliminar(), parametrosEntrada, null);
    }
}