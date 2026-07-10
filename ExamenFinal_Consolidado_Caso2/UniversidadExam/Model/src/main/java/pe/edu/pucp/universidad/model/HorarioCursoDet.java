package pe.edu.pucp.universidad.model;

import java.time.LocalDateTime;

public class HorarioCursoDet {
    private Long id;
    private HorarioCurso horarioCurso;
    private String tipoSesion;
    private int diaSemana;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
    private Aula aula;
    private String frecuencia;

    public HorarioCursoDet() { }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public HorarioCurso getHorarioCurso() { return horarioCurso; }
    public void setHorarioCurso(HorarioCurso horarioCurso) { this.horarioCurso = horarioCurso; }

    public String getTipoSesion() { return tipoSesion; }
    public void setTipoSesion(String tipoSesion) { this.tipoSesion = tipoSesion; }

    public int getDiaSemana() { return diaSemana; }
    public void setDiaSemana(int diaSemana) { this.diaSemana = diaSemana; }

    public LocalDateTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalDateTime horaInicio) { this.horaInicio = horaInicio; }

    public LocalDateTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalDateTime horaFin) { this.horaFin = horaFin; }

    public Aula getAula() { return aula; }
    public void setAula(Aula aula) { this.aula = aula; }

    public String getFrecuencia() { return frecuencia; }
    public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }

}