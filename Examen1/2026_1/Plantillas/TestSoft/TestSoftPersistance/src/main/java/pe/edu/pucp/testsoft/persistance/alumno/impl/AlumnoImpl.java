package pe.edu.pucp.testsoft.persistance.alumno.impl;

import pe.edu.pucp.testsoft.config.DBManager;
import pe.edu.pucp.testsoft.model.alumno.Alumno;
import pe.edu.pucp.testsoft.persistance.alumno.dao.AlumnoDAO;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlumnoImpl implements AlumnoDAO {

    private final DBManager dbManager = DBManager.getInstance();

    // equivalente al Insertar() de C#: parámetro OUT para obtener el id generado
    @Override
    public int insertar(Alumno alumno) {
        int resultado = 0;
        try {
            Map<Integer, Object> entrada = new HashMap<>();
            entrada.put(1, alumno.getCodigo());
            entrada.put(2, alumno.getNombre());
            entrada.put(3, alumno.getCorreo());

            // parámetro OUT: posicion 4, tipo INTEGER (como ParameterDirection.Output en C#)
            Map<Integer, Object> salida = new HashMap<>();
            salida.put(4, Types.INTEGER);

            resultado = dbManager.ejecutarProcedimiento("insertar_alumno", entrada, salida);

            // recuperar el id generado (como cmd.Parameters["p_id"].Value en C#)
            if (resultado > 0) {
                alumno.setId((int) salida.get(4));
                resultado = alumno.getId();
            }

        } catch (Exception ex) {
            System.out.println("Error al insertar alumno: " + ex.getMessage());
        }
        return resultado;
    }

    @Override
    public int modificar(Alumno alumno) {
        int resultado = 0;
        try {
            Map<Integer, Object> entrada = new HashMap<>();
            entrada.put(1, alumno.getId());
            entrada.put(2, alumno.getCodigo());
            entrada.put(3, alumno.getNombre());
            entrada.put(4, alumno.getCorreo());

            resultado = dbManager.ejecutarProcedimiento("modificar_alumno", entrada, null);

        } catch (Exception ex) {
            System.out.println("Error al modificar alumno: " + ex.getMessage());
        }
        return resultado;
    }

    @Override
    public int eliminar(int id) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, id);
        return DBManager.getInstance().ejecutarProcedimiento("eliminar_alumno", parametrosEntrada, null);
    }

    // equivalente al BuscarPorNombre() / BuscarPorId() de C#
    @Override
    public Alumno buscarPorId(int id) {
        Alumno alumno = null;
        Map<Integer, Object> entrada = new HashMap<>();
        entrada.put(1, id);

        try (DBManager.ResultadoConsulta rc =
                     dbManager.ejecutarProcedimientoLectura("buscar_alumno_por_id", entrada)) {

            if (rc.getRs().next()) {
                alumno = new Alumno();
                alumno.setId(rc.getRs().getInt("id"));
                alumno.setCodigo(rc.getRs().getString("codigo"));
                alumno.setNombre(rc.getRs().getString("nombre"));
                alumno.setCorreo(rc.getRs().getString("correo"));
            }

        } catch (Exception ex) {
            System.out.println("Error al buscar alumno: " + ex.getMessage());
        }
        return alumno;
    }

    @Override
    public List<Alumno> listarTodos() {
        List<Alumno> lista = new ArrayList<>();

        try (DBManager.ResultadoConsulta rc =
                     dbManager.ejecutarProcedimientoLectura("listar_alumnos", null)) {

            while (rc.getRs().next()) {
                Alumno a = new Alumno();
                a.setId(rc.getRs().getInt("id"));
                a.setCodigo(rc.getRs().getString("codigo"));
                a.setNombre(rc.getRs().getString("nombre"));
                a.setCorreo(rc.getRs().getString("correo"));
                lista.add(a);
            }

        } catch (Exception ex) {
            System.out.println("Error al listar alumnos: " + ex.getMessage());
        }
        return lista;
    }

    // *** PATRON SELECT INTO ***
    // SP: buscar_alumno_por_codigo(IN p_codigo, OUT p_id)
    // Igual que buscar_tipo_pokemon_por_nombre en Lab7
    // El SP hace: SELECT id INTO p_id FROM alumno WHERE codigo = p_codigo
    // NO retorna ResultSet, retorna el id via parametro OUT
    // Uso: verificar si un alumno ya existe antes de insertar (evitar duplicados)
    @Override
    public int buscarPorCodigo(String codigo) {
        Map<Integer, Object> entrada = new HashMap<>();
        Map<Integer, Object> salida = new HashMap<>();
        entrada.put(1, codigo);
        salida.put(2, Types.INTEGER);

        dbManager.ejecutarProcedimiento("buscar_alumno_por_codigo", entrada, salida);

        Object resultado = salida.get(2);
        if (resultado == null || (int) resultado == 0) return 0;
        return (int) resultado;
    }
}