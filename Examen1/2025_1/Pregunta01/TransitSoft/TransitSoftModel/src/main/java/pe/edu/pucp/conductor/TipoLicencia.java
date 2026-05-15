package pe.edu.pucp.conductor;

public class TipoLicencia {
    private int tipoLicenciaId;
    private String nombre;
    private String descripcion;

    public TipoLicencia() {
    }

    public TipoLicencia(int tipoLicenciaId, String nombre, String descripcion) {
        this.tipoLicenciaId = tipoLicenciaId;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getTipoLicenciaId() {
        return tipoLicenciaId;
    }

    public void setTipoLicenciaId(int tipoLicenciaId) {
        this.tipoLicenciaId = tipoLicenciaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
