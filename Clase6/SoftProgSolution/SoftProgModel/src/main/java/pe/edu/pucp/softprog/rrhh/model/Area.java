package pe.edu.pucp.softprog.rrhh.model;
public class Area {
    private int idArea;
    private String nombre;
    private boolean activa;

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    @Override
    public String toString() {
       return "AREA: " + idArea + ". " + nombre;
    }
}
