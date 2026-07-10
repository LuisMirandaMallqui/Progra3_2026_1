package pe.edu.pucp.universidad.soapservices.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "estudiante")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EstudianteDTO", propOrder = {"id", "especialidad", "codigoUniversitario", "nombres", "apellidos", "correoInstitucional", "activo"})
public class EstudianteDTO {
    public Long id;
    public EspecialidadDTO especialidad;
    public String codigoUniversitario;
    public String nombres;
    public String apellidos;
    public String correoInstitucional;
    public boolean activo;

    public EstudianteDTO() { }
}
