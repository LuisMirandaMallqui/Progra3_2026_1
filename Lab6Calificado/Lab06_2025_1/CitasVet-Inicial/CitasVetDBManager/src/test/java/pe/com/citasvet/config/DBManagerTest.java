package pe.com.citasvet.config;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DBManagerTest {

    @Test
    void getInstance() {
        try {
            DBManager db = DBManager.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getConnection() {
        System.out.println("getConnection");
        DBManager dBManager = null;
        try {
            dBManager = DBManager.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Connection conexion = null;
        try {
            conexion = dBManager.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(conexion);
//        dBManager.closeConnection();
    }
}