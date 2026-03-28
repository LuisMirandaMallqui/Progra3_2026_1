import java.util.List;
import java.util.ArrayList;

class EQuipu{
	private List<Equipo> equipos;
	private List<Miembro> ejecutivos;

	public void EQuipu(){
		this.equipos = new ArrayList<>();
		this.ejecutivos = new ArrayList<>();
	}
	
	public void agregarMiembro(Equipo equipo){
		this.equipos.add(equipo);
	}
	
	public String consultarMiembrosDeEquipo(int indice){
		equipos.get(indice).consultarDatosAlumnosProfesoresPUCP();
	}
	
	public void agregarEjecutivo(Miembro ejecutivo){
		this.ejecutivos.add(ejecutivo);
	}
}