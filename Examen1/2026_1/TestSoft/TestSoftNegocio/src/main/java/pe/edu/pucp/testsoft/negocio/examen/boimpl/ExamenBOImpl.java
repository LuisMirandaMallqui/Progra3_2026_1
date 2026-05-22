package pe.edu.pucp.testsoft.negocio.examen.boimpl;

import pe.edu.pucp.testsoft.model.alumno.Alumno;
import pe.edu.pucp.testsoft.model.examen.Examen;
import pe.edu.pucp.testsoft.model.pregunta.Pregunta;
import pe.edu.pucp.testsoft.negocio.examen.bo.ExamenBO;
import pe.edu.pucp.testsoft.persistance.examen.dao.ExamenDAO;
import pe.edu.pucp.testsoft.persistance.examen.impl.ExamenImpl;

import java.util.List;

public class ExamenBOImpl implements ExamenBO {

    private ExamenDAO examenDAO = new ExamenImpl();

    @Override
    public int insertar(Examen objeto) throws Exception {
        return examenDAO.insertar(objeto);
    }

    @Override
    public int modificar(Examen objeto) throws Exception {
        return examenDAO.modificar(objeto);
    }

    @Override
    public int eliminar(int id) throws Exception {
        return examenDAO.eliminar(id);
    }

    @Override
    public Examen buscarPorId(int id) throws Exception {
        return examenDAO.buscarPorId(id);
    }

    @Override
    public List<Examen> leerTodos() {
        return examenDAO.listarTodos();
    }

    @Override
    public void crearExamenConPreguntas(Alumno alumno, String titulo, List<Pregunta> preguntas) {
        Examen examen = new Examen(alumno, titulo, preguntas);
        int idExamen = examenDAO.insertar(examen);
        System.out.println("Examen creado: " + idExamen + " - Fecha: " + examen.getFechaCreacion());
    }
}
