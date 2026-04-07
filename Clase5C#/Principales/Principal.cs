namespace Principales;
using Academico;
using Matematicas;

class Principal{
	public static void Main(){	
		Console.WriteLine("Hello, World!");
		Estudiante est1 = new Estudiante("JUAN","PEREZ",66.4,55);
		Console.WriteLine(est1.DevolverDatos());
		Administrativo adm1 = new Administrativo("RAUL");
		//usamos las propiedades: setter y getter
		adm1.Nombre = "RAUL";
		Console.WriteLine("ADM: "+adm1.Nombre);
		Matematicas.Operacion op = new Matematicas.Operacion();
		int resultado = op.sumar(10,20);
		Console.WriteLine(resultado);
		Console.WriteLine(op.multiplicar(3,4));
	}
}
