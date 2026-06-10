package pe.edu.pucp.testsoft.business.alumno.impl;

import pe.edu.pucp.testsoft.business.alumno.bo.IAlumnoBO;
import pe.edu.pucp.testsoft.model.Alumno;
import pe.edu.pucp.testsoft.persistance.alumno.DAO.AlumnoDAO;
import pe.edu.pucp.testsoft.persistance.alumno.Impl.AlumnoImpl;

import java.util.List;

public class AlumnoBOImpl implements IAlumnoBO {

    private final AlumnoDAO alumnoDAO = new AlumnoImpl();

    @Override
    public int insertar(Alumno alumno) throws Exception {
        validar(alumno, false);
        // Regla de negocio: no permitir códigos duplicados (SELECT INTO devuelve id>0 si ya existe).
        if (alumnoDAO.buscarPorCodigo(alumno.getCodigo()) > 0)
            throw new Exception("Ya existe un alumno con el código " + alumno.getCodigo() + ".");
        return alumnoDAO.insertar(alumno);
    }

    @Override
    public int modificar(Alumno alumno) throws Exception {
        validar(alumno, true);
        return alumnoDAO.modificar(alumno);
    }

    @Override
    public int eliminar(int id) throws Exception {
        if (id <= 0) throw new Exception("El id del alumno es inválido.");
        return alumnoDAO.eliminar(id);
    }

    @Override
    public Alumno buscarPorId(int id) throws Exception {
        if (id <= 0) throw new Exception("El id del alumno es inválido.");
        return alumnoDAO.buscarPorId(id);
    }

    @Override
    public List<Alumno> listarTodos() throws Exception {
        return alumnoDAO.listarTodos();
    }

    @Override
    public List<Alumno> buscarPorNombreApellido(String texto) throws Exception {
        if (texto == null) texto = "";
        return alumnoDAO.buscarPorNombreApellido(texto.trim());
    }

    // Validaciones de negocio centralizadas (el front NO es la única defensa).
    private void validar(Alumno a, boolean esModificacion) throws Exception {
        if (a == null) throw new Exception("El alumno no puede ser nulo.");
        if (esModificacion && a.getId() <= 0) throw new Exception("El id es obligatorio para modificar.");
        if (a.getCodigo() == null || a.getCodigo().isBlank()) throw new Exception("El código es obligatorio.");
        if (a.getNombre() == null || a.getNombre().isBlank()) throw new Exception("El nombre es obligatorio.");
        if (a.getApellidos() == null || a.getApellidos().isBlank()) throw new Exception("Los apellidos son obligatorios.");
        if (a.getCorreo() == null || !a.getCorreo().contains("@")) throw new Exception("El correo no es válido.");
        if (a.getEstado() == null || a.getEstado().isBlank()) a.setEstado("A"); // default activo
    }
}
