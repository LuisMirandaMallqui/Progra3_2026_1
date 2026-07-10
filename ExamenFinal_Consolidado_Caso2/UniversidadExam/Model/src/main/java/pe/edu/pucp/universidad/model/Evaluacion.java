package pe.edu.pucp.universidad.model;

import java.time.LocalDateTime;

public class Evaluacion {
    private Long id;
    private HorarioCurso horarioCurso;
    private String tipoEvaluacion;
    private double peso;
    private LocalDateTime fechaEvaluacion;

    public Evaluacion() { }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public HorarioCurso getHorarioCurso() { return horarioCurso; }
    public void setHorarioCurso(HorarioCurso horarioCurso) { this.horarioCurso = horarioCurso; }

    public String getTipoEvaluacion() { return tipoEvaluacion; }
    public void setTipoEvaluacion(String tipoEvaluacion) { this.tipoEvaluacion = tipoEvaluacion; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public LocalDateTime getFechaEvaluacion() { return fechaEvaluacion; }
    public void setFechaEvaluacion(LocalDateTime fechaEvaluacion) { this.fechaEvaluacion = fechaEvaluacion; }

}