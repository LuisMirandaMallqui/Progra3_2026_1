package pe.edu.pucp.universidad.soapservices.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "cursoPrerrequisito")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CursoPrerrequisitoDTO", propOrder = {"curso", "cursoPrerreq", "activo"})
public class CursoPrerrequisitoDTO {
    public CursoDTO curso;
    public CursoDTO cursoPrerreq;
    public boolean activo;

    public CursoPrerrequisitoDTO() { }
}
