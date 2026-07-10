package pe.edu.pucp.universidad.model;

public class Estudiante {
    private Long id;
    private Especialidad especialidad;
    private String codigoUniversitario;
    private String nombres;
    private String apellidos;
    private String correoInstitucional;
    private boolean activo;

    public Estudiante() { }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Especialidad getEspecialidad() { return especialidad; }
    public void setEspecialidad(Especialidad especialidad) { this.especialidad = especialidad; }

    public String getCodigoUniversitario() { return codigoUniversitario; }
    public void setCodigoUniversitario(String codigoUniversitario) { this.codigoUniversitario = codigoUniversitario; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getCorreoInstitucional() { return correoInstitucional; }
    public void setCorreoInstitucional(String correoInstitucional) { this.correoInstitucional = correoInstitucional; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

}