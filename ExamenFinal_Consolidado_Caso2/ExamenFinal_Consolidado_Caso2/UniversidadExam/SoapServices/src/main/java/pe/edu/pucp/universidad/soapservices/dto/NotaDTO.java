package pe.edu.pucp.universidad.soapservices.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "nota")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NotaDTO", propOrder = {"id", "evaluacion", "matricula", "horarioCurso", "calificacion"})
public class NotaDTO {
    public Long id;
    public EvaluacionDTO evaluacion;
    public MatriculaDTO matricula;
    public HorarioCursoDTO horarioCurso;
    public double calificacion;

    public NotaDTO() { }
}
