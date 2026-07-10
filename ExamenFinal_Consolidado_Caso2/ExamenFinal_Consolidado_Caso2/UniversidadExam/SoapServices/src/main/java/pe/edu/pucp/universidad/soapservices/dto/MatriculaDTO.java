package pe.edu.pucp.universidad.soapservices.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "matricula")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MatriculaDTO", propOrder = {"id", "estudiante", "semestre", "fechaInscripcion", "tipoMatricula", "estadoMatricula", "modalidad"})
public class MatriculaDTO {
    public Long id;
    public EstudianteDTO estudiante;
    public String semestre;
    public String fechaInscripcion;
    public String tipoMatricula;
    public String estadoMatricula;
    public String modalidad;

    public MatriculaDTO() { }
}
