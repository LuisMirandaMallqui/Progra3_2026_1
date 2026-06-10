package pe.edu.pucp.pokemones.dao.Implements;

import pe.edu.pucp.pokemones.dao.PokemonDAO;
import pe.edu.pucp.pokemones.manager.DBManager;
import pe.edu.pucp.pokemones.model.Pokemon;
import pe.edu.pucp.pokemones.model.enums.EstadoEvolutivo;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ============================================================================
// REFACTORIZADO EN LA MEJORA:
//  - Antes abría/cerraba la conexión a mano y guardaba con/cs/rs como CAMPOS de
//    instancia -> NO es thread-safe. En un WS llegan varias peticiones a la vez
//    y compartir esos campos provoca condiciones de carrera. Ahora se usan los
//    métodos del DBManager (ejecutarProcedimiento / ...Lectura) con variables
//    locales, igual que el patrón del profe Paz.
//  - listar/buscar ahora leen "nombre_tipo" (viene del JOIN nuevo) y lo guardan
//    en stringTipoPokemon, para que el front muestre el NOMBRE del tipo.
//  - se implementa eliminar (antes devolvía 0).
//  Parámetros POSICIONALES: la clave del Map = posición del ? en {call sp(?,...)}.
// ============================================================================
public class PokemonImplementsDAO implements PokemonDAO {

    @Override
    public int insertar(Pokemon p) {
        Map<Integer, Object> in = new HashMap<>(), out = new HashMap<>();
        // INSERTAR_POKEMON(OUT p_id, IN p_fid_tipo, p_nombre, p_altura, p_peso, p_estado_evolutivo, p_descripcion)
        out.put(1, Types.INTEGER);                       // OUT primero
        in.put(2, p.getIdTipoPokemon());
        in.put(3, p.getNombre());
        in.put(4, p.getAltura());
        in.put(5, p.getPeso());
        in.put(6, p.getEstadoEvolutivo().name());        // enum -> texto ("BASICO")
        in.put(7, p.getDescripcion());
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_POKEMON", in, out);
        p.setIdPokemon((int) out.get(1));
        return p.getIdPokemon();
    }

    @Override
    public int modificar(Pokemon p) {
        Map<Integer, Object> in = new HashMap<>();
        // MODIFICAR_POKEMON(IN p_id, p_fid_tipo, p_nombre, p_altura, p_peso, p_estado_evolutivo, p_descripcion)
        in.put(1, p.getIdPokemon());
        in.put(2, p.getIdTipoPokemon());
        in.put(3, p.getNombre());
        in.put(4, p.getAltura());
        in.put(5, p.getPeso());
        in.put(6, p.getEstadoEvolutivo().name());
        in.put(7, p.getDescripcion());
        return DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_POKEMON", in, null);
    }

    @Override
    public int eliminar(int idElemento) {
        Map<Integer, Object> in = new HashMap<>();
        in.put(1, idElemento);
        return DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_POKEMON", in, null);
    }

    @Override
    public Pokemon buscarPorId(int idElemento) {
        Pokemon pokemon = null;
        Map<Integer, Object> in = new HashMap<>();
        in.put(1, idElemento);
        try (DBManager.ResultadoConsulta rc =
                     DBManager.getInstance().ejecutarProcedimientoLectura("BUSCAR_POKEMON_POR_ID", in)) {
            ResultSet rs = rc.getRs();
            if (rs.next()) pokemon = mapear(rs);
        } catch (Exception ex) {
            System.out.println("Error al buscar pokemon: " + ex.getMessage());
        }
        return pokemon;
    }

    @Override
    public List<Pokemon> listarTodos() {
        List<Pokemon> pokemones = new ArrayList<>();
        try (DBManager.ResultadoConsulta rc =
                     DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_TODOS_POKEMONES", null)) {
            ResultSet rs = rc.getRs();
            while (rs.next()) pokemones.add(mapear(rs));
        } catch (Exception ex) {
            System.out.println("Error al listar pokemones: " + ex.getMessage());
        }
        return pokemones;
    }

    // Mapeo fila -> objeto en un solo lugar (incluye el nombre del tipo del JOIN).
    private Pokemon mapear(ResultSet rs) throws Exception {
        Pokemon p = new Pokemon();
        p.setIdPokemon(rs.getInt("id_pokemon"));
        p.setIdTipoPokemon(rs.getInt("fid_tipo"));
        // NUEVO: nombre del tipo (viene del JOIN de Procedimientos_Mejoras.sql).
        // Defensivo: si todavía no corriste ese script, la columna no existe y NO queremos
        // que listar/buscar truenen por eso -> lo ignoramos.
        try { p.setStringTipoPokemon(rs.getString("nombre_tipo")); } catch (Exception ignore) { }
        p.setNombre(rs.getString("nombre"));
        p.setAltura(rs.getDouble("altura"));
        p.setPeso(rs.getDouble("peso"));
        p.setEstadoEvolutivo(EstadoEvolutivo.valueOf(rs.getString("estado_evolutivo")));
        p.setDescripcion(rs.getString("descripcion"));
        return p;
    }
}
