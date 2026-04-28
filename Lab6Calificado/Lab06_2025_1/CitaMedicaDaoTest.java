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
import pe.com.citasvet.dao.IMedicoDAO;
import pe.com.citasvet.daoimpl.PacienteDAOImpl;
import pe.com.citasvet.dao.ICitaMedicaDAO;
import pe.com.citasvet.dao.IPacienteDAO;
import pe.com.citasvet.dao.ITutorDAO;
import pe.com.citasvet.daoimpl.CitaMedicaDAOImpl;
import pe.com.citasvet.daoimpl.MedicoDAOImpl;
import pe.com.citasvet.daoimpl.TutorDAOImpl;
import pe.com.citasvet.modelo.Paciente;
import pe.com.citasvet.modelo.Medico;
import pe.com.citasvet.modelo.CitaMedica;
import pe.com.citasvet.modelo.Tutor;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CitaMedicaDaoTest implements ICrudDaoTest {
    private int testId;
    private int testPacienteId;
    private int testMedicoId;
    private int testTutorId;
    private final int idIncorrecto = 99999;
    
    @BeforeAll
    public void inicializar() {
        ITutorDAO tutorDao = new TutorDAOImpl();
        Tutor tutor = new Tutor();
        tutor.setDni("56783490");
        tutor.setNombre("Tutor de prueba");
        tutor.setTelefono("111-111-111");
        tutor.setDireccion("Direccion de prueba");
        this.testTutorId = tutorDao.insertar(tutor);
        
        IPacienteDAO pacienteDao = new PacienteDAOImpl();
        Paciente paciente = new Paciente();
        paciente.setTutor(new TutorDAOImpl().buscar(this.testTutorId));
        paciente.setNombre("Rocky");
        paciente.setEspecie("Gato");
        paciente.setRaza("Angora");
        paciente.setEstado("Enfermo");
        paciente.setEdad(10);
        this.testPacienteId = pacienteDao.insertar(paciente);
        
        IMedicoDAO medicoDao = new MedicoDAOImpl();
        Medico medico = new Medico();
        medico.setDni("45896745");
        medico.setNombre("Pedro Gutierrez");
        medico.setEspecialidad("Gastro");
        this.testMedicoId = medicoDao.insertar(medico);
    }
    
    @AfterAll
    public void limpiar() {
        IPacienteDAO pacienteDao = new PacienteDAOImpl();
        pacienteDao.eliminar(this.testPacienteId);
        
        IMedicoDAO medicoDao = new MedicoDAOImpl();
        medicoDao.eliminar(this.testMedicoId);
        
        ITutorDAO tutorDao = new TutorDAOImpl();
        tutorDao.eliminar(this.testTutorId);
    }
    
    @Test
    @Order(1)
    @Override
    public void debeInsertar() {
        ICitaMedicaDAO citaMedicaDao = new CitaMedicaDAOImpl();
        CitaMedica citaMedica  = new CitaMedica();
        citaMedica.setPaciente(new PacienteDAOImpl().buscar(this.testPacienteId));
        citaMedica.setMedico(new MedicoDAOImpl().buscar(this.testMedicoId));
        citaMedica.setMotivo("Motivo de prueba");
        try {
            citaMedica.setFecha(new SimpleDateFormat("dd-MM-yyy").parse("02-05-2025"));
        }
        catch (ParseException e) {
            System.err.println("No se puede inicializar la fecha" + e.getMessage());
        }
        
        this.testId = citaMedicaDao.insertar(citaMedica);
        assertTrue(this.testId > 0);
    }
    
    @Test
    @Order(2)
    @Override
    public void debeModificarSiIdExiste() {
        ICitaMedicaDAO citaMedicaDao = new CitaMedicaDAOImpl();
        CitaMedica citaMedica = new CitaMedica();
        citaMedica.setId(this.testId);
        citaMedica.setPaciente(new PacienteDAOImpl().buscar(this.testPacienteId));
        citaMedica.setMedico(new MedicoDAOImpl().buscar(this.testMedicoId));
        citaMedica.setMotivo("Motivo de prueba actualizado");
        try {
            citaMedica.setFecha(new SimpleDateFormat("dd-MM-yyy").parse("08-05-2025"));
        }
        catch (ParseException e) {
            System.err.println("No se puede inicializar la fecha" + e.getMessage());
        }

        boolean modifico = citaMedicaDao.modificar(citaMedica);
        assertTrue(modifico);

        CitaMedica citaMedicaModificada = citaMedicaDao.buscar(this.testId);
        assertEquals(citaMedicaModificada.getMotivo(), "Motivo de prueba actualizado");
        try {
            assertEquals(citaMedicaModificada.getFecha(), new SimpleDateFormat("dd-MM-yyyy").parse("08-05-2025"));
        }
        catch (ParseException e) {
            System.err.println("No se puede inicializar la fecha" + e.getMessage());
        }
    }
    
    @Test
    @Order(3)
    @Override
    public void noDebeModificarSiIdNoExiste() {
        ICitaMedicaDAO citaMedicaDao = new CitaMedicaDAOImpl();
        CitaMedica citaMedica = new CitaMedica();
        citaMedica.setId(this.idIncorrecto);
        citaMedica.setPaciente(new PacienteDAOImpl().buscar(this.testPacienteId));
        citaMedica.setMedico(new MedicoDAOImpl().buscar(this.testMedicoId));
        citaMedica.setMotivo("Motivo de prueba actualizado");
        try {
            citaMedica.setFecha(new SimpleDateFormat("dd-MM-yyy").parse("08-05-2025"));
        }
        catch (ParseException e) {
            System.err.println("No se puede inicializar la fecha" + e.getMessage());
        }

        boolean modifico = citaMedicaDao.modificar(citaMedica);
        assertFalse(modifico);
    }
    
    @Test
    @Order(4)
    @Override
    public void noDebeEliminarSiIdNoExiste() {
        ICitaMedicaDAO citaMedicaDao = new CitaMedicaDAOImpl();
        boolean elimino = citaMedicaDao.eliminar(this.idIncorrecto);
        assertFalse(elimino);
    }
    
    @Test
    @Order(5)
    @Override
    public void debeEncontrarSiIdExiste() {
        ICitaMedicaDAO citaMedicaDao = new CitaMedicaDAOImpl();
        CitaMedica citaMedica = citaMedicaDao.buscar(this.testId);
        assertNotNull(citaMedica);
    }
    
    @Test
    @Order(6)
    @Override
    public void noDebeEncontrarSiIdNoExiste() {
        ICitaMedicaDAO citaMedicaDao = new CitaMedicaDAOImpl();
        CitaMedica citaMedica = citaMedicaDao.buscar(this.idIncorrecto);
        assertNull(citaMedica);
    }
    
    @Test
    @Order(7)
    @Override
    public void debeListar() {
        ICitaMedicaDAO citaMedicaDao = new CitaMedicaDAOImpl();
        List<CitaMedica> citasMedicas = citaMedicaDao.listar();
        
        assertNotNull(citasMedicas);
        assertFalse(citasMedicas.isEmpty());
    }
    
    @Test
    @Order(8)
    @Override
    public void debeEliminarSiIdExiste() {
        ICitaMedicaDAO citaMedicaDao = new CitaMedicaDAOImpl();
        boolean elimino = citaMedicaDao.eliminar(this.testId);
        assertTrue(elimino);
    }
}
