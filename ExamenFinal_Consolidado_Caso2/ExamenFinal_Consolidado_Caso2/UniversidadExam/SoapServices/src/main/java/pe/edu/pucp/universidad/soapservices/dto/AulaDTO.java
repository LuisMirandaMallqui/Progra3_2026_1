package pe.edu.pucp.universidad.soapservices.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "aula")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AulaDTO", propOrder = {"id", "codigoAula", "ubicacion", "activo"})
public class AulaDTO {
    public Long id;
    public String codigoAula;
    public String ubicacion;
    public boolean activo;

    public AulaDTO() { }
}
