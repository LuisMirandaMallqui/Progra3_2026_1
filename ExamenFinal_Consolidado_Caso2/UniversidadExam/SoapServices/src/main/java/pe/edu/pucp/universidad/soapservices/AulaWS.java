package pe.edu.pucp.universidad.soapservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;

import pe.edu.pucp.universidad.business.AulaBO;
import pe.edu.pucp.universidad.business.implementsBO.AulaImplementsBO;
import pe.edu.pucp.universidad.model.Aula;
import pe.edu.pucp.universidad.soapservices.dto.AulaDTO;
import pe.edu.pucp.universidad.soapservices.mapper.UniversidadSoapMapper;

import java.util.ArrayList;
import java.util.List;

@WebService(
        serviceName = "AulaWS",
        targetNamespace = "http://soapservices.universidad.pucp.edu.pe/aula"
)
@XmlSeeAlso({AulaDTO.class})
public class AulaWS {

    private AulaBO bo;

    public AulaWS() {
        bo = new AulaImplementsBO();
    }

    @WebMethod(operationName = "pingAula")
    public String pingAula() {
        return "SOAP de aulas funcionando";
    }

    @WebMethod(operationName = "listarAulas")
    public AulaDTO[] listarAulas() {
        List<Aula> lista = bo.listarTodos();
        List<AulaDTO> respuesta = new ArrayList<>();
        if (lista != null) {
            for (Aula item : lista) {
                respuesta.add(UniversidadSoapMapper.toDTO(item));
            }
        }
        return respuesta.toArray(new AulaDTO[0]);
    }

    @WebMethod(operationName = "buscarAulaPorId")
    public AulaDTO buscarAulaPorId(@WebParam(name = "id") int id) {
        return UniversidadSoapMapper.toDTO(bo.buscarPorId(id));
    }

    @WebMethod(operationName = "insertarAula")
    public int insertarAula(@WebParam(name = "aula") AulaDTO aula) {
        Aula model = UniversidadSoapMapper.toModel(aula);
        return bo.insertar(model);
    }

    @WebMethod(operationName = "modificarAula")
    public int modificarAula(@WebParam(name = "id") int id,
                           @WebParam(name = "aula") AulaDTO aula) {
        Aula model = UniversidadSoapMapper.toModel(aula);
        model.setId((long) id);
        return bo.modificar(model);
    }

    @WebMethod(operationName = "eliminarAula")
    public int eliminarAula(@WebParam(name = "id") int id) {
        return bo.eliminar(id);
    }
}
