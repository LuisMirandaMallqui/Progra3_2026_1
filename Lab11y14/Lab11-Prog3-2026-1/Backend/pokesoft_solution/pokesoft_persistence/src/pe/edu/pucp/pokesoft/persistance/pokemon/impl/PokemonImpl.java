package pe.edu.pucp.pokesoft.persistance.pokemon.impl;

import pe.edu.pucp.pokesoft.dbmanager.DBManager;
import pe.edu.pucp.pokesoft.model.Pokemon;
import pe.edu.pucp.pokesoft.persistance.pokemon.dao.PokemonDAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokemonImpl implements PokemonDAO {
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;

    public Pokemon getPokemon(int indice){
        Pokemon pokemon = null;
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1,indice);
        try (DBManager.ResultadoConsulta resultado = DBManager.getInstance().ejecutarProcedimientoLectura("obtener_pokemon_por_indice", parametrosEntrada)) {
            ResultSet rs = resultado.getRs();
            if (rs.next()) {
                pokemon = new Pokemon();
                pokemon.setIdPokemon(rs.getInt("id"));
                pokemon.setNombre(rs.getString("Nombre"));
                pokemon.setAltura(rs.getDouble("Altura"));
                pokemon.setPeso(rs.getDouble("Peso"));
                pokemon.setEstadoEvolutivo(rs.getString("EstadoEvolutivo"));
                pokemon.setTipo(rs.getString("Tipo"));
                pokemon.setDescripcion(rs.getString("Descripcion"));
                pokemon.setImagenUrl(rs.getString("ImagenUrl"));
            }
        } catch (Exception ex) {
            System.out.println("Error al buscar empleado por id: " + ex.getMessage());
        }
        return pokemon;
    }

    public int getTotalCount(){
        int total = 0;
        try (DBManager.ResultadoConsulta resultado = DBManager.getInstance().ejecutarProcedimientoLectura("obtener_total_pokemon", null)) {
            ResultSet rs = resultado.getRs();
            while (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception ex) {
            System.out.println("ERROR al contar pokemons: " + ex.getMessage());
        }
        return total;
    }

    @Override
    public int insertar(Pokemon objeto) {
        return 0;
    }

    @Override
    public int modificar(Pokemon objeto) {
        return 0;
    }

    @Override
    public int eliminar(int id) {
        return 0;
    }

    @Override
    public Pokemon buscarPorId(int id) {
        return null;
    }

    @Override
    public List<Pokemon> listarTodos() {
        return List.of();
    }
}
