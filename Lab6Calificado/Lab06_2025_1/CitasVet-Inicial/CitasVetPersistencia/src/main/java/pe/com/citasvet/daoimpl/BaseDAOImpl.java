package pe.com.citasvet.daoimpl;

import pe.com.citasvet.config.DBManager;

import java.io.IOException;
import java.sql.*;

public abstract class BaseDAOImpl {
    protected Connection conexion;
    // PreparedStatement: exclusivo para SELECTs directos (obtenerPorId, listarTodos, agregados)
    protected PreparedStatement preparedStatement;
    protected CallableStatement callableStatement;
    protected ResultSet resultSet;

    protected void abrirConexion() throws SQLException {
        try {
            this.conexion = DBManager.getInstance().getConnection();
        } catch (ClassNotFoundException e) { // consultar si estan bien los catch
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void cerrarConexion() throws SQLException {
        if (this.resultSet != null) {
            this.resultSet.close();
            this.resultSet = null;
        }
        if (this.preparedStatement != null) {
            this.preparedStatement.close();
            this.preparedStatement = null;
        }
        if (this.callableStatement != null) {
            this.callableStatement.close();
            this.callableStatement = null;
        }
        if (this.conexion != null && !this.conexion.isClosed()) {
            this.conexion.close();
        }
    }

    protected void IniciarTransaccion() throws SQLException {
        this.abrirConexion();
        this.conexion.setAutoCommit(false);
    }

    protected void comitarTransaccion() throws SQLException {
        if (this.conexion != null) {
            this.conexion.commit();
        }
    }

    protected void rollbackTransaccion() throws SQLException {
        if (this.conexion != null) {
            this.conexion.rollback();
        }
    }

    // Metodos para preparar y ejecutar SELECT con PreparedStatement en lugar de Procedures NO LO USE ESTE LAB
    protected void prepararConsulta(String sql) throws SQLException {
        System.out.println("[SQL]: " + sql);
        this.preparedStatement = this.conexion.prepareStatement(sql);
    }

    protected void ejecutarConsulta() throws SQLException { //TAMPOCO LO USE PORQUE SE HACE ESTE LAB CON PROCEDURES
        this.resultSet = this.preparedStatement.executeQuery();
    }



    // Metodos SELECT con PreparedStatement
    protected void procesarResultSet(int id) throws SQLException {
        try {
            if (this.resultSet.next()) {
                this.instanciarObjetoDelResultSet();
            } else {
                System.err.println("No se encontró el registro con id: " + id);
                this.limpiarObjetoDelResultSet();
            }
        } catch (SQLException err) {
            System.err.println("Error en procesarResultSet - " + err);
        }
    }

    //protected abstract String obtenerSQLParaObtenerPorId(); // no se usa pq estoy con full procedures
    protected void instanciarObjetoDelResultSet() throws SQLException {
        throw new UnsupportedOperationException("Debe sobreescribir instanciarObjetoDelResultSet()");
    }

    protected void limpiarObjetoDelResultSet() {
        throw new UnsupportedOperationException("Debe sobreescribir limpiarObjetoDelResultSet()");
    }
}
