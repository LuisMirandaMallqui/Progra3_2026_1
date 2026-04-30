package pe.edu.pucp.testsoft.db;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DBManagerTest {

    @Test
    void getConnection() {
        System.out.println("getConnection");
        DBManager dBManager = DBManager.getInstance();
        Connection conexion = dBManager.getConnection();
        assertNotNull(conexion);
        dBManager.cerrarConexion();
    }

    @Test
    void getInstance() {
        System.out.println("getInstance");
        DBManager dBManager = DBManager.getInstance();
        assertNotNull(dBManager);
    }
}