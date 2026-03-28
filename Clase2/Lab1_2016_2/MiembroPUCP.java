import java.util.Date;

abstract class MiembroPUCP extends Miembro implements IConsultable{
	private  String codigoPUCP;

	public MiembroPUCP(String nombre, Date fechaNacimiento, String direccion,
	String email, char sexo, String codigoPUCP){
		super(nombre, fechaNacimiento, direccion, email, sexo);
		this.codigoPUCP = codigoPUCP;
	}

	public abstract String consultarDatos();
	public String getCodigoPUCP(){
		return this.codigoPUCP;
	}
}