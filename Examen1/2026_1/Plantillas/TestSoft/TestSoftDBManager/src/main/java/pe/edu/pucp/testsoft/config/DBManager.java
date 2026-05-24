package pe.edu.pucp.testsoft.config;

import java.sql.*;
import java.util.Map;
import java.util.ResourceBundle;

public class DBManager {

    private static DBManager dbManager;

    private final String hostnameMySQL;
    private final String hostnameMSSQL;
    private final String esquema;
    private final String puertoMySQL;
    private final String puertoMSSQL;
    private final String usuario;
    private final String password;
    private final String tipoBD;
    private String url;

    private Connection conTransaccion;

    private DBManager() {
        ResourceBundle db = ResourceBundle.getBundle("datos");

        this.hostnameMySQL = db.getString("db.hostMySQL");
        this.hostnameMSSQL = db.getString("db.hostMSSQL");
        this.esquema = db.getString("db.esquema");
        this.puertoMySQL = db.getString("db.puertoMySQL");
        this.puertoMSSQL = db.getString("db.puertoMSSQL");
        this.usuario = db.getString("db.usuario");
        this.password = db.getString("db.password");
        this.tipoBD = db.getString("db.type");

        if (this.tipoBD.equals("mysql")) {
            this.url = "jdbc:mysql://" + hostnameMySQL + ":" + puertoMySQL + "/" + esquema;
        } else if (this.tipoBD.equals("mssql")) {
            this.url = "jdbc:sqlserver://" + hostnameMSSQL + ":" + puertoMSSQL
                    + ";databaseName=" + esquema
                    + ";encrypt=false;trustServerCertificate=true;integratedSecurity=false;";
        }
    }

    public static DBManager getInstance() {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    public Connection getConnection() throws SQLException {
        try {
            if (this.tipoBD.equals("mysql")) {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } else if (this.tipoBD.equals("mssql")) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            }
        } catch (ClassNotFoundException ex) {
            throw new SQLException("No se encontró el driver de conexión.", ex);
        }

        return DriverManager.getConnection(url, usuario, password);
    }

    public int ejecutarProcedimiento(
            String nombreProcedimiento,
            Map<Integer, Object> parametrosEntrada,
            Map<Integer, Object> parametrosSalida) {

        int resultado = 0;

        try (
                Connection con = getConnection();
                CallableStatement cst = formarLlamadaProcedimiento(
                        con,
                        nombreProcedimiento,
                        parametrosEntrada,
                        parametrosSalida)
        ) {
            if (parametrosEntrada != null) {
                registrarParametrosEntrada(cst, parametrosEntrada);
            }

            if (parametrosSalida != null) {
                registrarParametrosSalida(cst, parametrosSalida);
            }

            resultado = cst.executeUpdate();

            if (parametrosSalida != null) {
                obtenerValoresSalida(cst, parametrosSalida);
            }

        } catch (SQLException ex) {
            System.out.println("Error ejecutando procedimiento almacenado: " + ex.getMessage());
        }

        return resultado;
    }

    public ResultadoConsulta ejecutarProcedimientoLectura(
            String nombreProcedimiento,
            Map<Integer, Object> parametrosEntrada) {

        try {
            Connection con = getConnection();

            CallableStatement cs = formarLlamadaProcedimiento(
                    con,
                    nombreProcedimiento,
                    parametrosEntrada,
                    null);

            if (parametrosEntrada != null) {
                registrarParametrosEntrada(cs, parametrosEntrada);
            }

            ResultSet rs = cs.executeQuery();

            return new ResultadoConsulta(con, cs, rs);

        } catch (SQLException ex) {
            System.out.println("Error ejecutando procedimiento almacenado de lectura: " + ex.getMessage());
            return null;
        }
    }

    public CallableStatement formarLlamadaProcedimiento(
            Connection con,
            String nombreProcedimiento,
            Map<Integer, Object> parametrosEntrada,
            Map<Integer, Object> parametrosSalida) throws SQLException {

        StringBuilder call = new StringBuilder("{call " + nombreProcedimiento + "(");

        int cantParametrosEntrada = parametrosEntrada != null ? parametrosEntrada.size() : 0;
        int cantParametrosSalida = parametrosSalida != null ? parametrosSalida.size() : 0;

        int numParams = cantParametrosEntrada + cantParametrosSalida;

        for (int i = 0; i < numParams; i++) {
            call.append("?");

            if (i < numParams - 1) {
                call.append(",");
            }
        }

        call.append(")}");

        return con.prepareCall(call.toString());
    }

    private void registrarParametrosEntrada(
            CallableStatement cs,
            Map<Integer, Object> parametros) throws SQLException {

        for (Map.Entry<Integer, Object> entry : parametros.entrySet()) {
            Integer key = entry.getKey();
            Object value = entry.getValue();

            switch (value) {
                case Integer entero -> cs.setInt(key, entero);
                case String cadena -> cs.setString(key, cadena);
                case Double decimal -> cs.setDouble(key, decimal);
                case Boolean booleano -> cs.setBoolean(key, booleano);
                case java.util.Date fecha -> cs.setDate(key, new Date(fecha.getTime()));
                case Character caracter -> cs.setString(key, String.valueOf(caracter));
                case byte[] archivo -> cs.setBytes(key, archivo);
                case null -> cs.setNull(key, Types.NULL);
                default -> cs.setObject(key, value);
            }
        }
    }

    private void registrarParametrosSalida(
            CallableStatement cst,
            Map<Integer, Object> params) throws SQLException {

        for (Map.Entry<Integer, Object> entry : params.entrySet()) {
            Integer posicion = entry.getKey();
            int sqlType = (int) entry.getValue();

            cst.registerOutParameter(posicion, sqlType);
        }
    }

    private void obtenerValoresSalida(
            CallableStatement cst,
            Map<Integer, Object> parametrosSalida) throws SQLException {

        for (Map.Entry<Integer, Object> entry : parametrosSalida.entrySet()) {
            Integer posicion = entry.getKey();
            int sqlType = (int) entry.getValue();

            Object value;

            switch (sqlType) {
                case Types.INTEGER -> value = cst.getInt(posicion);
                case Types.VARCHAR -> value = cst.getString(posicion);
                case Types.DOUBLE -> value = cst.getDouble(posicion);
                case Types.BOOLEAN -> value = cst.getBoolean(posicion);
                case Types.DATE -> value = cst.getDate(posicion);
                case Types.BLOB -> value = cst.getBytes(posicion);
                default -> value = cst.getObject(posicion);
            }

            parametrosSalida.put(posicion, value);
        }
    }

    // TRANSACCIONES

    public void iniciarTransaccion() throws SQLException {
        conTransaccion = getConnection();
        conTransaccion.setAutoCommit(false);
    }

    public void confirmarTransaccion() throws SQLException {
        if (conTransaccion != null) {
            conTransaccion.commit();
            conTransaccion.close();
            conTransaccion = null;
        }
    }

    public void cancelarTransaccion() {
        try {
            if (conTransaccion != null) {
                conTransaccion.rollback();
                conTransaccion.close();
                conTransaccion = null;
            }
        } catch (SQLException ex) {
            System.out.println("Error al cancelar transacción: " + ex.getMessage());
        }
    }

    public int ejecutarProcedimientoTransaccion(
            String nombreProcedimiento,
            Map<Integer, Object> parametrosEntrada,
            Map<Integer, Object> parametrosSalida) {

        int resultado = 0;

        try (
                CallableStatement cst = formarLlamadaProcedimiento(
                        conTransaccion,
                        nombreProcedimiento,
                        parametrosEntrada,
                        parametrosSalida)
        ) {
            if (parametrosEntrada != null) {
                registrarParametrosEntrada(cst, parametrosEntrada);
            }

            if (parametrosSalida != null) {
                registrarParametrosSalida(cst, parametrosSalida);
            }

            resultado = cst.executeUpdate();

            if (parametrosSalida != null) {
                obtenerValoresSalida(cst, parametrosSalida);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Error ejecutando procedimiento almacenado en transacción: " + ex.getMessage(), ex);
        }
        return resultado;
    }

    public boolean hayTransaccionActiva() {
        return conTransaccion != null;
    }

    public int ejecutarProcedimientoAuto(String nombreProcedimiento, Map<Integer, Object> parametrosEntrada, Map<Integer, Object> parametrosSalida) {
        if (hayTransaccionActiva())
            return ejecutarProcedimientoTransaccion(nombreProcedimiento, parametrosEntrada, parametrosSalida);
        return ejecutarProcedimiento(nombreProcedimiento, parametrosEntrada, parametrosSalida);
    }

    // Wrapper para las lecturas
    public static class ResultadoConsulta implements AutoCloseable {

        private final Connection con;
        private final CallableStatement cs;
        private final ResultSet rs;

        public ResultadoConsulta(Connection con, CallableStatement cs, ResultSet rs) {
            this.con = con;
            this.cs = cs;
            this.rs = rs;
        }

        public ResultSet getRs() {
            return rs;
        }

        @Override
        public void close() {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error cerrando ResultSet: " + ex.getMessage());
            }

            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error cerrando CallableStatement: " + ex.getMessage());
            }

            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error cerrando Connection: " + ex.getMessage());
            }
        }


    }
}