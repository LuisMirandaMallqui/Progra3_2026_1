package pe.edu.pucp.testsoft.model.examen;

import pe.edu.pucp.testsoft.model.pregunta.Pregunta;

public class ExamenPregunta {
    private int id;
    private Examen examen;
    private Pregunta pregunta;
    private int orden;

    public ExamenPregunta() {
    }

    public ExamenPregunta(Examen examen, Pregunta pregunta, int orden) {
        this.examen = examen;
        this.pregunta = pregunta;
        this.orden = orden;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
}
