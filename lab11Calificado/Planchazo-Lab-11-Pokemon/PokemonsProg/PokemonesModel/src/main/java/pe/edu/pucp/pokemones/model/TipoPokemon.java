package pe.edu.pucp.pokemones.model;

public class TipoPokemon {
    private int id_Tipo_Pokemon;
    private String nombre;

    public TipoPokemon(){

    }


    //Getters and setters
    public int getIdTipoPokemon() {
        return id_Tipo_Pokemon;
    }

    public void setIdTipoPokemon(int id_Tipo_Pokemon) {
        this.id_Tipo_Pokemon = id_Tipo_Pokemon;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
