public class Postulante{
	//Atributos
	private String paterno;
	private String materno;
	private String nombre;
	private String dni;
	
	//Propiedades
	public void setPaterno(String paterno){
		this.paterno = paterno;
	}
	public String getPaterno(){
		return paterno;
	}
	public void setMaterno(String materno){
		this.materno = materno;
	}
	public String getMaterno(){
		return materno;
	}
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	public String getNombre(){
		return nombre;
	}
	public void setDni(String dni){
		this.dni = dni;
	}
	public String getDni(){
		return dni;
	}
	
	
	//Constructor con parametros
	public Postulante(String paterno,String materno,
						String nombre,String dni){
		this.paterno = paterno;
		this.materno = materno;
		this.nombre = nombre;
		this.dni = dni;
	}
	
	//Constructor sin parametro
	public Postulante(){
			
	}
	
	//Constructor copia 
	public Postulante(Postulante postulante){
		this.paterno = postulante.getPaterno();
		this.materno = postulante.getMaterno();
		this.nombre = postulante.getNombre();
		this.dni = postulante.getDni();
	}
	
	@Override 
	public String toString(){
		return paterno + " " + materno + ", " + nombre + "(" + dni + ")"  ;
	}
}