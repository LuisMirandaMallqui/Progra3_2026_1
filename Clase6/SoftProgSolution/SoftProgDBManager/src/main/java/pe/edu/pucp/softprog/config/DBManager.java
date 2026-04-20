package pe.edu.pucp.softprog.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class DBManager {

    private static Connection con;
    private static DBManager dbManager;
    private final String hostname;
    private final String esquema;
    private final String puerto;
    private final String usuario;
    private final String password;
    private final String url;

    private DBManager(){
        ResourceBundle db = ResourceBundle.getBundle("datos");
        this.hostname = db.getString("db.host");
        this.esquema = db.getString("db.esquema");
        this.puerto = db.getString("db.puerto");
        this.usuario = db.getString("db.usuario");
        this.password = db.getString("db.password");
        this.url = "jdbc:mysql://" + this.hostname + ":" + this.puerto + "/" + this.esquema;
    }

    public Connection getConnection(){
        try {
            //Se registra el driver de conexión
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Se establece la conexión
            con = DriverManager.getConnection(url, usuario, password);
            System.out.println("Se ha realizado la conexion");
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
}
