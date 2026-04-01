import java.time.LocalDateTime;
//Fecha y Hora LocalDateTime
//Solo Fecha LocalDate
public class FichaEvaluacion{
	private Postulante candidato;
	private LocalDateTime fecha_hora;
	private int evaluacion_expediente;
	private int evaluacion_entrevista;
	private int evaluacion_examen;
	private EstadoCandidato estado_candidato = EstadoCandidato.SIN_EVALUACION;
	
	//propiedades
	
	public void setCandidato(Postulante candidato){
		this.candidato = new Postulante(candidato);
	}
	public Postulante getCandidato(){
		return new Postulante(this.candidato);
	}
	
	public void setFechaHora(LocalDateTime fecha_hora){
		this.fecha_hora = fecha_hora;
	}
	public LocalDateTime getFechaHora(){
		return fecha_hora;
	}
	public void setEvaluacionExpediente(int evaluacion_expediente){
		this.evaluacion_expediente = evaluacion_expediente;
	}
	public int getEvaluacionExpediente(){
		return evaluacion_expediente;
	}
	public void setEvaluacionEntrevista(int evaluacion_entrevista){
		this.evaluacion_entrevista = evaluacion_entrevista;
	}
	public int getEvaluacionEntrevista(){
		return evaluacion_entrevista;
	}
	public void setEvaluacionExamen(int evaluacion_examen){
		this.evaluacion_examen = evaluacion_examen;
	}
	public int getEvaluacionExamen(){
		return evaluacion_examen;
	}
	public void setEstadoCandidato(EstadoCandidato estado_candidato){
		this.estado_candidato = estado_candidato;
	}
	public EstadoCandidato getEstadoCandidato(){
		int puntaje_total = evaluacion_entrevista + evaluacion_expediente + evaluacion_examen;
		if(puntaje_total > 75) estado_candidato = EstadoCandidato.ADMITIDO;
		else estado_candidato = EstadoCandidato.NO_ADMITIDO;
		return estado_candidato;
	}
	
	//constructor con parametros
	public FichaEvaluacion(Postulante candidato, LocalDateTime fecha_hora, int evaluacion_expediente,
		int evaluacion_entrevista, int evaluacion_examen, EstadoCandidato estado_candidato){
		this.candidato = new Postulante(candidato);
		this.fecha_hora = fecha_hora;
		this.evaluacion_expediente = evaluacion_expediente;
		this.evaluacion_entrevista = evaluacion_entrevista;
		this.evaluacion_examen = evaluacion_examen;
		this.estado_candidato = estado_candidato;
	}
	//constructor sin parametros
	public FichaEvaluacion(){
		
	}
	//constructor copia 
	public FichaEvaluacion(FichaEvaluacion ficha){
		if(ficha != null){
			this.candidato = (ficha.candidato != null) ? new Postulante(ficha.candidato) : null;
			this.fecha_hora = ficha.fecha_hora;
			this.evaluacion_expediente = ficha.evaluacion_expediente;
			this.evaluacion_entrevista = ficha.evaluacion_entrevista;
			this.evaluacion_examen = ficha.evaluacion_examen;
			this.estado_candidato = ficha.estado_candidato;
		}
	}
}