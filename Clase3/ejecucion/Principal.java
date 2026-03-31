package ejecucion;
//import academico.Curso;
//import academico.Docente;
import academico.*;
import academico.profesional.*;
public class Principal{
	public static void main(String[] args){
		Curso curso1 = new Curso("1INF30","PROGRAMACIÓN 3");
		Docente docente1 = new Docente("201213","Rony","Cueva");
		
		curso1.setDocente(docente1);
		docente1.agregarCursos(curso1);
		//navegaos del curso al docente asociado e imprimimos su nombre 
		System.out.println(curso1.getDocente().getNombre());
		//navegamos del docente a los cursos asociados e imprimimos el nombre
		//del primer curso asignado 
		System.out.println(docente1.getCurso().get(0));
		docente1.setNombre("Javier");
		System.out.println(curso1.getDocente().getNombre());
		//Ejemplo
		Curso curso = new Curso("1INF20","Sistemas 1");
		docente1.agregarCursos(curso);
		curso.setClave("1INF40");
		curso.setNombre("Algoritmos avanzados");
		docente1.agregarCursos(curso);
		curso.setClave("1INF117");
		curso.setNombre("Fundamentos de sitemas");
		docente1.agregarCursos(curso);
		// Imprimimos los cursos del docente 
		System.out.println(docente1.devolverListaCursos());
	}
}