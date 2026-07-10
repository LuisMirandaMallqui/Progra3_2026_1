package pe.edu.pucp.universidad.soapservices.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "docente")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocenteDTO", propOrder = {"id", "departamento", "codigo", "nombreCompleto", "categoria", "dedicacion", "activo"})
public class DocenteDTO {
    public Long id;
    public DepartamentoDTO departamento;
    public String codigo;
    public String nombreCompleto;
    public String categoria;
    public String dedicacion;
    public boolean activo;

    public DocenteDTO() { }
}
