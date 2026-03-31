package unidad;
import java.util.ArrayList;
import java.util.List;
import academico.*;
public class Especialidad{
	private String nombre;
	private ArrayList<Curso> cursos; 
	
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public ArrayList<Curso> getCursos(){
		return new ArrayList<>(cursos);
	}
	
	public void setCursos(List<Curso> cursos){
		this.cursos = new ArrayList<>(cursos);
	}
}