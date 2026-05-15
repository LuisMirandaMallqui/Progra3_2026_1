package pe.edu.pucp.vehiculo;

import pe.edu.pucp.conductor.Conductor;
import java.util.Date;

public class VehiculoConductor {
    private Vehiculo vehiculo;
    private Conductor conductor;
    private Date fechaAdquisicion;

    public VehiculoConductor() {
    }

    public VehiculoConductor(Vehiculo vehiculo, Conductor conductor, Date fechaAdquisicion) {
        this.vehiculo = vehiculo;
        this.conductor = conductor;
        this.fechaAdquisicion = fechaAdquisicion;
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

    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }
}
