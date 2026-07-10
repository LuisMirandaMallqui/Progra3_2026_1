package pe.edu.pucp.universidad.business.implementsBO;

import java.util.List;
import pe.edu.pucp.universidad.business.MatriculaHorarioBO;
import pe.edu.pucp.universidad.dao.MatriculaHorarioDAO;
import pe.edu.pucp.universidad.impl.MatriculaHorarioDAOImpl;
import pe.edu.pucp.universidad.model.MatriculaHorario;

public class MatriculaHorarioImplementsBO implements MatriculaHorarioBO {
    private MatriculaHorarioDAO dao;

    public MatriculaHorarioImplementsBO() {
        dao = new MatriculaHorarioDAOImpl();
    }

    public int insertar(MatriculaHorario elemento) {
        try { return dao.insertar(elemento); } catch (Exception ex) { ex.printStackTrace(); return 0; }
    }

    public int insertarValidado(MatriculaHorario elemento) {
        try { return dao.insertarValidado(elemento); } catch (Exception ex) { ex.printStackTrace(); return 0; }
    }

    public int modificar(MatriculaHorario elemento) {
        try { return dao.modificar(elemento); } catch (Exception ex) { ex.printStackTrace(); return 0; }
    }

    public int eliminar(int idMatricula, int idHorarioCurso) {
        try { return dao.eliminar(idMatricula, idHorarioCurso); } catch (Exception ex) { ex.printStackTrace(); return 0; }
    }

    public MatriculaHorario buscarPorId(int idMatricula, int idHorarioCurso) {
        try { return dao.obtenerPorId(idMatricula, idHorarioCurso); } catch (Exception ex) { ex.printStackTrace(); return null; }
    }

    public List<MatriculaHorario> listarTodos() {
        try { return dao.listarTodos(); } catch (Exception ex) { ex.printStackTrace(); return List.of(); }
    }
}
