package pe.edu.pucp.universidad.soapservices.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "horarioCurso")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HorarioCursoDTO", propOrder = {"id", "curso", "semestre", "codigoHorario", "docente", "cupoMaximo", "estado"})
public class HorarioCursoDTO {
    public Long id;
    public CursoDTO curso;
    public String semestre;
    public String codigoHorario;
    public DocenteDTO docente;
    public int cupoMaximo;
    public String estado;

    public HorarioCursoDTO() { }
}
