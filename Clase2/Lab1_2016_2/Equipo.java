import java.util.List;
import java.util.ArrayList;

class Equipo{
	private String nombre;
	private String interes;
	private List<Miembro> miembros;
	
	public Equipo(String nombre, String interes){
		this.nombre = nombre;
		this.interes = interes;
		this.miembros = new ArrayList<>();
	}
	
	public void agregarMiembro(Miembro m){
		this.miembros.add(m);
	}
	
	public String consultarDatosAlumnosProfesoresPUCP(){
		String reporte = "";
		for(Miembro m: miembros){
			if(m instanceof MiembroPUCP){
				reporte += ((MiembroPUCP)m).consultarDatos();
			}
		}
		return reporte;
	}
}