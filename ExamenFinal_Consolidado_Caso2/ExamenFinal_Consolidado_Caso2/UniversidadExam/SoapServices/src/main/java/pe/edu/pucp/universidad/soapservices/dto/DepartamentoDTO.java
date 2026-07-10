package pe.edu.pucp.universidad.soapservices.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "departamento")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DepartamentoDTO", propOrder = {"id", "nombre", "activo"})
public class DepartamentoDTO {
    public Long id;
    public String nombre;
    public boolean activo;

    public DepartamentoDTO() { }
}
