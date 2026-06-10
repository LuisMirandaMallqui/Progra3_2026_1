package pe.edu.pucp.pokemones.model;

import pe.edu.pucp.pokemones.model.enums.EstadoEvolutivo;

public class Pokemon {
    private int id_Pokemon;
    private int id_tipo_Pokemon;
    private String string_tipo_Pokemon;
    private String nombre;
    private double altura;
    private double peso;
    private EstadoEvolutivo estado_evolutivo;
    private String descripcion;

    public Pokemon(){

    }

    //Getters and setters
    public int getIdPokemon() {
        return id_Pokemon;
    }

    public void setIdPokemon(int id_Pokemon) {
        this.id_Pokemon = id_Pokemon;
    }

    public int getIdTipoPokemon() {
        return id_tipo_Pokemon;
    }

    public void setIdTipoPokemon(int id_tipo_Pokemon) {
        this.id_tipo_Pokemon = id_tipo_Pokemon;
    }

    public String getStringTipoPokemon() {
        return string_tipo_Pokemon;
    }

    public void setStringTipoPokemon(String string_tipo_Pokemon) {
        this.string_tipo_Pokemon = string_tipo_Pokemon;
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

    public EstadoEvolutivo getEstadoEvolutivo() {
        return estado_evolutivo;
    }

    public void setEstadoEvolutivo(EstadoEvolutivo estado_evolutivo) {
        this.estado_evolutivo = estado_evolutivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



}
