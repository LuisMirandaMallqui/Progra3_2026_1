package academico;
import academico.profesional.Docente;
public class Curso{
	private String clave;
	private String nombre;
	private Docente docente;
	//private academico.profesional.Docente docente;
	
	
	public Curso(String clave, String nombre){
		this.clave = clave;
		this.nombre = nombre; 
	} 
	
	public Curso(Curso curso){
		this.clave = curso.getClave();
		this.nombre = curso.getNombre(); 
	} 
	
	public String getClave(){
		return clave;
	}
	public void setClave(String clave){
		this.clave = clave;
	}
	public String getNombre(){
		return nombre;
	}
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public Docente getDocente(){
		return docente;
	}
	
	public void setDocente(Docente docente){
		this.docente = new Docente(docente);
	}
	

}