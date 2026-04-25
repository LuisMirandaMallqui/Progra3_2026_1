package pe.edu.pucp.softprog.rrhh.bo;

import pe.edu.pucp.softprog.rrhh.boi.IAreaBO;
import pe.edu.pucp.softprog.rrhh.dao.AreaDAO;
import pe.edu.pucp.softprog.rrhh.impl.AreaImpl;
import pe.edu.pucp.softprog.rrhh.model.Area;

import java.util.List;

public class AreaBOImpl implements IAreaBO{

    private final AreaDAO daoArea;

    public AreaBOImpl(){
        daoArea = new AreaImpl();
    }

    @Override
    public int insertar(Area area) throws Exception {
        validar(area, false);
        return daoArea.insertar(area);
    }

    @Override
    public int modificar(Area area) throws Exception {
        validar(area, true);
        return daoArea.modificar(area);
    }

    @Override
    public int eliminar(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("El id del area debe ser mayor que cero.");
        }
        return daoArea.eliminar(id);
    }

    @Override
    public List<Area> listarTodos() throws Exception {
        return daoArea.listarTodos();
    }

    @Override
    public Area buscarPorId(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("El id del area debe ser mayor que cero.");
        }
        return daoArea.buscarPorId(id);
    }

    private void validar(Area area, boolean esModificacion) throws Exception {
        if (area == null) {
            throw new Exception("El área no puede ser nula.");
        }

        if (esModificacion && area.getIdArea() <= 0) {
            throw new Exception("El id del área es obligatorio para la modificación.");
        }

        validarNombre(area.getNombre());
    }

    private void validarNombre(String nombre) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre del área es obligatorio.");
        }

        nombre = nombre.trim();

        if (nombre.length() < 3) {
            throw new Exception("El nombre del área debe tener al menos 3 caracteres.");
        }

        if (nombre.length() > 30) {
            throw new Exception("El nombre del área no puede exceder los 75 caracteres.");
        }
    }
}
