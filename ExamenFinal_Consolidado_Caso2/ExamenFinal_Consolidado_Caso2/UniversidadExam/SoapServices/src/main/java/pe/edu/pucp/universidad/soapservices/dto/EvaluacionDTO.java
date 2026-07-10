package pe.edu.pucp.universidad.soapservices.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "evaluacion")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EvaluacionDTO", propOrder = {"id", "horarioCurso", "tipoEvaluacion", "peso", "fechaEvaluacion"})
public class EvaluacionDTO {
    public Long id;
    public HorarioCursoDTO horarioCurso;
    public String tipoEvaluacion;
    public double peso;
    public String fechaEvaluacion;

    public EvaluacionDTO() { }
}
