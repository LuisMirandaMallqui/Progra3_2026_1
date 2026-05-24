package pe.edu.pucp.testsoft.dao;

import pe.edu.pucp.testsoft.model.ExamenPregunta;

import java.util.ArrayList;

public interface IExamenPreguntaDAO {
    public ArrayList<ExamenPregunta> obtenerPreguntas(int idExamen);
}
