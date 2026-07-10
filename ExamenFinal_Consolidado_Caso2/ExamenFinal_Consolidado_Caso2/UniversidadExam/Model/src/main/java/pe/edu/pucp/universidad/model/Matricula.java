package pe.edu.pucp.universidad.model;

import java.time.LocalDateTime;

public class Matricula {
    private Long id;
    private Estudiante estudiante;
    private String semestre;
    private LocalDateTime fechaInscripcion;
    private String tipoMatricula;
    private String estadoMatricula;
    private String modalidad;

    public Matricula() { }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }

    public String getSemestre() { return semestre; }
    public void setSemestre(String semestre) { this.semestre = semestre; }

    public LocalDateTime getFechaInscripcion() { return fechaInscripcion; }
    public void setFechaInscripcion(LocalDateTime fechaInscripcion) { this.fechaInscripcion = fechaInscripcion; }

    public String getTipoMatricula() { return tipoMatricula; }
    public void setTipoMatricula(String tipoMatricula) { this.tipoMatricula = tipoMatricula; }

    public String getEstadoMatricula() { return estadoMatricula; }
    public void setEstadoMatricula(String estadoMatricula) { this.estadoMatricula = estadoMatricula; }

    public String getModalidad() { return modalidad; }
    public void setModalidad(String modalidad) { this.modalidad = modalidad; }

}