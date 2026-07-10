package pe.edu.pucp.universidad.model;

public class Aula {
    private Long id;
    private String codigoAula;
    private String ubicacion;
    private boolean activo;

    public Aula() { }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigoAula() { return codigoAula; }
    public void setCodigoAula(String codigoAula) { this.codigoAula = codigoAula; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

}