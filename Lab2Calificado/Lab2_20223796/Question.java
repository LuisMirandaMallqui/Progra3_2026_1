import java.util.List;
import java.util.ArrayList;

public abstract class Question implements Printable{
	private String prompt;
	private int codigo;
	private List<String>opciones;
	
	//constructor con parametros
	public Question(int codigo, String prompt, List<String>opciones){
		this.prompt = prompt;
		this.codigo = codigo;
		this.opciones = opciones;
	}
	
	//constructor por defecto
	public Question(){
		opciones = new ArrayList<>();
	}
	
	//constructor copia 
	public Question(Question pregunta){
		this.prompt = pregunta.getPrompt();
		this.codigo = pregunta.getCodigo();
		this.opciones = new ArrayList<>(pregunta.getOpciones());// esta bien?
	}
	
	//setters y getters necesarios
	public String getPrompt(){
		return prompt;
	}
	public int getCodigo(){
		return codigo;
	}
	public List<String> getOpciones(){
		return opciones;
	}
	
	//metodos
	public String devolverDatos(){
		String datos = "Pregunta" + codigo + ":  " + prompt + "\n";
		int indice = 1;
		for(String opcion : opciones){
			datos += indice + ". " + opcion +"\n";
			indice++;
		}
		return datos;
	}
	
	public abstract Question clonar();
}	
