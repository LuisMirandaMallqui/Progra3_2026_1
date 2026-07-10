package pe.edu.pucp.universidad.soapservices.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "matriculaHorario")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MatriculaHorarioDTO", propOrder = {"matricula", "horarioCurso", "fechaRegistro"})
public class MatriculaHorarioDTO {
    public MatriculaDTO matricula;
    public HorarioCursoDTO horarioCurso;
    public String fechaRegistro;

    public MatriculaHorarioDTO() { }
}
