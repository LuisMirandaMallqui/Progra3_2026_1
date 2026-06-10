package pe.edu.pucp.testsoft.persistance.examen.Impl;

import pe.edu.pucp.testsoft.config.DBManager;
import pe.edu.pucp.testsoft.model.Examen;
import pe.edu.pucp.testsoft.persistance.examen.DAO.ExamenDAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamenImpl implements ExamenDAO {

    @Override
    public List<Examen> listarPendientesPorAlumno(int idAlumno) {
        List<Examen> lista = new ArrayList<>();
        Map<Integer, Object> entrada = new HashMap<>();
        entrada.put(1, idAlumno);
        try (DBManager.ResultadoConsulta rc =
                     DBManager.getInstance().ejecutarProcedimientoLectura("SP_LISTAR_EXAMENES_PENDIENTES_X_ALUMNO", entrada)) {
            ResultSet rs = rc.getRs();
            while (rs.next()) lista.add(mapear(rs));
        } catch (Exception ex) {
            System.out.println("Error al listar examenes pendientes: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public Examen buscarPorId(int id) {
        Examen examen = null;
        Map<Integer, Object> entrada = new HashMap<>();
        entrada.put(1, id);
        try (DBManager.ResultadoConsulta rc =
                     DBManager.getInstance().ejecutarProcedimientoLectura("SP_OBTENER_EXAMEN_POR_ID", entrada)) {
            ResultSet rs = rc.getRs();
            if (rs.next()) examen = mapear(rs);
        } catch (Exception ex) {
            System.out.println("Error al buscar examen por id: " + ex.getMessage());
        }
        return examen;
    }

    @Override
    public int actualizarResultado(int idExamen, String estado, int nota) {
        Map<Integer, Object> entrada = new HashMap<>();
        // SP_ACTUALIZAR_RESULTADO_EXAMEN(IN p_estado, IN p_nota, IN p_id)
        entrada.put(1, estado);
        entrada.put(2, nota);
        entrada.put(3, idExamen);
        return DBManager.getInstance().ejecutarProcedimiento("SP_ACTUALIZAR_RESULTADO_EXAMEN", entrada, null);
    }

    // CRUD genérico no usado para Examen en este caso -> implementaciones mínimas.
    @Override public int insertar(Examen objeto) { return 0; }
    @Override public int modificar(Examen objeto) { return actualizarResultado(objeto.getId(), objeto.getEstado(), objeto.getNota()); }
    @Override public int eliminar(int id) { return 0; }
    @Override public List<Examen> listarTodos() { return new ArrayList<>(); }

    private Examen mapear(ResultSet rs) throws Exception {
        Examen e = new Examen();
        e.setId(rs.getInt("id"));
        e.setTitulo(rs.getString("titulo"));
        e.setFechaCreacion(rs.getString("fechaCreacion"));
        e.setFechaResolucion(rs.getString("fechaResolucion"));
        e.setEstado(rs.getString("estado"));
        e.setNota(rs.getInt("nota"));
        e.setIdAlumno(rs.getInt("id_alumno"));
        return e;
    }
}
