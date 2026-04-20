package pe.edu.pucp.softprog.rrhh.model;

public class CuentaUsuario {
    private int idCuentaUsuario;
    private String username;
    private String password;
    private boolean activo;

    public int getIdCuentaUsuario() {
        return idCuentaUsuario;
    }

    public void setIdCuentaUsuario(int idCuentaUsuario) {
        this.idCuentaUsuario = idCuentaUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
