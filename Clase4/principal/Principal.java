package principal;
import matematicas.Operacion;

class Principal{
	public static void main(){	
		int a = 10;
		int b = 25;
		Operacion op = new Operacion();
		int suma =  op.sumar(a,b);
		int resta = op.restar(a,b);
		System.out.println("La suma de "+a+" y "+b+" es: "+suma);
		System.out.println("La resta de "+a+" y "+b+" es: "+resta);
		
	}
}