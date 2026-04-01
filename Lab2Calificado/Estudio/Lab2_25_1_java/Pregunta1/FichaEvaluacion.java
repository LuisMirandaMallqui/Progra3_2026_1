import java.util.Date;

public class FichaEvaluacion{
	private Postulante candidato;
	private Date fecha_hora;
	private int evaluacion_expediente;
	private int evaluacion_entrevista;
	private int evaluacion_examen;
	private EstadoCandidato estado_candidato = EstadoCandidato.SIN_EVALUACION;
	
	//propiedades
	
	public void setCandidato(){}
	public Postulante getCandidato(){}
	public void setFechaHora(){}
	public Date getFechaHora(){}
	public void setEvaluacionExpediente(){}
	public int getEvaluacionExpediente(){}
	public void setEvaluacionEntrevista(){}
	public int getEvaluacionEntrevista(){}
	public void setEvaluacionExamen(){}
	public int getEvaluacionExamen(){}
	public void setEstadoCandidato(){}
	public void getEstadoCandidato(){}
	
	//constructor con parametros
	public FichaEvaluacion(){
		
	}
	//constructor sin parametros
	public FichaEvaluacion(){
		
	}
	//constructor copia 
	public FichaEvaluacion(){
		
	}
}