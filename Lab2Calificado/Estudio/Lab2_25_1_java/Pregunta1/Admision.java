import java.util.ArrayList;
import java.util.List;

public class Admision{
	private List<FichaEvaluacion> listaFichas;
	private int cantidad_admitidos;
	private int cantidad_postulantes;
	
	//constructor sin parametro
	public Admision(){
		listaFichas = new ArrayList<FichaEvaluacion>();
		cantidad_admitidos = 0;
		cantidad_postulantes = 0;
	}
	
	public void agregarFichaDeEvaluacion(FichaEvaluacion ficha){
		if(ficha == null) return;
		listaFichas.add(new FichaEvaluacion(ficha));
		EstadoCandidato estado = ficha.getEstadoCandidato();
		if(estado == EstadoCandidato.ADMITIDO)
			cantidad_admitidos++;
		else cantidad_postulantes++;
	}
	
	@Override 
	public String toString(){
		String reporte = "PROCESO DE ADMISION: "+cantidad_postulantes+" postulantes"+", "+cantidad_admitidos+" admitidos";
		reporte += "\n";
		reporte += "LISTA DE ADMITIDOS: \n";
		for(FichaEvaluacion ficha : listaFichas){
			if(ficha.getEstadoCandidato() == EstadoCandidato.ADMITIDO){
				reporte += ficha.getCandidato().toString();
				reporte += "\n";
			}
		}
		return reporte;
	}
}

