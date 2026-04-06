import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class Assessment{
	private int duracion; //int? float? Date?
	private Date fecha_hora_inicio;
	private List<Teacher>profesores;
	private List<Item>items;
	
	public Assessment(int duracion, Date fecha_hora_inicio, List<Teacher>profesores,List<Item>items){
		this.duracion = duracion;
		this.fecha_hora_inicio = fecha_hora_inicio;
		this.profesores = new ArrayList<>(profesores);
		this.items = new ArrayList<>(items);
	}
	
	public Assessment(){
		profesores = new ArrayList<>();
		items = new ArrayList<>();
	}
	
	
	//setters y getters necesarios
	public Date getFechaHoraInicio(){
		return fecha_hora_inicio;
	}
	
	public List<Item> getItems(){
		return items;
	}
}