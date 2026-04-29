package pe.edu.pucp.assessment.db;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;
import java.util.ResourceBundle;

public class DBManager {
    private static Connection con;
    private static DBManager dbManager;
    private final String hostnameMySQL;
    private final String hostnameMSSQL;
    private final String esquema;
    private final String puertoMySQL;
    private final String puertoMSSQL;
    private final String usuario;
    private final String password;
    private String url;
    private final String tipoBD;
    private ResultSet rs;

    // Constructor privado (Singleton)
    private DBManager(){
        ResourceBundle db = ResourceBundle.getBundle("datos");
        this.hostnameMySQL = db.getString("db.hostMySQL");
        this.hostnameMSSQL = db.getString("db.hostMSSQL");
        this.esquema = db.getString("db.esquema");
        this.puertoMySQL = db.getString("db.puertoMySQL");
        this.puertoMSSQL = db.getString("db.puertoMSSQL");
        this.usuario = db.getString("db.usuario");
        this.password = db.getString("db.password");
        this.tipoBD = db.getString("db.type");
        if(this.tipoBD.equals("mysql"))
            this.url = "jdbc:mysql://" + this.hostnameMySQL + ":" + this.puertoMySQL + "/" + this.esquema;
        else if(this.tipoBD.equals("mssql"))
            this.url = "jdbc:sqlserver://" + this.hostnameMSSQL + ":" + this.puertoMSSQL + ";databaseName=" +  this.esquema +";encrypt=false;trustServerCertificate=true;integratedSecurity=false;";
    }

    public Connection getConnection(){
        try{
            if(con == null || con.isClosed()){
                //Se registra el driver de conexión
                Class.forName("com.mysql.cj.jdbc.Driver");
                //Se establece la conexión
                con = DriverManager.getConnection(url, usuario,password);
                System.out.println("Se ha realizado la conexion");
            }
        }catch(Exception ex){
            System.out.println(ex.getStackTrace());
            System.out.println("Error al conectarse con la BD: " + ex.getMessage());
        }
        return con;
    }

    public static DBManager getInstance(){
        if(dbManager == null)
            dbManager = new DBManager();
        return dbManager;
    }

    public void cerrarConexion(){
        try{
            con.close();
        }catch(SQLException ex){
            System.out.println("Error al cerrar la conexion: " + ex.getMessage());
        }
    }

    //Métodos para llamadas a Procedimientos Almacenados
    public int ejecutarProcedimiento(String nombreProcedimiento, Map<Integer, Object> parametrosEntrada, Map<Integer, Object> parametrosSalida) {
        int resultado = 0;
        try{
            CallableStatement cst = formarLlamadaProcedimiento(nombreProcedimiento, parametrosEntrada, parametrosSalida);
            if(parametrosEntrada != null)
                registrarParametrosEntrada(cst, parametrosEntrada);
            if(parametrosSalida != null)
                registrarParametrosSalida(cst, parametrosSalida);

            resultado = cst.executeUpdate();

            if(parametrosSalida != null)
                obtenerValoresSalida(cst, parametrosSalida);
        }catch(SQLException ex){
            System.out.println("Error ejecutando procedimiento almacenado: " + ex.getMessage());
        }finally{
            cerrarConexion();
        }
        return resultado;
    }

    public ResultSet ejecutarProcedimientoLectura(String nombreProcedimiento, Map<Integer,Object> parametrosEntrada){
        try{
            CallableStatement cs = formarLlamadaProcedimiento(nombreProcedimiento, parametrosEntrada, null);
            if(parametrosEntrada!=null)
                registrarParametrosEntrada(cs,parametrosEntrada);
            rs = cs.executeQuery();
        }catch(SQLException ex){
            System.out.println("Error ejecutando procedimiento almacenado de lectura: " + ex.getMessage());
        }
        return rs;
    }

    public CallableStatement formarLlamadaProcedimiento(String nombreProcedimiento, Map<Integer, Object> parametrosEntrada, Map<Integer, Object> parametrosSalida) throws SQLException{
        con = getConnection();
        StringBuilder call = new StringBuilder("{call " + nombreProcedimiento + "(");
        int cantParametrosEntrada = 0;
        int cantParametrosSalida = 0;
        if(parametrosEntrada!=null) cantParametrosEntrada = parametrosEntrada.size();
        if(parametrosSalida!=null) cantParametrosSalida = parametrosSalida.size();
        int numParams =  cantParametrosEntrada + cantParametrosSalida;
        for (int i = 0; i < numParams; i++) {
            call.append("?");
            if (i < numParams - 1) {
                call.append(",");
            }
        }
        call.append(")}");
        return con.prepareCall(call.toString());
    }

    private void registrarParametrosEntrada(CallableStatement cs, Map<Integer, Object> parametros) throws SQLException {
        for (Map.Entry<Integer, Object> entry : parametros.entrySet()) {
            Integer key = entry.getKey();
            Object value = entry.getValue();
            switch (value) {
                case Integer entero -> cs.setInt(key, entero);
                case String cadena -> cs.setString(key, cadena);
                case Double decimal -> cs.setDouble(key, decimal);
                case Boolean booleano -> cs.setBoolean(key, booleano);
                case java.util.Date fecha -> cs.setDate(key, new java.sql.Date(fecha.getTime()));
                case Character caracter -> cs.setString(key, String.valueOf(caracter));
                case byte[] archivo -> cs.setBytes(key, archivo);
                default -> {
                }
                // Agregar más tipos según sea necesario
            }
        }
    }

    private void registrarParametrosSalida(CallableStatement cst, Map<Integer, Object> params) throws SQLException {
        for (Map.Entry<Integer, Object> entry : params.entrySet()) {
            Integer posicion = entry.getKey();
            int sqlType = (int) entry.getValue();
            cst.registerOutParameter(posicion, sqlType);
        }
    }

    private void obtenerValoresSalida(CallableStatement cst, Map<Integer, Object> parametrosSalida) throws SQLException {
        for (Map.Entry<Integer, Object> entry : parametrosSalida.entrySet()) {
            Integer posicion = entry.getKey();
            int sqlType = (int) entry.getValue();
            Object value = null;
            switch (sqlType) {
                case Types.INTEGER -> value = cst.getInt(posicion);
                case Types.VARCHAR -> value = cst.getString(posicion);
                case Types.DOUBLE -> value = cst.getDouble(posicion);
                case Types.BOOLEAN -> value = cst.getBoolean(posicion);
                case Types.DATE -> value = cst.getDate(posicion);
                case Types.BLOB -> value = cst.getBytes(posicion);
                // Agregar más tipos según sea necesario
            }
            parametrosSalida.put(posicion, value);
        }
    }

    //Para transacciones

    public void iniciarTransaccion() throws SQLException{
        con = getConnection();
        con.setAutoCommit(false);
    }

    public void cancelarTransaccion(){
        try{
            con.rollback();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            cerrarConexion();
        }
    }

    public void confirmarTransaccion() throws SQLException{
        con.commit();
    }

    public CallableStatement formarLlamadaProcedimientoTransaccion(String nombreProcedimiento, Map<Integer, Object> parametrosEntrada, Map<Integer, Object> parametrosSalida) throws SQLException{
        StringBuilder call = new StringBuilder("{call " + nombreProcedimiento + "(");
        int cantParametrosEntrada = 0;
        int cantParametrosSalida = 0;
        if(parametrosEntrada!=null) cantParametrosEntrada = parametrosEntrada.size();
        if(parametrosSalida!=null) cantParametrosSalida = parametrosSalida.size();
        int numParams =  cantParametrosEntrada + cantParametrosSalida;
        for (int i = 0; i < numParams; i++) {
            call.append("?");
            if (i < numParams - 1) {
                call.append(",");
            }
        }
        call.append(")}");
        return con.prepareCall(call.toString());
    }

    public int ejecutarProcedimientoTransaccion(String nombreProcedimiento, Map<Integer, Object> parametrosEntrada, Map<Integer, Object> parametrosSalida) {
        int resultado = 0;
        try{
            CallableStatement cst = formarLlamadaProcedimientoTransaccion(nombreProcedimiento, parametrosEntrada, parametrosSalida);
            if(parametrosEntrada != null)
                registrarParametrosEntrada(cst, parametrosEntrada);
            if(parametrosSalida != null)
                registrarParametrosSalida(cst, parametrosSalida);

            resultado = cst.executeUpdate();

            if(parametrosSalida != null)
                obtenerValoresSalida(cst, parametrosSalida);
        }catch(SQLException ex){
            System.out.println("Error ejecutando procedimiento almacenado: " + ex.getMessage());
        }
        return resultado;
    }
}