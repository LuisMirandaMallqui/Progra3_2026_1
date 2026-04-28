package pe.edu.pucp.assessment.db;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
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