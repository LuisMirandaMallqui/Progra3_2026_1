class Curso{
	//Atributos
	private String clave;
	private String nombre;
	//Constructos
	public  Curso(String nombre , String clave){
		this.clave = clave;
		this.nombre= nombre;
	}
	
	//Stters y Getters
	public String getClave(){
		return clave;
	}
	public void setClave(String clave){
		this.clave = clave;
	}
}
