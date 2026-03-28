import java.util.ArrayList;
class Principal{
	public static void main(String[] args){
		//Profesor p1 = new Profesor();
		//IConsultable p2 = new Profesor();
		// de que sirve hacerlo asi?? 
		/* 
		si yo tengo Alumno que tmb implementa 
		por ejem:		
		IConsultable p2 = new Profesor();
		IConsultable a1 = new Alumno();
		//pueden ser tratados como iguales y meterse 
		//por ejem en un ArrayList
		ArrayList<IConsultable> lista  = new ArrayList<>();
		lista.add(p2);
		lista.add(a1);
		*/
		SimpleDateFormat sdf = new SImpleDateFormat();
		
		EQuipu objEquipu = new EQuipu();
		
		Equipo equipo1 = new Equipo("HCI-DUXAIT","Interes en Interaccion Humano Computador");
		//...
		// Se crean profesores y alumnos
		Alumno alum1 = new Alummno ("Juan Perez", sdf.parse("19-05-1988), "Av. Sucre 827", "jperez@pucp.edu.pe",
		"M", "20090606", 68.3);
		Profesor prof1 = new Profesor("Andrea Montenegro", sdf.parse("10-03-1992")
		
		MiembroExterno mExt = new MiembroExterno("Celeste Sipiran",sdf.parse("01-02-1984"),"Av. Bolivar 271"
		,"celeste@gmail.com",);
		//Asignamos los miembros del equipo 
		equipo1.agregarMiembro(alum1);
		equipo1.agregarMiembro(prof1);
		equipo1.agregarMiembro(alum2);
		//Agregamos el equipo a EQuipu
		objEquipu.agregarEquipo(equipo1);
		objEquipu.agregarEjecutivo(prof1);
		//Consultar 
		String reporte = objEquipu.consultarMiembrosDeEquipo(0);
		System.out.print(reporte)
	}
}