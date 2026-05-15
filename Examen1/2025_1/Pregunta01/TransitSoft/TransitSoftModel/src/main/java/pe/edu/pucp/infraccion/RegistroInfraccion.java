package pe.edu.pucp.infraccion;

import pe.edu.pucp.conductor.Conductor;
import pe.edu.pucp.vehiculo.Vehiculo;

import java.util.Date;

public class RegistroInfraccion {
    private Date fecha;
    private Vehiculo vehiculo;
    private Conductor conductor;
    private Infraccion infraccion;

    public RegistroInfraccion() {
    }

    public RegistroInfraccion(Date fecha, Vehiculo vehiculo, Conductor conductor, Infraccion infraccion) {
        this.fecha = fecha;
        this.vehiculo = vehiculo;
        this.conductor = conductor;
        this.infraccion = infraccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public Infraccion getInfraccion() {
        return infraccion;
    }

    public void setInfraccion(Infraccion infraccion) {
        this.infraccion = infraccion;
    }
}
