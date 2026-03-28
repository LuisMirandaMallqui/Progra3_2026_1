public class HolaMundo
{
	public static void main(String[] args){
		System.out.print("Hola mundo");
		//system es una clase, out un atributo de la clase, print un metodo
		Curso c1 = new Curso("progra3","inf29");
		String clave = c1.getClave();
		System.out.print("la clave es"+ clave);		
	}
}
