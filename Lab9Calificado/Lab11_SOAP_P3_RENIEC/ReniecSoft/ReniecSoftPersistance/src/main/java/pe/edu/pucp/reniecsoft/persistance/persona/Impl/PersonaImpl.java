package pe.edu.pucp.reniecsoft.persistance.persona.Impl;

import pe.edu.pucp.reniecsoft.config.DBManager;
import pe.edu.pucp.reniecsoft.model.Persona;
import pe.edu.pucp.reniecsoft.persistance.persona.DAO.PersonaDAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonaImpl implements PersonaDAO {
    private Connection con;
    private CallableStatement cs;

    @Override
    public int insertar(Persona objeto) {
        return 0;
    }

    @Override
    public int modificar(Persona objeto) {
        return 0;
    }

    @Override
    public int eliminar(String dni) {
        return 0;
    }

    //IMPORTANTE
    @Override
    public Persona buscarPorDni(String dni) {
        Persona persona = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1,dni);
        try (DBManager.ResultadoConsulta resultado = DBManager.getInstance().ejecutarProcedimientoLectura("obtenerPersona", parametrosEntrada)) {
            ResultSet rs = resultado.getRs();
            if (rs.next()) {
                persona = new Persona();
                persona.setDni(rs.getString("DNI"));
                persona.setMaterno(rs.getString("MATERNO"));
                persona.setPaterno(rs.getString("PATERNO"));
                persona.setNombres(rs.getString("NOMBRES"));
            }
        } catch (Exception ex) {
            System.out.println("Error al buscar persona por dni: " + ex.getMessage());
        }
        return persona;
    }

    @Override
    public List<Persona> listarTodos() {
        return List.of();
    }
}
