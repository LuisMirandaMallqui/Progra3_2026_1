package pe.com.citasvet.test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;

import pe.com.citasvet.daoimpl.TutorDAOImpl;
import pe.com.citasvet.dao.ITutorDAO;
import pe.com.citasvet.modelo.Tutor;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TutorDaoTest implements ICrudDaoTest {
    private int testId;
    private final int idIncorrecto = 99999;
    
    @Test
    @Order(1)
    @Override
    public void debeInsertar() {
        ITutorDAO areaDao = new TutorDAOImpl();
        Tutor tutor = new Tutor();
        tutor.setDni("45678923");
        tutor.setNombre("Tutor de prueba");
        tutor.setDireccion("Direccion de prueba");
        tutor.setTelefono("111-111-111");
        
        this.testId = areaDao.insertar(tutor);
        assertTrue(this.testId > 0);
    }
    
    @Test
    @Order(2)
    @Override
    public void debeModificarSiIdExiste() {
        ITutorDAO tutorDao = new TutorDAOImpl();
        Tutor tutor = new Tutor();
        tutor.setId(this.testId);
        tutor.setDni("45678923");
        tutor.setNombre("Tutor de prueba modificado");
        tutor.setDireccion("Direccion de prueba modificada");
        tutor.setTelefono("222-222-222");
        
        boolean modifico = tutorDao.modificar(tutor);
        assertTrue(modifico);
        
        Tutor turorModificado = tutorDao.buscar(this.testId);
        assertEquals(turorModificado.getNombre(), "Tutor de prueba modificado");
        assertEquals(turorModificado.getDireccion(), "Direccion de prueba modificada");
        assertEquals(turorModificado.getTelefono(), "222-222-222");
    }
    
    @Test
    @Order(3)
    @Override
    public void noDebeModificarSiIdNoExiste() {
        ITutorDAO tutorDao = new TutorDAOImpl();
        Tutor tutor = new Tutor();
        tutor.setId(this.idIncorrecto);
        tutor.setDni("45678923");
        tutor.setNombre("Tutor de prueba modificado");
        tutor.setDireccion("Direccion de prueba modificada");
        tutor.setTelefono("222-222-222");
        
        boolean modifico = tutorDao.modificar(tutor);
        assertFalse(modifico);
    }
    
    @Test
    @Order(4)
    @Override
    public void noDebeEliminarSiIdNoExiste() {
        ITutorDAO tutorDao = new TutorDAOImpl();
        boolean elimino = tutorDao.eliminar(this.idIncorrecto);
        assertFalse(elimino);
    }
    
    @Test
    @Order(5)
    @Override
    public void debeEncontrarSiIdExiste() {
        ITutorDAO tutorDao = new TutorDAOImpl();
        Tutor tutor = tutorDao.buscar(this.testId);
        assertNotNull(tutor);
    }
    
    @Test
    @Order(6)
    @Override
    public void noDebeEncontrarSiIdNoExiste() {
        ITutorDAO tutorDao = new TutorDAOImpl();
        Tutor tutor = tutorDao.buscar(this.idIncorrecto);
        assertNull(tutor);
    }
    
    @Test
    @Order(7)
    @Override
    public void debeListar() {
        ITutorDAO tutorDao = new TutorDAOImpl();
        List<Tutor> tutores = tutorDao.listar();
        
        assertNotNull(tutores);
        assertFalse(tutores.isEmpty());
    }
    
    @Test
    @Order(8)
    @Override
    public void debeEliminarSiIdExiste() {
        ITutorDAO tutorDao = new TutorDAOImpl();
        boolean elimino = tutorDao.eliminar(this.testId);
        assertTrue(elimino);
    }
}
