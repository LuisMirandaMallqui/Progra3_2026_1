package pe.edu.pucp.testsoft.model;

import java.sql.Date;
import java.util.ArrayList;

public class Examen {
    private int id;
    private Alumno alumno;
    private String titulo;
    private Date fechaCreacion;
    private Date fechaResolucion;
    private EstadoExamen estado;
    private int nota;
    private ArrayList<ExamenPregunta> preguntas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public EstadoExamen getEstado() {
        return estado;
    }

    public void setEstado(EstadoExamen estado) {
        this.estado = estado;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public ArrayList<ExamenPregunta> getPreguntas() {
        //
        
        //
        return preguntas;
    }

    public void setPreguntas(ArrayList<ExamenPregunta> preguntas) {
        this.preguntas = preguntas;
    }
}
