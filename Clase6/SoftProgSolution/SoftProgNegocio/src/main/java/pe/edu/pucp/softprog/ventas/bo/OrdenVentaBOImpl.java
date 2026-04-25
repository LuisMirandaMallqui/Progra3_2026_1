package pe.edu.pucp.softprog.ventas.bo;

import pe.edu.pucp.softprog.ventas.boi.IOrdenVentaBO;
import pe.edu.pucp.softprog.ventas.dao.OrdenVentaDAO;
import pe.edu.pucp.softprog.ventas.impl.OrdenVentaImpl;
import pe.edu.pucp.softprog.ventas.model.LineaOrdenVenta;
import pe.edu.pucp.softprog.ventas.model.OrdenVenta;

import java.util.List;
import java.util.Date;

public class OrdenVentaBOImpl implements IOrdenVentaBO {

    private final OrdenVentaDAO daoOrdenVenta;

    public OrdenVentaBOImpl(){
        daoOrdenVenta = new OrdenVentaImpl();
    }

    @Override
    public int insertar(OrdenVenta ordenVenta) throws Exception {
        validar(ordenVenta, false);
        //recalcularTotales(ordenVenta);
        return daoOrdenVenta.insertar(ordenVenta);
    }

    @Override
    public int modificar(OrdenVenta ordenVenta) throws Exception {
        validar(ordenVenta, true);
        //recalcularTotales(ordenVenta);
        return daoOrdenVenta.modificar(ordenVenta);
    }

    @Override
    public int eliminar(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("El id de la orden de venta debe ser mayor que cero.");
        }
        return daoOrdenVenta.eliminar(id);
    }

    @Override
    public List<OrdenVenta> listarTodos() throws Exception {
        return daoOrdenVenta.listarTodos();
    }

    @Override
    public OrdenVenta buscarPorId(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("El id de la orden de venta debe ser mayor que cero.");
        }
        return daoOrdenVenta.buscarPorId(id);
    }

    private void validar(OrdenVenta ordenVenta, boolean esModificacion) throws Exception {
        if (ordenVenta == null) {
            throw new Exception("La orden de venta no puede ser nula.");
        }

        if (esModificacion && ordenVenta.getIdOrdenVenta() <= 0) {
            throw new Exception("El id de la orden de venta es obligatorio para la modificación.");
        }

        validarCliente(ordenVenta);
        validarEmpleado(ordenVenta);
        validarFecha(ordenVenta);
        validarLineas(ordenVenta);
    }

    private void validarCliente(OrdenVenta ordenVenta) throws Exception {
        if (ordenVenta.getCliente() == null) {
            throw new Exception("La orden de venta debe tener un cliente.");
        }

        if (ordenVenta.getCliente().getIdPersona() <= 0) {
            throw new Exception("El cliente de la orden de venta no es válido.");
        }
    }

    private void validarEmpleado(OrdenVenta ordenVenta) throws Exception {
        if (ordenVenta.getEmpleado() == null) {
            throw new Exception("La orden de venta debe tener un empleado.");
        }

        if (ordenVenta.getEmpleado().getIdPersona() <= 0) {
            throw new Exception("El empleado de la orden de venta no es válido.");
        }
    }

    private void validarFecha(OrdenVenta ordenVenta) throws Exception {
        if (ordenVenta.getFechaHora() == null) {
            throw new Exception("La fecha de la orden de venta es obligatoria.");
        }

        if (ordenVenta.getFechaHora().after(new Date())) {
            throw new Exception("La fecha de la orden de venta no puede ser futura.");
        }
    }

    private void validarLineas(OrdenVenta ordenVenta) throws Exception {
        if (ordenVenta.getLineasOrdenVenta() == null || ordenVenta.getLineasOrdenVenta().isEmpty()) {
            throw new Exception("La orden de venta debe tener al menos una línea de detalle.");
        }

        for (LineaOrdenVenta linea : ordenVenta.getLineasOrdenVenta()) {
            validarLinea(linea);
        }
    }

    private void validarLinea(LineaOrdenVenta linea) throws Exception {
        if (linea == null) {
            throw new Exception("Existe una línea de venta nula.");
        }

        if (linea.getProducto() == null) {
            throw new Exception("Toda línea de venta debe tener un producto.");
        }

        if (linea.getProducto().getIdProducto() <= 0) {
            throw new Exception("El producto de una línea de venta no es válido.");
        }

        if (!linea.getProducto().isActivo()) {
            throw new Exception("El producto de una línea de venta no se encuentra activo.");
        }

        if (linea.getCantidadUnidades() <= 0) {
            throw new Exception("La cantidad de unidades debe ser mayor que cero.");
        }

        if (linea.getProducto().getPrecio() <= 0) {
            throw new Exception("El precio del producto debe ser mayor que cero.");
        }
    }

//    private void recalcularTotales(OrdenVenta ordenVenta) {
//        double total = 0;
//
//        for (LineaOrdenVenta linea : ordenVenta.getLineasOrdenVenta()) {
//            linea.calcularSubtotal();
//            total += linea.getSubtotal();
//        }
//
//        ordenVenta.setTotal(total);
//    }
}
