package pe.edu.pucp.reniecsoft.business.persona.impl;

import pe.edu.pucp.reniecsoft.business.persona.bo.IPersonaBO;
import pe.edu.pucp.reniecsoft.model.Persona;
import pe.edu.pucp.reniecsoft.persistance.persona.DAO.PersonaDAO;
import pe.edu.pucp.reniecsoft.persistance.persona.Impl.PersonaImpl;

import java.util.List;

public class PersonaBOImpl implements IPersonaBO {
    PersonaDAO personaDao = new PersonaImpl();

    @Override
    public int insertar(Persona objeto) throws Exception {
        return 0;
    }

    @Override
    public int modificar(Persona objeto) throws Exception {
        return 0;
    }

    @Override
    public int eliminar(String dni) throws Exception {
        return 0;
    }

    @Override
    public List<Persona> listarTodos(Persona objeto) throws Exception {
        return List.of();
    }

    @Override
    public Persona buscarPorDni(String dni) throws Exception {
        validarDni(dni);
        Persona persona = null;
        persona = personaDao.buscarPorDni(dni);
        if(persona == null){
            persona = new Persona();
            persona.setDni("00000000");
            persona.setPaterno("NO ENCONTRADO");
            persona.setMaterno("NO ENCONTRADO");
            persona.setNombres("NO ENCONTRADO");
        }
        return persona;
    }

    private void validarDni(String dni) throws Exception {
        if (dni == null || dni.trim().isEmpty()) {
            throw new Exception("El dni del usuario es obligatorio.");
        }

        dni = dni.trim();

        if (dni.length() > 8) {
            throw new Exception("El dni del usuario no puede exceder los 8 caracteres.");
        }
    }
}
