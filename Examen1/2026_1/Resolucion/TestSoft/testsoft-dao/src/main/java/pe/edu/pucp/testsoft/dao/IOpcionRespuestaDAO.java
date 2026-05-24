package pe.edu.pucp.testsoft.dao;

import pe.edu.pucp.testsoft.model.OpcionRespuesta;

import java.util.ArrayList;

public interface IOpcionRespuestaDAO {
    public ArrayList<OpcionRespuesta> obtenerOpciones (int idPregunta);
}
