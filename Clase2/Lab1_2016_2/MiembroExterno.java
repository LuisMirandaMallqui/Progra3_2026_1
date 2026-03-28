class MiembroExterno extends Miembro{
	private char tipoDedicacion;
	public MiembroExterno(String nombre, Date fechaNacimiento, String direccion,
	String email, char sexo, char tipoDedicacion){
		super(nombre, fechaNacimiento, direccion, email, sexo);
		this.tipoDedicacion = tipoDedicacion;
	}
	
}