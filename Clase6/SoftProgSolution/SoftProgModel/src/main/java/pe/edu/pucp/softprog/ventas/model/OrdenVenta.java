package pe.edu.pucp.softprog.ventas.model;
import pe.edu.pucp.softprog.gestclientes.model.Cliente;
import pe.edu.pucp.softprog.rrhh.model.Empleado;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdenVenta {
    private int idOrdenVenta;
    private Empleado empleado;
    private Cliente cliente;
    private List<LineaOrdenVenta> lineasOrdenVenta;
    private double total;
    private Date fechaHora;
    private boolean activo;

    public OrdenVenta(){
        this.lineasOrdenVenta = new ArrayList<>();
    }

    public int getIdOrdenVenta() {
        return idOrdenVenta;
    }

    public void setIdOrdenVenta(int idOrdenVenta) {
        this.idOrdenVenta = idOrdenVenta;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<LineaOrdenVenta> getLineasOrdenVenta() {
        return lineasOrdenVenta;
    }

    public void setLineasOrdenVenta(List<LineaOrdenVenta> lineasOrdenVenta) {
        this.lineasOrdenVenta = lineasOrdenVenta;
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

    public void calcularSutotalesYTotal(){
        total = 0;
        for(LineaOrdenVenta lov : lineasOrdenVenta) {
            lov.calcularSubtotal();
            total += lov.getSubtotal();
        }
    }

    public void agregarLineaOrdenVenta(LineaOrdenVenta lineaOrdenVenta){
        this.lineasOrdenVenta.add(lineaOrdenVenta);
    }
}