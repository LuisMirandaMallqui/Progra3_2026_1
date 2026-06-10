package pe.edu.pucp.testsoft.business.examen.impl;

import pe.edu.pucp.testsoft.business.examen.bo.IExamenBO;
import pe.edu.pucp.testsoft.model.Examen;
import pe.edu.pucp.testsoft.persistance.examen.DAO.ExamenDAO;
import pe.edu.pucp.testsoft.persistance.examen.Impl.ExamenImpl;

import java.util.List;

public class ExamenBOImpl implements IExamenBO {

    private final ExamenDAO examenDAO = new ExamenImpl();

    @Override
    public List<Examen> listarPendientesPorAlumno(int idAlumno) throws Exception {
        if (idAlumno <= 0) throw new Exception("El id del alumno es inválido.");
        return examenDAO.listarPendientesPorAlumno(idAlumno);
    }

    @Override
    public Examen buscarPorId(int id) throws Exception {
        if (id <= 0) throw new Exception("El id del examen es inválido.");
        return examenDAO.buscarPorId(id);
    }

    @Override
    public int actualizarResultado(int idExamen, String estado, int nota) throws Exception {
        if (idExamen <= 0) throw new Exception("El id del examen es inválido.");
        if (nota < 0 || nota > 20) throw new Exception("La nota debe estar entre 0 y 20.");
        return examenDAO.actualizarResultado(idExamen, estado, nota);
    }
}
