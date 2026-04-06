import java.util.List;
	
public class MultipleChoiceQuestion extends Question implements Printable{
	private List<Integer> respuestas;
	
	public MultipleChoiceQuestion(int codigo, String prompt, List<String>opciones, List<Integer> respuestas){
		super(codigo, prompt, opciones);
		this.respuestas = respuestas;
	}
	
	public String devolverDatos(){
		String datos = super.devolverDatos();
		datos+= "Seleccione las opciones de su respuesta \n";
		return datos;
	}
	
	@Override
    public Question clonar() {
        return new MultipleChoiceQuestion(this.getCodigo(), this.getPrompt(), this.getOpciones(), this.respuestas);
    }
}