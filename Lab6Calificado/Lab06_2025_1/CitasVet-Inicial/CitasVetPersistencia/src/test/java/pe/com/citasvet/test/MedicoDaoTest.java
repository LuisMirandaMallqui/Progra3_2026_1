package pe.com.citasvet.test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import pe.com.citasvet.dao.IMedicoDAO;
import pe.com.citasvet.daoimpl.MedicoDAOImpl;
import pe.com.citasvet.modelo.Medico;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MedicoDaoTest implements ICrudDaoTest {
    private int testId;
    private final int idIncorrecto = 99999;
    
    @Test
    @Order(1)
    @Override
    public void debeInsertar() {
        IMedicoDAO medicoDao = new MedicoDAOImpl();
        Medico medico = new Medico();
        medico.setDni("40891234");
        medico.setNombre("Juan Rodriguez");
        medico.setEspecialidad("Veterinaria");
        
        this.testId = medicoDao.insertar(medico);
        assertTrue(this.testId > 0);
    }
    
    @Test
    @Order(2)
    @Override
    public void debeModificarSiIdExiste() {
        IMedicoDAO medicoDao = new MedicoDAOImpl();
        Medico medico = new Medico();
        medico.setId(this.testId);
        medico.setDni("40891236");
        medico.setNombre("Miguel Rodriguez");
        medico.setEspecialidad("Veterinaria Gastro");
        
        boolean modifico = medicoDao.modificar(medico);
        assertTrue(modifico);
        
        Medico medicoModificado = medicoDao.buscar(this.testId);
        assertEquals(medicoModificado.getDni(), "40891236");
        assertEquals(medicoModificado.getNombre(), "Miguel Rodriguez");
        assertEquals(medicoModificado.getEspecialidad(), "Veterinaria Gastro");
    }
    
    @Test
    @Order(3)
    @Override
    public void noDebeModificarSiIdNoExiste() {
        IMedicoDAO medicoDao = new MedicoDAOImpl();
        Medico medico = new Medico();
        medico.setId(this.idIncorrecto);
        medico.setDni("40891236");
        medico.setNombre("Miguel Rodriguez");
        medico.setEspecialidad("Veterinaria Gastro");
        
        boolean modifico = medicoDao.modificar(medico);
        assertFalse(modifico);
    }
    
    @Test
    @Order(4)
    @Override
    public void noDebeEliminarSiIdNoExiste() {
        IMedicoDAO medicoDao = new MedicoDAOImpl();
        boolean elimino = medicoDao.eliminar(this.idIncorrecto);
        assertFalse(elimino);
    }
    
    @Test
    @Order(5)
    @Override
    public void debeEncontrarSiIdExiste() {
        IMedicoDAO medicoDao = new MedicoDAOImpl();
        Medico medico = medicoDao.buscar(this.testId);
        assertNotNull(medico);
    }
    
    @Test
    @Order(6)
    @Override
    public void noDebeEncontrarSiIdNoExiste() {
        IMedicoDAO medicoDao = new MedicoDAOImpl();
        Medico medico = medicoDao.buscar(this.idIncorrecto);
        assertNull(medico);
    }
    
    @Test
    @Order(7)
    @Override
    public void debeListar() {
        IMedicoDAO medicoDao = new MedicoDAOImpl();
        List<Medico> medicos = medicoDao.listar();
        
        assertNotNull(medicos);
        assertFalse(medicos.isEmpty());
    }
    
    @Test
    @Order(8)
    @Override
    public void debeEliminarSiIdExiste() {
        IMedicoDAO medicoDao = new MedicoDAOImpl();
        boolean elimino = medicoDao.eliminar(this.testId);
        assertTrue(elimino);
    }
}
