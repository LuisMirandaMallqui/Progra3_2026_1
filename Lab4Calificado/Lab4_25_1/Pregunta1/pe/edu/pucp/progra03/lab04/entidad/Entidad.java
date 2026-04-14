package pe.edu.pucp.progra03.lab04.entidad;

import java.util.ArrayList;

public class Entidad{
	private String nombre;
	protected ArrayList<Columna> listaColumnas;
	private ArrayList<Fila> listaFilas;
	
	public Entidad(){
		 
	}

	public Entidad(String nombre,ArrayList<Columna> listaColumnas){
		this.nombre = nombre;
		this.listaColumnas = listaColumnas;
		this.listaFilas = new ArrayList<>();
	}
	
	//setters y getters necesarios
	public String getNombre(){
		return nombre;
	}
	
	//metodos
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		// Encabezado (nombres de columnas)
		for (int i = 0; i < listaColumnas.size(); i++) {
			sb.append(listaColumnas.get(i).getNombre());
			if (i < listaColumnas.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("\n");
		
		// Datos de las filas
		for (Fila fila : listaFilas) {
			for (int i = 0; i < listaColumnas.size(); i++) {
				sb.append(fila.obtenerDato(i));
				if (i < listaColumnas.size() - 1) {
					sb.append(",");
				}
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
}