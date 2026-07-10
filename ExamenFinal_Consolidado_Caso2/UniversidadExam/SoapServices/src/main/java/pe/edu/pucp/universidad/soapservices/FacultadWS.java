package pe.edu.pucp.universidad.soapservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;

import pe.edu.pucp.universidad.business.FacultadBO;
import pe.edu.pucp.universidad.business.implementsBO.FacultadImplementsBO;
import pe.edu.pucp.universidad.model.Facultad;
import pe.edu.pucp.universidad.soapservices.dto.FacultadDTO;
import pe.edu.pucp.universidad.soapservices.mapper.UniversidadSoapMapper;

import java.util.ArrayList;
import java.util.List;

@WebService(
        serviceName = "FacultadWS",
        targetNamespace = "http://soapservices.universidad.pucp.edu.pe/facultad"
)
@XmlSeeAlso({FacultadDTO.class})
public class FacultadWS {

    private FacultadBO bo;

    public FacultadWS() {
        bo = new FacultadImplementsBO();
    }

    @WebMethod(operationName = "pingFacultad")
    public String pingFacultad() {
        return "SOAP de facultades funcionando";
    }

    @WebMethod(operationName = "listarFacultades")
    public FacultadDTO[] listarFacultades() {
        List<Facultad> lista = bo.listarTodos();
        List<FacultadDTO> respuesta = new ArrayList<>();
        if (lista != null) {
            for (Facultad item : lista) {
                respuesta.add(UniversidadSoapMapper.toDTO(item));
            }
        }
        return respuesta.toArray(new FacultadDTO[0]);
    }

    @WebMethod(operationName = "buscarFacultadPorId")
    public FacultadDTO buscarFacultadPorId(@WebParam(name = "id") int id) {
        return UniversidadSoapMapper.toDTO(bo.buscarPorId(id));
    }

    @WebMethod(operationName = "insertarFacultad")
    public int insertarFacultad(@WebParam(name = "facultad") FacultadDTO facultad) {
        Facultad model = UniversidadSoapMapper.toModel(facultad);
        return bo.insertar(model);
    }

    @WebMethod(operationName = "modificarFacultad")
    public int modificarFacultad(@WebParam(name = "id") int id,
                           @WebParam(name = "facultad") FacultadDTO facultad) {
        Facultad model = UniversidadSoapMapper.toModel(facultad);
        model.setId((long) id);
        return bo.modificar(model);
    }

    @WebMethod(operationName = "eliminarFacultad")
    public int eliminarFacultad(@WebParam(name = "id") int id) {
        return bo.eliminar(id);
    }
}
