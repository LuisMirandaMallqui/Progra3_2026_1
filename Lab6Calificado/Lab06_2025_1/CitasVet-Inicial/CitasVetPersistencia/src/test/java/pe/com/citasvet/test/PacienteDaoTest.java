package pe.com.citasvet.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import pe.com.citasvet.daoimpl.TutorDAOImpl;
import pe.com.citasvet.daoimpl.PacienteDAOImpl;
import pe.com.citasvet.dao.ITutorDAO;
import pe.com.citasvet.dao.IPacienteDAO;
import pe.com.citasvet.modelo.Tutor;
import pe.com.citasvet.modelo.Paciente;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PacienteDaoTest implements ICrudDaoTest{
    private int testId;
    private int testTutorId;
    private final int idIncorrecto = 99999;
    
    @BeforeAll
    public void inicializar() {
        ITutorDAO tutorDao = new TutorDAOImpl();
        Tutor tutor = new Tutor();
        tutor.setDni("43785612");
        tutor.setNombre("Tutor de Prueba");
        tutor.setTelefono("111-111-111");
        tutor.setDireccion("Direccion de prueba");
        this.testTutorId = tutorDao.insertar(tutor);
    }
    
    @AfterAll
    public void limpiar() {
        TutorDAOImpl tutorDao = new TutorDAOImpl();
        tutorDao.eliminar(testTutorId);
    }
    
    @Test
    @Order(1)
    @Override
    public void debeInsertar() {
        IPacienteDAO pacienteDao = new PacienteDAOImpl();
        Paciente paciente = new Paciente();
        paciente.setTutor(new TutorDAOImpl().buscar(this.testTutorId));
        paciente.setNombre("Firulais");
        paciente.setEspecie("Perro");
        paciente.setRaza("Pastor Aleman");
        paciente.setEdad(5);
        paciente.setEstado("Saludable");
        
        this.testId = pacienteDao.insertar(paciente);
        assertTrue(this.testId > 0);
    }

    @Test
    @Order(2)
    @Override
    public void debeModificarSiIdExiste() {
        IPacienteDAO pacienteDao = new PacienteDAOImpl();
        Paciente paciente = new Paciente();
        paciente.setId(this.testId);
        paciente.setTutor(new TutorDAOImpl().buscar(this.testTutorId));
        paciente.setNombre("Fido");
        paciente.setEspecie("Perro");
        paciente.setRaza("Poodle");
        paciente.setEdad(10);
        paciente.setEstado("Enfermo");

        boolean modifico = pacienteDao.modificar(paciente);
        assertTrue(modifico);

        Paciente pacienteModificado = pacienteDao.buscar(this.testId);
        assertEquals(pacienteModificado.getNombre(), "Fido");
        assertEquals(pacienteModificado.getEspecie(), "Perro");
        assertEquals(pacienteModificado.getRaza(), "Poodle");
        assertEquals(pacienteModificado.getEdad(), 10);
        assertEquals(pacienteModificado.getEstado(), "Enfermo");
    }

    @Test
    @Order(3)
    @Override
    public void noDebeModificarSiIdNoExiste() {
        IPacienteDAO pacienteDao = new PacienteDAOImpl();
        Paciente paciente = new Paciente();
        paciente.setId(this.idIncorrecto);
        paciente.setTutor(new TutorDAOImpl().buscar(this.testTutorId));
        paciente.setNombre("Fido");
        paciente.setEspecie("Perro");
        paciente.setRaza("Poodle");
        paciente.setEdad(10);
        paciente.setEstado("Enfermo");

        boolean modifico = pacienteDao.modificar(paciente);
        assertFalse(modifico);
    }

    @Test
    @Order(4)
    @Override
    public void noDebeEliminarSiIdNoExiste() {
        IPacienteDAO pacienteDao = new PacienteDAOImpl();
        boolean elimino = pacienteDao.eliminar(this.idIncorrecto);
        assertFalse(elimino);
    }

    @Test
    @Order(5)
    @Override
    public void debeEncontrarSiIdExiste() {
        IPacienteDAO pacienteDao = new PacienteDAOImpl();
        Paciente paciente = pacienteDao.buscar(this.testId);
        assertNotNull(paciente);
    }

    @Test
    @Order(6)
    @Override
    public void noDebeEncontrarSiIdNoExiste() {
        IPacienteDAO pacienteDao = new PacienteDAOImpl();
        Paciente paciente = pacienteDao.buscar(this.idIncorrecto);
        assertNull(paciente);
    }

    @Test
    @Order(7)
    @Override
    public void debeListar() {
        IPacienteDAO pacienteDao = new PacienteDAOImpl();
        List<Paciente> pacientes = pacienteDao.listar();

        assertNotNull(pacientes);
        assertFalse(pacientes.isEmpty());
    }

    @Test
    @Order(8)
    @Override
    public void debeEliminarSiIdExiste() {
        IPacienteDAO pacienteDao = new PacienteDAOImpl();
        boolean elimino = pacienteDao.eliminar(this.testId);
        assertTrue(elimino);
    }
}
