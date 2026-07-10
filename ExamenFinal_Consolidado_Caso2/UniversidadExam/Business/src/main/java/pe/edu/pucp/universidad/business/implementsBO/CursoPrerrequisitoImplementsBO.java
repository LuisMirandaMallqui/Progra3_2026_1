package pe.edu.pucp.universidad.business.implementsBO;

import java.util.List;
import pe.edu.pucp.universidad.business.CursoPrerrequisitoBO;
import pe.edu.pucp.universidad.dao.CursoPrerrequisitoDAO;
import pe.edu.pucp.universidad.impl.CursoPrerrequisitoDAOImpl;
import pe.edu.pucp.universidad.model.CursoPrerrequisito;

public class CursoPrerrequisitoImplementsBO implements CursoPrerrequisitoBO {
    private CursoPrerrequisitoDAO dao;

    public CursoPrerrequisitoImplementsBO() {
        dao = new CursoPrerrequisitoDAOImpl();
    }

    public int insertar(CursoPrerrequisito elemento) {
        try { return dao.insertar(elemento); } catch (Exception ex) { ex.printStackTrace(); return 0; }
    }

    public int modificar(CursoPrerrequisito elemento) {
        try { return dao.modificar(elemento); } catch (Exception ex) { ex.printStackTrace(); return 0; }
    }

    public int eliminar(int idCurso, int idCursoPrerreq) {
        try { return dao.eliminar(idCurso, idCursoPrerreq); } catch (Exception ex) { ex.printStackTrace(); return 0; }
    }

    public CursoPrerrequisito buscarPorId(int idCurso, int idCursoPrerreq) {
        try { return dao.obtenerPorId(idCurso, idCursoPrerreq); } catch (Exception ex) { ex.printStackTrace(); return null; }
    }

    public List<CursoPrerrequisito> listarTodos() {
        try { return dao.listarTodos(); } catch (Exception ex) { ex.printStackTrace(); return List.of(); }
    }
}
