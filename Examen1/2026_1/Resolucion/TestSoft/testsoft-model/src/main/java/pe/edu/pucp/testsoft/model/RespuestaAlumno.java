package pe.edu.pucp.testsoft.model;

public class RespuestaAlumno {
    private int id;
    private ExamenPregunta pregunta;
    private OpcionRespuesta respuesta;
    private int puntajeObtenido;
    private boolean esCorrecta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ExamenPregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(ExamenPregunta pregunta) {
        this.pregunta = pregunta;
    }

    public OpcionRespuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(OpcionRespuesta respuesta) {
        this.respuesta = respuesta;
    }

    public int getPuntajeObtenido() {
        return puntajeObtenido;
    }

    public void setPuntajeObtenido(int puntajeObtenido) {
        this.puntajeObtenido = puntajeObtenido;
    }

    public boolean isEsCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(boolean esCorrecta) {
        this.esCorrecta = esCorrecta;
    }
}
