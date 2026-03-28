import java.util.Date;

class Profesor extends MiembroPUCP implements IConsultable{
	private Estado estado;
	
	public Profesor(String nombre, Date fechaNacimiento, String direccion,
	String email, char sexo, String codigoPUCP, Estado estado){
		super(nombre, fechaNacimiento, direccion, email, sexo, codigoPUCP);
		this.estado = estado;
	}
	
	
	@Override
	public String consultarDatos(){
		return "Alumno: " + getCodigoPUCP() + " " + getNombre() + " -  " + estado + "\n";
	}
	
	
	
}