package pe.edu.pucp.testsoft.examen;

import pe.edu.pucp.testsoft.examen.dao.ExamenDAO;
import pe.edu.pucp.testsoft.examen.impl.ExamenImpl;
import pe.edu.pucp.testsoft.model.alumno.Alumno;
import pe.edu.pucp.testsoft.model.examen.Examen;
import pe.edu.pucp.testsoft.model.pregunta.Pregunta;
import pe.edu.pucp.testsoft.pregunta.dao.PreguntaDAO;
import pe.edu.pucp.testsoft.pregunta.impl.PreguntaImpl;

import java.util.List;

public class ExamenBOImpl implements  ExamenBO{
    private ExamenDAO examenDAO;

    @Override
    public int insertar(Examen objeto) throws Exception {
        //validar(objeto, false);
        ExamenDAO examenDAO = new ExamenImpl();
        return examenDAO.insertar(objeto);
    }


    @Override
    public int modificar(Examen objeto) throws Exception {
        return 0;
    }

    @Override
    public int eliminar(int id) throws Exception {
        return 0;
    }

    @Override
    public List<Examen> leerTodos()  {
        examenDAO = new ExamenImpl();
        return examenDAO.listarTodos();
    }

    @Override
    public Examen buscarPorId(int id) throws Exception {
        return null;
    }

    public void crearExamenConPreguntas(Alumno alumno, String cadena, List<Pregunta> preguntasSeleccionadas){
        Examen examen = new Examen(alumno,cadena,preguntasSeleccionadas);
        examenDAO = new ExamenImpl();

        int idExamen = examenDAO.insertar(examen);
        // recuperar de una Id y Fecha en el ObjetoModel
        examen = examenDAO.buscarPorId(idExamen);
        System.out.println("Examen creado: " + examen.getId() + " - Fecha: " + examen.getFechaCreacion());


        PreguntaDAO preguntaDAO = new PreguntaImpl();


    }
}
