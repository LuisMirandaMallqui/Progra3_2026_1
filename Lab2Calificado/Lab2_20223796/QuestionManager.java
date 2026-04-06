import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class QuestionManager{
	private List<Question>preguntas;
	
	//constructor sin parametros
	public QuestionManager(){
		preguntas = new ArrayList<>();
	}
	
	public void add(Question pregunta){
		// sin new Question(pregunta) porque eso mata el polimorfismo
		preguntas.add(pregunta.copiar()); //constructor copia para pregunta 
	}
	
	public ArrayList<Question> selectRandomN(int N){
		ArrayList<Question>preguntas_aleatorias = new ArrayList<Question>();	
		for(int n=0;n<N;n++){ // revisar
			Random rand = new Random();
			preguntas_aleatorias.add(preguntas.get(rand.nextInt(preguntas.size())));
		}
		return preguntas_aleatorias;
	}
}