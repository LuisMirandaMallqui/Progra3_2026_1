package pe.edu.pucp.softprog.almacen.bo;

import pe.edu.pucp.softprog.almacen.boi.IProductoBO;
import pe.edu.pucp.softprog.almacen.dao.ProductoDAO;
import pe.edu.pucp.softprog.almacen.impl.ProductoImpl;
import pe.edu.pucp.softprog.almacen.model.Producto;

import java.util.List;

public class ProductoBOImpl implements IProductoBO {

    private ProductoDAO daoProducto;

    public ProductoBOImpl(){
        daoProducto = new ProductoImpl();
    }

    @Override
    public int insertar(Producto producto) throws Exception {
        validar(producto, false);
        return daoProducto.insertar(producto);
    }

    @Override
    public int modificar(Producto producto) throws Exception {
        validar(producto, false);
        return daoProducto.modificar(producto);
    }

    @Override
    public int eliminar(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("El id del producto debe ser mayor que cero.");
        }
        return daoProducto.eliminar(id);
    }

    @Override
    public List<Producto> listarTodos() throws Exception {
        return daoProducto.listarTodos();
    }

    @Override
    public Producto buscarPorId(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("El id del producto debe ser mayor que cero.");
        }
        return daoProducto.buscarPorId(id);
    }

    private void validar(Producto producto, boolean esModificacion) throws Exception {
        if (producto == null) {
            throw new Exception("El producto no puede ser nulo.");
        }

        if (esModificacion && producto.getIdProducto() <= 0) {
            throw new Exception("El id del producto es obligatorio para la modificación.");
        }

        validarNombre(producto.getNombre());
        validarUnidadMedida(producto.getUnidadMedida());
        validarPrecio(producto.getPrecio());
    }

    private void validarNombre(String nombre) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre del producto es obligatorio.");
        }

        nombre = nombre.trim();

        if (nombre.length() < 2) {
            throw new Exception("El nombre del producto debe tener al menos 2 caracteres.");
        }

        if (nombre.length() > 100) {
            throw new Exception("El nombre del producto no puede exceder los 100 caracteres.");
        }
    }

    private void validarUnidadMedida(String unidadMedida) throws Exception {
        if (unidadMedida == null || unidadMedida.trim().isEmpty()) {
            throw new Exception("La unidad de medida del producto es obligatoria.");
        }

        unidadMedida = unidadMedida.trim();

        if (unidadMedida.length() < 1) {
            throw new Exception("La unidad de medida del producto no es válida.");
        }

        if (unidadMedida.length() > 20) {
            throw new Exception("La unidad de medida del producto no puede exceder los 20 caracteres.");
        }
    }

    private void validarPrecio(double precio) throws Exception {
        if (precio <= 0) {
            throw new Exception("El precio del producto debe ser mayor que cero.");
        }
    }
}
