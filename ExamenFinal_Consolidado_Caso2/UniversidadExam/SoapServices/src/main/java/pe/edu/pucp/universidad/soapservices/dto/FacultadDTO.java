package pe.edu.pucp.universidad.soapservices.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "facultad")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FacultadDTO", propOrder = {"id", "nombre", "activo"})
public class FacultadDTO {
    public Long id;
    public String nombre;
    public boolean activo;

    public FacultadDTO() { }
}
