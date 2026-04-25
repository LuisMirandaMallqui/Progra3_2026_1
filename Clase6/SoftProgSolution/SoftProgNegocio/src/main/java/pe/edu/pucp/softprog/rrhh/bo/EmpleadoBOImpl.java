package pe.edu.pucp.softprog.rrhh.bo;

import pe.edu.pucp.softprog.rrhh.boi.IEmpleadoBO;
import pe.edu.pucp.softprog.rrhh.dao.EmpleadoDAO;
import pe.edu.pucp.softprog.rrhh.impl.EmpleadoImpl;
import pe.edu.pucp.softprog.rrhh.model.Empleado;

import java.util.List;
import java.util.Date;

public class EmpleadoBOImpl implements IEmpleadoBO {

    private EmpleadoDAO daoEmpleado;

    public EmpleadoBOImpl(){
        daoEmpleado = new EmpleadoImpl();
    }

    @Override
    public int insertar(Empleado empleado) throws Exception {
        validar(empleado, false);
        return daoEmpleado.insertar(empleado);
    }

    @Override
    public int modificar(Empleado empleado) throws Exception {
        validar(empleado, true);
        return daoEmpleado.modificar(empleado);
    }

    @Override
    public int eliminar(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("El id del empleado debe ser mayor que cero.");
        }
        return daoEmpleado.eliminar(id);
    }

    @Override
    public List<Empleado> listarTodos() throws Exception {
        return daoEmpleado.listarTodos();
    }

    @Override
    public Empleado buscarPorId(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("El id del empleado debe ser mayor que cero.");
        }
        return daoEmpleado.buscarPorId(id);
    }

    private void validar(Empleado empleado, boolean esModificacion) throws Exception {
        if (empleado == null) {
            throw new Exception("El empleado no puede ser nulo.");
        }

        if (esModificacion && empleado.getIdPersona() <= 0) {
            throw new Exception("El id del empleado es obligatorio para la modificación.");
        }

        validarDNI(empleado.getDNI());
        validarTextoObligatorio(empleado.getNombre(), "El nombre es obligatorio.");
        validarTextoObligatorio(empleado.getApellidoPaterno(), "El apellido paterno es obligatorio.");
        validarGenero(empleado.getGenero());
        validarFechaNacimiento(empleado.getFechaNacimiento());
        validarArea(empleado);
        validarTextoObligatorio(empleado.getCargo(), "El cargo es obligatorio.");
        validarSueldo(empleado.getSueldo());
    }

    private void validarDNI(String DNI) throws Exception {
        if (DNI == null || DNI.trim().isEmpty()) {
            throw new Exception("El DNI es obligatorio.");
        }

        DNI = DNI.trim();

        if (DNI.length() != 8) {
            throw new Exception("El DNI debe tener exactamente 8 caracteres.");
        }

        if (!DNI.matches("\\d{8}")) {
            throw new Exception("El DNI debe contener solo números.");
        }
    }

    private void validarTextoObligatorio(String texto, String mensaje) throws Exception {
        if (texto == null || texto.trim().isEmpty()) {
            throw new Exception(mensaje);
        }
    }

    private void validarGenero(char genero) throws Exception {
        if (genero != 'M' && genero != 'F') {
            throw new Exception("El género debe ser 'M' o 'F'.");
        }
    }

    private void validarFechaNacimiento(Date fechaNacimiento) throws Exception {
        if (fechaNacimiento == null) {
            throw new Exception("La fecha de nacimiento es obligatoria.");
        }

        if (fechaNacimiento.after(new Date())) {
            throw new Exception("La fecha de nacimiento no puede ser futura.");
        }
    }

    private void validarArea(Empleado empleado) throws Exception {
        if (empleado.getArea() == null) {
            throw new Exception("El área es obligatoria.");
        }

        if (empleado.getArea().getIdArea() <= 0) {
            throw new Exception("El área seleccionada no es válida.");
        }
    }

    private void validarSueldo(double sueldo) throws Exception {
        if (sueldo <= 0) {
            throw new Exception("El sueldo debe ser mayor que cero.");
        }
    }
}
