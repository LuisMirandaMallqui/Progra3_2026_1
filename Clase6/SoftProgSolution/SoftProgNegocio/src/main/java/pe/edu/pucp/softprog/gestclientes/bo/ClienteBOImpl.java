package pe.edu.pucp.softprog.gestclientes.bo;

import pe.edu.pucp.softprog.gestclientes.boi.IClienteBO;
import pe.edu.pucp.softprog.gestclientes.dao.ClienteDAO;
import pe.edu.pucp.softprog.gestclientes.impl.ClienteImpl;
import pe.edu.pucp.softprog.gestclientes.model.Cliente;

import java.util.List;
import java.util.Date;

public class ClienteBOImpl implements IClienteBO {

    private ClienteDAO daoCliente;

    public ClienteBOImpl(){
        daoCliente = new ClienteImpl();
    }

    @Override
    public int insertar(Cliente cliente) throws Exception {
        validar(cliente, false);
        return daoCliente.insertar(cliente);
    }

    @Override
    public int modificar(Cliente cliente) throws Exception {
        validar(cliente, true);
        return daoCliente.modificar(cliente);
    }

    @Override
    public int eliminar(int id) throws Exception {
        return 0;
    }

    @Override
    public List<Cliente> listarTodos() throws Exception {
        return daoCliente.listarTodos();
    }

    @Override
    public Cliente buscarPorId(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("El id del cliente debe ser mayor que cero.");
        }
        return daoCliente.buscarPorId(id);
    }

    private void validar(Cliente cliente, boolean esModificacion) throws Exception {
        if (cliente == null) {
            throw new Exception("El cliente no puede ser nulo.");
        }

        if (esModificacion && cliente.getIdPersona() <= 0) {
            throw new Exception("El id del cliente es obligatorio para la modificación.");
        }

        validarDNI(cliente.getDNI());
        validarTextoObligatorio(cliente.getNombre(), "El nombre del cliente es obligatorio.");
        validarTextoObligatorio(cliente.getApellidoPaterno(), "El apellido paterno del cliente es obligatorio.");
        validarGenero(cliente.getGenero());
        validarFechaNacimiento(cliente.getFechaNacimiento());
        validarLineaCredito(cliente.getLineaCredito());
        validarCategoria(cliente);
    }

    private void validarDNI(String dni) throws Exception {
        if (dni == null || dni.trim().isEmpty()) {
            throw new Exception("El DNI es obligatorio.");
        }

        dni = dni.trim();

        if (!dni.matches("\\d{8}")) {
            throw new Exception("El DNI debe tener exactamente 8 dígitos numéricos.");
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

    private void validarLineaCredito(double lineaCredito) throws Exception {
        if (lineaCredito < 0) {
            throw new Exception("La línea de crédito no puede ser negativa.");
        }
    }

    private void validarCategoria(Cliente cliente) throws Exception {
        if (cliente.getCategoria() == null) {
            throw new Exception("La categoría del cliente es obligatoria.");
        }
    }
}
