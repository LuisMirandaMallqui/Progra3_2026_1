package pe.edu.pucp.testsoft.config;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DBManagerTest {

    @org.junit.jupiter.api.Test
    void getInstance() {
       DBManager db= DBManager.getInstance();
       assertNotNull(db);
    }

    @org.junit.jupiter.api.Test
    void getConnection() throws SQLException {
        Connection cn = DBManager.getInstance().getConnection();
        assertNotNull(cn);
    }
}