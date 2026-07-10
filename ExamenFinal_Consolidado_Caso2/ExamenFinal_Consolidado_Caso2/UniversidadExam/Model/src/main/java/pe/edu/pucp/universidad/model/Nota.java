package pe.edu.pucp.universidad.model;

public class Nota {
    private Long id;
    private Evaluacion evaluacion;
    private Matricula matricula;
    private HorarioCurso horarioCurso;
    private double calificacion;

    public Nota() { }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Evaluacion getEvaluacion() { return evaluacion; }
    public void setEvaluacion(Evaluacion evaluacion) { this.evaluacion = evaluacion; }

    public Matricula getMatricula() { return matricula; }
    public void setMatricula(Matricula matricula) { this.matricula = matricula; }

    public HorarioCurso getHorarioCurso() { return horarioCurso; }
    public void setHorarioCurso(HorarioCurso horarioCurso) { this.horarioCurso = horarioCurso; }

    public double getCalificacion() { return calificacion; }
    public void setCalificacion(double calificacion) { this.calificacion = calificacion; }

}