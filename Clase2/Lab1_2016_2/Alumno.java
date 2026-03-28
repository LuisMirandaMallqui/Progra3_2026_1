import java.util.Date;

class Alumno extends MiembroPUCP implements IConsultable{
	//retornar codigo de alumno, nombre y estado
	private double CRAEST;
	
	public Alumno(String nombre, Date fechaNacimiento, String direccion,
	String email, char sexo, String codigoPUCP, double CRAEST){
		super(nombre, fechaNacimiento, direccion, email, sexo, codigoPUCP);
		this.CRAEST = CRAEST;
	}
	
	@Override
	public String consultarDatos(){
		return "";
	}
	
	public String consultarDatos(){
		return "Alumno: " + getCodigoPUCP() + " " + getNombre() + " -  " + CRAEST + "\n";
	}
}