package pe.edu.pucp.pokemones.dao.Implements;

import pe.edu.pucp.pokemones.dao.TipoPokemonDAO;
import pe.edu.pucp.pokemones.manager.DBManager;
import pe.edu.pucp.pokemones.model.TipoPokemon;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// REFACTORIZADO EN LA MEJORA: mismo motivo que PokemonImplementsDAO
// (métodos del DBManager + variables locales + eliminar implementado).
public class TipoPokemonImplementsDAO implements TipoPokemonDAO {

    @Override
    public int insertar(TipoPokemon t) {
        Map<Integer, Object> in = new HashMap<>(), out = new HashMap<>();
        out.put(1, Types.INTEGER);                 // INSERTAR_TIPO_POKEMON(OUT p_id, IN p_nombre)
        in.put(2, t.getNombre());
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_TIPO_POKEMON", in, out);
        t.setIdTipoPokemon((int) out.get(1));
        return t.getIdTipoPokemon();
    }

    @Override
    public int modificar(TipoPokemon t) {
        Map<Integer, Object> in = new HashMap<>();
        in.put(1, t.getIdTipoPokemon());           // MODIFICAR_TIPO_POKEMON(IN p_id, p_nombre)
        in.put(2, t.getNombre());
        return DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_TIPO_POKEMON", in, null);
    }

    @Override
    public int eliminar(int idElemento) {
        Map<Integer, Object> in = new HashMap<>();
        in.put(1, idElemento);                     // falla por FK si el tipo tiene pokemones (correcto)
        return DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_TIPO_POKEMON", in, null);
    }

    @Override
    public TipoPokemon buscarPorId(int idElemento) {
        Map<Integer, Object> in = new HashMap<>();
        in.put(1, idElemento);
        return leerUno("BUSCAR_TIPO_POKEMON_POR_ID", in);
    }

    @Override
    public TipoPokemon buscarPorNombre(String nombre) {
        Map<Integer, Object> in = new HashMap<>();
        in.put(1, nombre);
        return leerUno("BUSCAR_TIPO_POKEMON_POR_NOMBRE", in);
    }

    @Override
    public List<TipoPokemon> listarTodos() {
        List<TipoPokemon> tipos = new ArrayList<>();
        try (DBManager.ResultadoConsulta rc =
                     DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_TODOS_TIPOS_POKEMONES", null)) {
            ResultSet rs = rc.getRs();
            while (rs.next()) tipos.add(mapear(rs));
        } catch (Exception ex) {
            System.out.println("Error al listar tipos de pokemones: " + ex.getMessage());
        }
        return tipos;
    }

    private TipoPokemon leerUno(String sp, Map<Integer, Object> in) {
        TipoPokemon tipo = null;
        try (DBManager.ResultadoConsulta rc =
                     DBManager.getInstance().ejecutarProcedimientoLectura(sp, in)) {
            ResultSet rs = rc.getRs();
            if (rs.next()) tipo = mapear(rs);
        } catch (Exception ex) {
            System.out.println("Error al buscar tipo de pokemon: " + ex.getMessage());
        }
        return tipo;
    }

    private TipoPokemon mapear(ResultSet rs) throws Exception {
        TipoPokemon t = new TipoPokemon();
        t.setIdTipoPokemon(rs.getInt("id_tipo"));
        t.setNombre(rs.getString("nombre"));
        return t;
    }
}
