namespace Principales;
using Matematicas;
class Program{
	public static void Main(){
		int a = 10;
		int b = 25;
		Operacion op = new Operacion();
		int suma =  op.sumar(a,b);
		int resta = op.restar(a,b);
		System.Console.WriteLine("La suma de "+a+" y "+b+" es: "+suma);
		System.Console.WriteLine("La resta de "+a+" y "+b+" es: "+resta);
	}
}