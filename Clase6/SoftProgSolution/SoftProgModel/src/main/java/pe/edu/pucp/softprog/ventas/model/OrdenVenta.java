package pe.edu.pucp.softprog.ventas.model;
import pe.edu.pucp.softprog.almacen.model.Producto;

import java.util.Date;
import java.util.List;

public class OrdenVenta {
    private int idOrdenVenta;
    private double total;
    private Date fechaHora;
    private List<Producto> productos
    private boolean activo;

    public int getIdOrdenVenta() {
        return idOrdenVenta;
    }

    public void setIdOrdenVenta(int idOrdenVenta) {
        this.idOrdenVenta = idOrdenVenta;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
