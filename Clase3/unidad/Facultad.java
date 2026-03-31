package unidad;
import java.util.ArrayList;
import java.util.List;
public class Facultad{
	private String nombre;
	private List<Especialidad> especialidades;
	
	public Facultad(String nombre){
		this.nombre = nombre;
	}
	
	public void setEspecialidades(List<Especialidad> especialidades){
		this.especialidades = new ArrayList<>(especialidades);
	}
}