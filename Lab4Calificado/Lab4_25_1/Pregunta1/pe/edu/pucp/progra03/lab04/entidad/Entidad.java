package pe.edu.pucp.progra03.lab04.entidad;

import java.util.ArrayList;

public class Entidad{
	private String nombre;
	protected ArrayList<Columna> listaColumnas;
	private ArrayList<Fila> listaFilas;
	
	public void insertarFila(){
		Fila fila = new Fila();
		listaFilas.add(fila);
	}
	
	public void agregarEnteroEnFila(Integer entero){
		listaFilas.get(listaFilas.size()-1).insertarEntero(entero);
	}
	
	public void agregarCadenaEnFila(String cadena){
		listaFilas.get(listaFilas.size()-1).insertarCadena(cadena);
	}
}