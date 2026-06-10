package pe.edu.pucp.testsoft.persistance.alumno.Impl;

import pe.edu.pucp.testsoft.config.DBManager;
import pe.edu.pucp.testsoft.model.Alumno;
import pe.edu.pucp.testsoft.persistance.alumno.DAO.AlumnoDAO;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// DAO de Alumno. TODO acceso a datos pasa por Stored Procedures (nunca SQL inline).
// Parámetros POSICIONALES: la clave del Map es la posición del ? en {call sp(?,?,...)}.
public class AlumnoImpl implements AlumnoDAO {

    @Override
    public int insertar(Alumno alumno) {
        Map<Integer, Object> entrada = new HashMap<>();
        Map<Integer, Object> salida  = new HashMap<>();
        // SP_INSERTAR_ALUMNO(OUT p_id, IN p_codigo, p_nombre, p_apellidos, p_correo, p_estado)
        salida.put(1, Types.INTEGER);              // OUT va PRIMERO (posición 1)
        entrada.put(2, alumno.getCodigo());
        entrada.put(3, alumno.getNombre());
        entrada.put(4, alumno.getApellidos());
        entrada.put(5, alumno.getCorreo());
        entrada.put(6, alumno.getEstado());
        DBManager.getInstance().ejecutarProcedimiento("SP_INSERTAR_ALUMNO", entrada, salida);
        alumno.setId((int) salida.get(1));         // recuperar el id auto_increment
        return alumno.getId();
    }

    @Override
    public int modificar(Alumno alumno) {
        Map<Integer, Object> entrada = new HashMap<>();
        // SP_MODIFICAR_ALUMNO(IN p_id, p_codigo, p_nombre, p_apellidos, p_correo, p_estado)
        entrada.put(1, alumno.getId());
        entrada.put(2, alumno.getCodigo());
        entrada.put(3, alumno.getNombre());
        entrada.put(4, alumno.getApellidos());
        entrada.put(5, alumno.getCorreo());
        entrada.put(6, alumno.getEstado());
        return DBManager.getInstance().ejecutarProcedimiento("SP_MODIFICAR_ALUMNO", entrada, null);
    }

    @Override
    public int eliminar(int id) {
        Map<Integer, Object> entrada = new HashMap<>();
        entrada.put(1, id);                        // baja lógica (estado='I') dentro del SP
        return DBManager.getInstance().ejecutarProcedimiento("SP_ELIMINAR_ALUMNO", entrada, null);
    }

    @Override
    public Alumno buscarPorId(int id) {
        Alumno alumno = null;
        Map<Integer, Object> entrada = new HashMap<>();
        entrada.put(1, id);
        try (DBManager.ResultadoConsulta rc =
                     DBManager.getInstance().ejecutarProcedimientoLectura("SP_OBTENER_ALUMNO_POR_ID", entrada)) {
            ResultSet rs = rc.getRs();
            if (rs.next()) alumno = mapear(rs);     // 0 o 1 fila -> if, no while
        } catch (Exception ex) {
            System.out.println("Error al buscar alumno por id: " + ex.getMessage());
        }
        return alumno;
    }

    @Override
    public List<Alumno> listarTodos() {
        return buscarPorNombreApellido("");        // reusa el SP de búsqueda con texto vacío
    }

    @Override
    public List<Alumno> buscarPorNombreApellido(String texto) {
        List<Alumno> lista = new ArrayList<>();
        Map<Integer, Object> entrada = new HashMap<>();
        entrada.put(1, texto);
        try (DBManager.ResultadoConsulta rc =
                     DBManager.getInstance().ejecutarProcedimientoLectura("SP_LISTAR_ALUMNOS_X_NOMBRE_APELLIDO", entrada)) {
            ResultSet rs = rc.getRs();
            while (rs.next()) lista.add(mapear(rs));
        } catch (Exception ex) {
            System.out.println("Error al listar alumnos: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public int buscarPorCodigo(String codigo) {
        // SELECT INTO: el SP mete el id en un OUT en vez de devolver filas -> NO usar lectura.
        Map<Integer, Object> entrada = new HashMap<>();
        Map<Integer, Object> salida  = new HashMap<>();
        entrada.put(1, codigo);
        salida.put(2, Types.INTEGER);
        DBManager.getInstance().ejecutarProcedimiento("SP_BUSCAR_ALUMNO_POR_CODIGO", entrada, salida);
        Object valor = salida.get(2);
        if (valor == null) return 0;               // no encontrado
        return (int) valor;
    }

    // Mapeo fila -> objeto, en un solo lugar para no repetir.
    private Alumno mapear(ResultSet rs) throws Exception {
        Alumno a = new Alumno();
        a.setId(rs.getInt("id"));
        a.setCodigo(rs.getString("codigo"));
        a.setNombre(rs.getString("nombre"));
        a.setApellidos(rs.getString("apellidos"));
        a.setCorreo(rs.getString("correo"));
        a.setEstado(rs.getString("estado"));       // CHAR(1) -> getString da "A"/"I"
        return a;
    }
}
