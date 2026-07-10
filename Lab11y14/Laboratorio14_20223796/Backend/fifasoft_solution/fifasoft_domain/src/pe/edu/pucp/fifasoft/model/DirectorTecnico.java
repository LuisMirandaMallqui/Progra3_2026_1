package pe.edu.pucp.fifasoft.model;

public class DirectorTecnico {

    private int idDirectorTecnico;
    private String nombre;
    private String nacionalidad;
    private int edad;

    public int getIdDirectorTecnico() {
        return idDirectorTecnico;
    }

    public void setIdDirectorTecnico(int idDirectorTecnico) {
        this.idDirectorTecnico = idDirectorTecnico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
