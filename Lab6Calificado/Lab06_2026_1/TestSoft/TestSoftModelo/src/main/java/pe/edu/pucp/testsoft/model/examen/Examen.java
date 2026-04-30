package pe.edu.pucp.testsoft.model.examen;

import pe.edu.pucp.testsoft.model.alumno.Alumno;
import pe.edu.pucp.testsoft.model.pregunta.Pregunta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Examen {
    private int id;
    private Date fechaCreacion;
    private int id_alumno;
    //private Alumno alumno;
    private String titulo;
    private List<Pregunta> preguntas;

    public Examen() {
        preguntas = new ArrayList<Pregunta>();
    }

    public Examen(int id, Date fechaCreacion, int id_alumno, String titulo, List<Pregunta> preguntas) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.id_alumno = id_alumno;
        this.titulo = titulo;
        this.preguntas = preguntas;
    }

    public Examen(int id_alumno, String titulo, List<Pregunta> preguntas) {
        this.id_alumno = id_alumno;
        this.titulo = titulo;
        this.preguntas = preguntas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

}
