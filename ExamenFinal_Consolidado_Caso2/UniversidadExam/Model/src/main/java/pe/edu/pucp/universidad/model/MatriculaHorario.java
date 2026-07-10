package pe.edu.pucp.universidad.model;

import java.time.LocalDateTime;

public class MatriculaHorario {
    private Matricula matricula;
    private HorarioCurso horarioCurso;
    private LocalDateTime fechaRegistro;

    public MatriculaHorario() { }

    public Matricula getMatricula() { return matricula; }
    public void setMatricula(Matricula matricula) { this.matricula = matricula; }

    public HorarioCurso getHorarioCurso() { return horarioCurso; }
    public void setHorarioCurso(HorarioCurso horarioCurso) { this.horarioCurso = horarioCurso; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

}