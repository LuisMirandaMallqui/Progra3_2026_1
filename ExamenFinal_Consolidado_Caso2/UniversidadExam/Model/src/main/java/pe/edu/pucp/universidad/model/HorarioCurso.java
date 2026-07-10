package pe.edu.pucp.universidad.model;

public class HorarioCurso {
    private Long id;
    private Curso curso;
    private String semestre;
    private String codigoHorario;
    private Docente docente;
    private int cupoMaximo;
    private String estado;

    public HorarioCurso() { }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }

    public String getSemestre() { return semestre; }
    public void setSemestre(String semestre) { this.semestre = semestre; }

    public String getCodigoHorario() { return codigoHorario; }
    public void setCodigoHorario(String codigoHorario) { this.codigoHorario = codigoHorario; }

    public Docente getDocente() { return docente; }
    public void setDocente(Docente docente) { this.docente = docente; }

    public int getCupoMaximo() { return cupoMaximo; }
    public void setCupoMaximo(int cupoMaximo) { this.cupoMaximo = cupoMaximo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

}