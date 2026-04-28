package pe.com.citasvet.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {
    private static DBManager dbManager;

    private String host;
    private int puerto;
    private String esquema;
    private String usuario;
    private String password;

    private DBManager() throws IOException {
        cargarProperties();
    }

    public synchronized static DBManager getInstance() throws IOException {
        if (dbManager == null) {
            createInstance();
        }
        return dbManager;
    }

    private static void createInstance() throws IOException {
        dbManager = new DBManager();
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String cadenaConexion = cadenaConexion(host, puerto, esquema);
            return DriverManager.getConnection(cadenaConexion, usuario, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
            throw e;
        }
    }

    private void cargarProperties() throws IOException {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                System.err.println("No se pudo abrir el archivo db.properties");
                return;
            }

            properties.load(input);

            host = properties.getProperty("db.host");
            puerto = Integer.parseInt(properties.getProperty("db.puerto"));
            esquema = properties.getProperty("db.esquema");
            usuario = properties.getProperty("db.usuario");
            password = properties.getProperty("db.password");
        } catch (IOException e) {
            System.err.println("No se pudo cargar el archivo db.properties");
            throw e;
        }
    }

    // PATRON BUILDER AQUI !!
    private class CadenaConexionBuilder {
        //original: jdbc:mysql://%s:%d/%s?useSSL=false&allowPublicKeyRetrieval=true;
        private String host;
        private int puerto;
        private String esquema;
        private String formato;

        public CadenaConexionBuilder() {
            formato = "jdbc:mysql://";
        }

        public String getHost() {
            return host;
        }

        public CadenaConexionBuilder setHost(String host) {
            this.host = host;
            formato = formato + String.format("%s", host);
            return this;
        }

        public int getPuerto() {
            return puerto;
        }

        public CadenaConexionBuilder setPuerto(int puerto) {
            this.puerto = puerto;
            formato = formato + String.format(":%d", puerto);
            return this;
        }

        public String getEsquema() {
            return esquema;
        }

        public CadenaConexionBuilder setEsquema(String esquema) {
            this.esquema = esquema;
            formato = formato + String.format("/%s", esquema);
            return this;
        }

        public String build(){
            return formato = formato + "?useSSL=false&allowPublicKeyRetrieval=true";
        }
    }
    // FIN PATRON BUILDER
    //ENUNCIADO:
    /*
    Actualiza el método cadenaConexion del proyecto CitasVetDBManager para delegar la construcción de la
    cadena de conexión a una clase denominada CadenaConexionBuilder, la cual deberá implementar una
    lógica flexible y mantenible para ensamblar los distintos componentes de la cadena.
    El patrón Builder separa la construcción de un objeto complejo de su representación, permitiendo crear
    distintas versiones del objeto paso a paso, mediante una clase constructora especializada que encapsula el
    proceso de ensamblaje. Es útil cuando se requiere mayor control y claridad en la creación de objetos.
    */
    private String cadenaConexion(String host, int puerto, String esquema) {
        CadenaConexionBuilder builder = new CadenaConexionBuilder()
                .setHost(host)
                .setPuerto(puerto)
                .setEsquema(esquema);
        return builder.build();
//      return String.format("jdbc:mysql://%s:%d/%s?useSSL=false&allowPublicKeyRetrieval=true", host, puerto, esquema);
    }
}
