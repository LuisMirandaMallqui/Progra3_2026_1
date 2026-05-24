package pe.edu.pucp.testsoft.model;

import java.util.ArrayList;

public class Pregunta {
    private int id;
    private String enunciado;
    private ArrayList<OpcionRespuesta> opciones;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public ArrayList<OpcionRespuesta> getOpciones() {
        return opciones;
    }

    public void setOpciones(ArrayList<OpcionRespuesta> opciones) {
        this.opciones = opciones;
    }
}
