package pe.edu.pucp.universidad.soapservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;

import pe.edu.pucp.universidad.business.DocenteBO;
import pe.edu.pucp.universidad.business.implementsBO.DocenteImplementsBO;
import pe.edu.pucp.universidad.model.Docente;
import pe.edu.pucp.universidad.soapservices.dto.DocenteDTO;
import pe.edu.pucp.universidad.soapservices.mapper.UniversidadSoapMapper;

import java.util.ArrayList;
import java.util.List;

@WebService(
        serviceName = "DocenteWS",
        targetNamespace = "http://soapservices.universidad.pucp.edu.pe/docente"
)
@XmlSeeAlso({DocenteDTO.class})
public class DocenteWS {

    private DocenteBO bo;

    public DocenteWS() {
        bo = new DocenteImplementsBO();
    }

    @WebMethod(operationName = "pingDocente")
    public String pingDocente() {
        return "SOAP de docentes funcionando";
    }

    @WebMethod(operationName = "listarDocentes")
    public DocenteDTO[] listarDocentes() {
        List<Docente> lista = bo.listarTodos();
        List<DocenteDTO> respuesta = new ArrayList<>();
        if (lista != null) {
            for (Docente item : lista) {
                respuesta.add(UniversidadSoapMapper.toDTO(item));
            }
        }
        return respuesta.toArray(new DocenteDTO[0]);
    }

    @WebMethod(operationName = "buscarDocentePorId")
    public DocenteDTO buscarDocentePorId(@WebParam(name = "id") int id) {
        return UniversidadSoapMapper.toDTO(bo.buscarPorId(id));
    }

    @WebMethod(operationName = "insertarDocente")
    public int insertarDocente(@WebParam(name = "docente") DocenteDTO docente) {
        Docente model = UniversidadSoapMapper.toModel(docente);
        return bo.insertar(model);
    }

    @WebMethod(operationName = "modificarDocente")
    public int modificarDocente(@WebParam(name = "id") int id,
                           @WebParam(name = "docente") DocenteDTO docente) {
        Docente model = UniversidadSoapMapper.toModel(docente);
        model.setId((long) id);
        return bo.modificar(model);
    }

    @WebMethod(operationName = "eliminarDocente")
    public int eliminarDocente(@WebParam(name = "id") int id) {
        return bo.eliminar(id);
    }
}
