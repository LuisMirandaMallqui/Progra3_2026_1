package pe.edu.pucp.pokesoft.model;
public class Pokemon {
    private int idPokemon;
    private String nombre;
    private double altura;
    private double peso;
    private String estadoEvolutivo;
    private String tipo;
    private String descripcion;
    private String imagenUrl;

    public Pokemon() {
    }

    public Pokemon(int idPokemon, String nombre, double altura, double peso, String estadoEvolutivo, String tipo, String descripcion, String imagenUrl) {
        this.idPokemon = idPokemon;
        this.nombre = nombre;
        this.altura = altura;
        this.peso = peso;
        this.estadoEvolutivo = estadoEvolutivo;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.imagenUrl = imagenUrl;
    }

    public Pokemon(String nombre, double altura, double peso, String estadoEvolutivo, String tipo, String descripcion, String imagenUrl) {
        this.nombre = nombre;
        this.altura = altura;
        this.peso = peso;
        this.estadoEvolutivo = estadoEvolutivo;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.imagenUrl = imagenUrl;
    }

    public int getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(int idPokemon) {
        this.idPokemon = idPokemon;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getEstadoEvolutivo() {
        return estadoEvolutivo;
    }

    public void setEstadoEvolutivo(String estadoEvolutivo) {
        this.estadoEvolutivo = estadoEvolutivo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}