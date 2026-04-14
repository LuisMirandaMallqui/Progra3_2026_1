package pe.edu.pucp.progra03.lab04.entidad;

public class Columna{
	private String nombre;
	private TipoDeDato tipoDeDato;
	
	public Columna(){
		
	}
	
	//setters y getters necesarios
	public String getNombre(){
		return nombre;
	}
	
	public Columna(String nombre, TipoDeDato tipoDeDato){
		this.nombre = nombre;
		this.tipoDeDato = tipoDeDato;
	}
	
}
