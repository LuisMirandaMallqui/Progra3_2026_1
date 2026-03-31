package academico.profesional;
import java.util.ArrayList;
import java.util.List;
import academico.*;
public class Docente{
	private String codigoPUCP;
	private String nombre;
	private String apellidoPaterno;
	private List<Curso> cursos;
	
	
	public Docente(String codigoPUCP, String nombre, String apellidoPaterno){
		this.codigoPUCP = codigoPUCP;
		this.nombre= nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.cursos = new ArrayList<>();
	}
	
	public Docente(Docente docente){
		this.codigoPUCP = docente.getCodigoPUCP();
		this.nombre = docente.getNombre();
		this.apellidoPaterno = docente.getApellidoPaterno();
	}
	public String getCodigoPUCP(){
		return codigoPUCP;
	}
	
	public void setCodigoPUCP(String codigoPUCP){
		this.codigoPUCP = codigoPUCP;
	}
	
	public String getNombre(){
		return nombre;
	}

	public void setNombre(String nombre){
		this.nombre = nombre;
	}

	public String getApellidoPaterno(){
		return nombre;
	}

	public void setApellidoPaterno(String apellidoPaterno){
		this.apellidoPaterno = apellidoPaterno;
	}
	
	public List<Curso>  getCurso(){
		return new ArrayList<>(cursos);
	}
	
	public void setCursos(List<Curso> cursos){
		this.cursos = new ArrayList<>(cursos);
	}
	
	public void agregarCursos(Curso curso){
	  curso = new Curso(curso);
	  this.cursos.add(curso);
	}
	
	public String devolverListaCursos(){
		String reporte = "";
		for(Curso curso : cursos){
			reporte += curso.getClave() + " - " + curso.getNombre()
			+ "\n";
		}
		return reporte;
	}
}
