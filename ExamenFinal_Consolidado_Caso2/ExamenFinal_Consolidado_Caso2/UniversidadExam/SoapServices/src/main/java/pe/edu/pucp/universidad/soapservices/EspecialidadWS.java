package pe.edu.pucp.universidad.soapservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;

import pe.edu.pucp.universidad.business.EspecialidadBO;
import pe.edu.pucp.universidad.business.implementsBO.EspecialidadImplementsBO;
import pe.edu.pucp.universidad.model.Especialidad;
import pe.edu.pucp.universidad.soapservices.dto.EspecialidadDTO;
import pe.edu.pucp.universidad.soapservices.mapper.UniversidadSoapMapper;

import java.util.ArrayList;
import java.util.List;

@WebService(
        serviceName = "EspecialidadWS",
        targetNamespace = "http://soapservices.universidad.pucp.edu.pe/especialidad"
)
@XmlSeeAlso({EspecialidadDTO.class})
public class EspecialidadWS {

    private EspecialidadBO bo;

    public EspecialidadWS() {
        bo = new EspecialidadImplementsBO();
    }

    @WebMethod(operationName = "pingEspecialidad")
    public String pingEspecialidad() {
        return "SOAP de especialidades funcionando";
    }

    @WebMethod(operationName = "listarEspecialidades")
    public EspecialidadDTO[] listarEspecialidades() {
        List<Especialidad> lista = bo.listarTodos();
        List<EspecialidadDTO> respuesta = new ArrayList<>();
        if (lista != null) {
            for (Especialidad item : lista) {
                respuesta.add(UniversidadSoapMapper.toDTO(item));
            }
        }
        return respuesta.toArray(new EspecialidadDTO[0]);
    }

    @WebMethod(operationName = "buscarEspecialidadPorId")
    public EspecialidadDTO buscarEspecialidadPorId(@WebParam(name = "id") int id) {
        return UniversidadSoapMapper.toDTO(bo.buscarPorId(id));
    }

    @WebMethod(operationName = "insertarEspecialidad")
    public int insertarEspecialidad(@WebParam(name = "especialidad") EspecialidadDTO especialidad) {
        Especialidad model = UniversidadSoapMapper.toModel(especialidad);
        return bo.insertar(model);
    }

    @WebMethod(operationName = "modificarEspecialidad")
    public int modificarEspecialidad(@WebParam(name = "id") int id,
                           @WebParam(name = "especialidad") EspecialidadDTO especialidad) {
        Especialidad model = UniversidadSoapMapper.toModel(especialidad);
        model.setId((long) id);
        return bo.modificar(model);
    }

    @WebMethod(operationName = "eliminarEspecialidad")
    public int eliminarEspecialidad(@WebParam(name = "id") int id) {
        return bo.eliminar(id);
    }
}
