package pe.edu.pucp.universidad.soapservices.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "curso")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CursoDTO", propOrder = {"id", "especialidad", "codigo", "nombre", "creditos", "nivelAcademico", "estadoCurso", "activo"})
public class CursoDTO {
    public Long id;
    public EspecialidadDTO especialidad;
    public String codigo;
    public String nombre;
    public int creditos;
    public int nivelAcademico;
    public String estadoCurso;
    public boolean activo;

    public CursoDTO() { }
}
