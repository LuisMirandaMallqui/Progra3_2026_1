import java.util.List;

public class SingleChoiceQuestion extends Question implements Printable{
	private int respuesta;
	//1, "1 + 1?", List.of("1", "2", "11", "0"), 2
	public SingleChoiceQuestion(int codigo, String prompt, List<String>opciones, int respuesta){
		super(codigo, prompt, opciones);
		this.respuesta = respuesta;
	}
	
	public String devolverDatos(){
		String datos = super.devolverDatos();
		datos+= "Ingrese su respuesta: \n";
		return datos;
	}
	
	@Override
    public Question clonar() {
        return new SingleChoiceQuestion(this.getCodigo(), this.getPrompt(), this.getOpciones(), this.respuesta);
    }
}