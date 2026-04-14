package pe.edu.pucp.softprog.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DBManager {
    private Connection con;
    private static DBManager dbManager; //Un solo obj toda la vida del programa
    private final String hostname;
    private final String esquema;
    private final String puerto;
    private final String usuario;
    private final String password;
    private final String url;

    public DBManager(){
        ResourceBundle db =
                ResourceBundle.getBundle("db");
        this.hostname = db.getString("db.host");
        this.esquema = db.getString("db.esquema");
        this.usuario = db.getString("db.user");
        this.password = db.getString("db.password");
        this.puerto = db.getString("db.puerto");
        this.url = "jdbc:mysql://" + this.hostname + ":"+this.puerto+"/"+this.esquema;
    }

    public Connection getConnection(){
        try {
            if(this.con == null || this.con.isClosed()) {
                //Se registra el driver de conexión
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(this.url, this.usuario, this.password);
                System.out.println("conexión establecida");
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return con;
    }

    public static DBManager getInstance(){
        //preguntar si esta inicializado
        if(dbManager == null){
            dbManager = new DBManager();
        }
        return  dbManager;
    }
}
