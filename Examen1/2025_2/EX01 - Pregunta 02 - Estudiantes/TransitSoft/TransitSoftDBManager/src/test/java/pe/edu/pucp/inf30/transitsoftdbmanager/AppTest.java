package pe.edu.pucp.inf30.transitsoftdbmanager;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */


    @Test
    void getConnection() { // para ver que los properties si enganchen en la construccion de la conexion
        System.out.println("getConnection");
        DBManager dBManager = DBManager.getInstance();
        Connection conexion = null;
        try {
            conexion = dBManager.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(conexion);
    }
}
