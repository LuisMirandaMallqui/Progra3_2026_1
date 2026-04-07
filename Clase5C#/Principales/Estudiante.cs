namespace Academico;
class Estudiante{
	private String nombre;
	private String apellidoPaterno;
	private double craest;
	private double creditosMatriculados;
	//Forma actual:
	// public double CreditosMatriculados{get;set;}
	
	public Estudiante(String nombre, String apellidoPaterno, double craest, double creditosMatriculados){
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.craest = craest;
		this.creditosMatriculados = creditosMatriculados;
	}
	
	//getters y setters
	public String Nombre{
		get{
			return nombre;
		}
		set{
			this.nombre = value;
		}
	}
	public String ApellidoPaterno{
		get{
			return apellidoPaterno;
		}
		set{
			this.apellidoPaterno = value;
		}
	}
	public double Craest{
		get{
			return craest;
		}
		set{
			this.craest = value;
		}
	}
	public double CreditosMatriculados{
		get{
			return creditosMatriculados;
		}
		set{
			this.creditosMatriculados = value;
		}
	}
	
	//metodos
	public String DevolverDatos(){
		return "EST: "+nombre+" "+apellidoPaterno+" - "+craest;
	}
}