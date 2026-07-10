package pe.edu.pucp.universidad.soapservices.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "especialidad")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EspecialidadDTO", propOrder = {"id", "facultad", "nombre", "activo"})
public class EspecialidadDTO {
    public Long id;
    public FacultadDTO facultad;
    public String nombre;
    public boolean activo;

    public EspecialidadDTO() { }
}
