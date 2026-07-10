package pe.edu.pucp.universidad.soapservices.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "horarioCursoDet")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HorarioCursoDetDTO", propOrder = {"id", "horarioCurso", "tipoSesion", "diaSemana", "horaInicio", "horaFin", "aula", "frecuencia"})
public class HorarioCursoDetDTO {
    public Long id;
    public HorarioCursoDTO horarioCurso;
    public String tipoSesion;
    public int diaSemana;
    public String horaInicio;
    public String horaFin;
    public AulaDTO aula;
    public String frecuencia;

    public HorarioCursoDetDTO() { }
}
