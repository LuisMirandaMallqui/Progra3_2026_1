package pe.edu.pucp.universidad.model;

public class Curso {
    private Long id;
    private Especialidad especialidad;
    private String codigo;
    private String nombre;
    private int creditos;
    private int nivelAcademico;
    private String estadoCurso;
    private boolean activo;

    public Curso() { }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Especialidad getEspecialidad() { return especialidad; }
    public void setEspecialidad(Especialidad especialidad) { this.especialidad = especialidad; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCreditos() { return creditos; }
    public void setCreditos(int creditos) { this.creditos = creditos; }

    public int getNivelAcademico() { return nivelAcademico; }
    public void setNivelAcademico(int nivelAcademico) { this.nivelAcademico = nivelAcademico; }

    public String getEstadoCurso() { return estadoCurso; }
    public void setEstadoCurso(String estadoCurso) { this.estadoCurso = estadoCurso; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

}