public class Item{
	private Question pregunta;
	private int puntaje;
		
	public Item(Question pregunta,int puntaje){
		this.pregunta = (pregunta!=null)? pregunta.clonar() : null;
		this.puntaje = puntaje;
	}
	
	//setters y getters necesarios 
	public Question getPregunta(){
		return pregunta.clonar();
	}
}