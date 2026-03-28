import java.util.Date;

abstract class Miembro{
	private static int correlativo = 1;
	private int codigo;
	private String nombre;
	private Date fechaNacimiento;
	private String direccion;
	private String email;
	private char sexo;
	
	public Miembro(String nombre, Date fechaNacimiento, String direccion,
	String email, char sexo){
		this.codigo = correlativo;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.email = email;
		this.sexo = sexo;
		correlativo ++;
	}
	
	public String getNmbre(){
		return nombre;
	}
}