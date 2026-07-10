package pe.edu.pucp.universidad.model;

public class Especialidad {
    private Long id;
    private Facultad facultad;
    private String nombre;
    private boolean activo;

    public Especialidad() { }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Facultad getFacultad() { return facultad; }
    public void setFacultad(Facultad facultad) { this.facultad = facultad; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

}