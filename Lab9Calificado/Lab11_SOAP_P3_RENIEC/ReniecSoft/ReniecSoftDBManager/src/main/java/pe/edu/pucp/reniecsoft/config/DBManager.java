package pe.edu.pucp.reniecsoft.config;

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

    // Conexión especial que se reutiliza durante una transacción activa.
    // En operaciones normales (sin transacción) es null.
    private Connection conTransaccion;

    private DBManager() {
        ResourceBundle db = ResourceBundle.getBundle("datos");
        this.hostnameMySQL = db.getString("db.hostMySQL");
        this.hostnameMSSQL = db.getString("db.hostMSSQL");
        this.esquema       = db.getString("db.esquema");
        this.puertoMySQL   = db.getString("db.puertoMySQL");
        this.puertoMSSQL   = db.getString("db.puertoMSSQL");
        this.usuario       = db.getString("db.usuario");
        this.password      = db.getString("db.password");
        this.tipoBD        = db.getString("db.type");

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

    // =====================================================================
    // MÉTODOS DE INFRAESTRUCTURA (internos, usados por ambos modos)
    // =====================================================================

    // Abre y retorna una conexión nueva cada vez que se llama.
    // Las operaciones SIN transacción la abren y cierran solas.
    // Las operaciones CON transacción NO la usan — usan conTransaccion.
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

    // Arma el string "{call nombre_sp(?,?,?)}" según cuántos parámetros hay.
    // Usado internamente por TODOS los métodos de ejecución.
    public CallableStatement formarLlamadaProcedimiento(
            Connection con,
            String nombreProcedimiento,
            Map<Integer, Object> parametrosEntrada,
            Map<Integer, Object> parametrosSalida) throws SQLException {

        StringBuilder call = new StringBuilder("{call " + nombreProcedimiento + "(");
        int numParams = (parametrosEntrada != null ? parametrosEntrada.size() : 0)
                + (parametrosSalida  != null ? parametrosSalida.size()  : 0);

        for (int i = 0; i < numParams; i++) {
            call.append("?");
            if (i < numParams - 1) call.append(",");
        }
        call.append(")}");
        return con.prepareCall(call.toString());
    }

    // Registra los parámetros IN en el CallableStatement.
    // Usado internamente por todos los modos.
    private void registrarParametrosEntrada(
            CallableStatement cs,
            Map<Integer, Object> parametros) throws SQLException {

        for (Map.Entry<Integer, Object> entry : parametros.entrySet()) {
            Integer key   = entry.getKey();
            Object  value = entry.getValue();
            switch (value) {
                case Integer   entero   -> cs.setInt    (key, entero);
                case String    cadena   -> cs.setString (key, cadena);
                case Double    decimal  -> cs.setDouble (key, decimal);
                case Boolean   booleano -> cs.setBoolean(key, booleano);
                case java.util.Date fec -> cs.setDate   (key, new java.sql.Date(fec.getTime()));
                case Character caracter -> cs.setString (key, String.valueOf(caracter));
                case byte[]    archivo  -> cs.setBytes  (key, archivo);
                case null               -> cs.setNull   (key, Types.NULL);
                default                 -> cs.setObject (key, value);
            }
        }
    }

    // Registra los parámetros OUT en el CallableStatement.
    // Usado internamente por todos los modos.
    private void registrarParametrosSalida(
            CallableStatement cst,
            Map<Integer, Object> params) throws SQLException {

        for (Map.Entry<Integer, Object> entry : params.entrySet()) {
            cst.registerOutParameter(entry.getKey(), (int) entry.getValue());
        }
    }

    // Lee los valores OUT del CallableStatement y los vuelve a meter en el Map.
    // Después de llamar a esto, el BO puede hacer salida.get(posicion) para obtener el valor.
    private void obtenerValoresSalida(
            CallableStatement cst,
            Map<Integer, Object> parametrosSalida) throws SQLException {

        for (Map.Entry<Integer, Object> entry : parametrosSalida.entrySet()) {
            Integer posicion = entry.getKey();
            int     sqlType  = (int) entry.getValue();
            Object  value;
            switch (sqlType) {
                case Types.INTEGER -> value = cst.getInt    (posicion);
                case Types.VARCHAR -> value = cst.getString (posicion);
                case Types.DOUBLE  -> value = cst.getDouble (posicion);
                case Types.BOOLEAN -> value = cst.getBoolean(posicion);
                case Types.DATE    -> value = cst.getDate   (posicion);
                case Types.BLOB    -> value = cst.getBytes  (posicion);
                default            -> value = cst.getObject (posicion);
            }
            parametrosSalida.put(posicion, value);
        }
    }

    // =====================================================================
    // SIN TRANSACCIÓN — cada llamada abre y cierra su propia conexión
    // =====================================================================

    // INSERT / UPDATE / DELETE sin transacción.
    // Abre conexión → ejecuta SP → cierra conexión (try-with-resources).
    // Úsalo cuando la operación es atómica y no depende de otras.
    public int ejecutarProcedimiento(
            String nombreProcedimiento,
            Map<Integer, Object> parametrosEntrada,
            Map<Integer, Object> parametrosSalida) {

        int resultado = 0;
        try (
                Connection con = getConnection();                           // conexión nueva, se cierra sola
                CallableStatement cst = formarLlamadaProcedimiento(
                        con, nombreProcedimiento, parametrosEntrada, parametrosSalida)
        ) {
            if (parametrosEntrada != null) registrarParametrosEntrada(cst, parametrosEntrada);
            if (parametrosSalida  != null) registrarParametrosSalida (cst, parametrosSalida);

            resultado = cst.executeUpdate();

            if (parametrosSalida  != null) obtenerValoresSalida(cst, parametrosSalida);

        } catch (SQLException ex) {
            System.out.println("Error ejecutando procedimiento almacenado: " + ex.getMessage());
        }
        return resultado;
    }

    // SELECT sin transacción.
    // Abre conexión y la deja abierta hasta que el llamador cierre el ResultadoConsulta.
    // SIEMPRE usar en try-with-resources: try (var rc = dbManager.ejecutarProcedimientoLectura(...))
    public ResultadoConsulta ejecutarProcedimientoLectura(
            String nombreProcedimiento,
            Map<Integer, Object> parametrosEntrada) {

        try {
            Connection con = getConnection();                           // conexión que se cierra en rc.close()
            CallableStatement cs = formarLlamadaProcedimiento(
                    con, nombreProcedimiento, parametrosEntrada, null);

            if (parametrosEntrada != null) registrarParametrosEntrada(cs, parametrosEntrada);

            ResultSet rs = cs.executeQuery();
            return new ResultadoConsulta(con, cs, rs);                 // el caller cierra con try-with-resources

        } catch (SQLException ex) {
            System.out.println("Error ejecutando procedimiento de lectura: " + ex.getMessage());
            return null;
        }
    }

    // =====================================================================
    // CON TRANSACCIÓN — los tres pasos obligatorios son:
    //   1. iniciarTransaccion()
    //   2. ejecutarProcedimientoTransaccion() — una o más veces
    //   3. confirmarTransaccion() o cancelarTransaccion()
    // =====================================================================

    // PASO 1: abre la conexión compartida y desactiva el autocommit.
    // A partir de aquí, nada se guarda en BD hasta que confirmes o canceles.
    public void iniciarTransaccion() throws SQLException {
        conTransaccion = getConnection();
        conTransaccion.setAutoCommit(false);
    }

    // PASO 2: INSERT / UPDATE / DELETE dentro de la transacción activa.
    // Usa conTransaccion (no abre una nueva).
    // Si algo falla lanza RuntimeException para que el BO la capture y llame a cancelarTransaccion().
    public int ejecutarProcedimientoTransaccion(
            String nombreProcedimiento,
            Map<Integer, Object> parametrosEntrada,
            Map<Integer, Object> parametrosSalida) {

        int resultado = 0;
        try (
                CallableStatement cst = formarLlamadaProcedimiento(
                        conTransaccion,                                     // misma conexión compartida
                        nombreProcedimiento, parametrosEntrada, parametrosSalida)
        ) {
            if (parametrosEntrada != null) registrarParametrosEntrada(cst, parametrosEntrada);
            if (parametrosSalida  != null) registrarParametrosSalida (cst, parametrosSalida);

            resultado = cst.executeUpdate();

            if (parametrosSalida  != null) obtenerValoresSalida(cst, parametrosSalida);

        } catch (SQLException ex) {
            // Lanza excepción para que el BO haga rollback
            throw new RuntimeException("Error en transacción: " + ex.getMessage(), ex);
        }
        return resultado;
    }

    // PASO 3a: hace commit y cierra la conexión compartida. Todo lo ejecutado queda guardado.
    public void confirmarTransaccion() throws SQLException {
        if (conTransaccion != null) {
            conTransaccion.commit();
            conTransaccion.close();
            conTransaccion = null;
        }
    }

    // PASO 3b: hace rollback y cierra la conexión. Todo lo ejecutado se deshace.
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

    // =====================================================================
    // MÉTODO INTELIGENTE — decide solo si usar transacción o no
    // =====================================================================

    // Úsalo cuando el BO no sabe si está dentro de una transacción o no.
    // Si hay transacción activa → ejecutarProcedimientoTransaccion
    // Si no hay               → ejecutarProcedimiento (abre y cierra solo)
    public int ejecutarProcedimientoAuto(
            String nombreProcedimiento,
            Map<Integer, Object> parametrosEntrada,
            Map<Integer, Object> parametrosSalida) {

        if (hayTransaccionActiva())
            return ejecutarProcedimientoTransaccion(nombreProcedimiento, parametrosEntrada, parametrosSalida);
        return ejecutarProcedimiento(nombreProcedimiento, parametrosEntrada, parametrosSalida);
    }

    // Retorna true si iniciarTransaccion() fue llamado y aún no se confirmó/canceló.
    public boolean hayTransaccionActiva() {
        return conTransaccion != null;
    }

    // =====================================================================
    // WRAPPER DE LECTURA — agrupa Connection + Statement + ResultSet
    // =====================================================================

    // Implementa AutoCloseable para poder usarse en try-with-resources.
    // Cierra rs → cs → con en ese orden (siempre del más interno al más externo).
    public static class ResultadoConsulta implements AutoCloseable {

        private final Connection       con;
        private final CallableStatement cs;
        private final ResultSet         rs;

        public ResultadoConsulta(Connection con, CallableStatement cs, ResultSet rs) {
            this.con = con;
            this.cs  = cs;
            this.rs  = rs;
        }

        public ResultSet getRs() { return rs; }

        @Override
        public void close() {
            try { if (rs  != null) rs.close();  } catch (SQLException ex) { System.out.println("Error cerrando ResultSet: "          + ex.getMessage()); }
            try { if (cs  != null) cs.close();  } catch (SQLException ex) { System.out.println("Error cerrando CallableStatement: "  + ex.getMessage()); }
            try { if (con != null) con.close(); } catch (SQLException ex) { System.out.println("Error cerrando Connection: "         + ex.getMessage()); }
        }
    }
}