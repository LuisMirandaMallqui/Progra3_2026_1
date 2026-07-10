package pe.edu.pucp.universidad.soapservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;

import pe.edu.pucp.universidad.business.EstudianteBO;
import pe.edu.pucp.universidad.business.implementsBO.EstudianteImplementsBO;
import pe.edu.pucp.universidad.model.Estudiante;
import pe.edu.pucp.universidad.soapservices.dto.EstudianteDTO;
import pe.edu.pucp.universidad.soapservices.mapper.UniversidadSoapMapper;

import java.util.ArrayList;
import java.util.List;

@WebService(
        serviceName = "EstudianteWS",
        targetNamespace = "http://soapservices.universidad.pucp.edu.pe/estudiante"
)
@XmlSeeAlso({EstudianteDTO.class})
public class EstudianteWS {

    private EstudianteBO bo;

    public EstudianteWS() {
        bo = new EstudianteImplementsBO();
    }

    @WebMethod(operationName = "pingEstudiante")
    public String pingEstudiante() {
        return "SOAP de estudiantes funcionando";
    }

    @WebMethod(operationName = "listarEstudiantes")
    public EstudianteDTO[] listarEstudiantes() {
        List<Estudiante> lista = bo.listarTodos();
        List<EstudianteDTO> respuesta = new ArrayList<>();
        if (lista != null) {
            for (Estudiante item : lista) {
                respuesta.add(UniversidadSoapMapper.toDTO(item));
            }
        }
        return respuesta.toArray(new EstudianteDTO[0]);
    }

    @WebMethod(operationName = "buscarEstudiantePorId")
    public EstudianteDTO buscarEstudiantePorId(@WebParam(name = "id") int id) {
        return UniversidadSoapMapper.toDTO(bo.buscarPorId(id));
    }

    @WebMethod(operationName = "insertarEstudiante")
    public int insertarEstudiante(@WebParam(name = "estudiante") EstudianteDTO estudiante) {
        Estudiante model = UniversidadSoapMapper.toModel(estudiante);
        return bo.insertar(model);
    }

    @WebMethod(operationName = "modificarEstudiante")
    public int modificarEstudiante(@WebParam(name = "id") int id,
                           @WebParam(name = "estudiante") EstudianteDTO estudiante) {
        Estudiante model = UniversidadSoapMapper.toModel(estudiante);
        model.setId((long) id);
        return bo.modificar(model);
    }

    @WebMethod(operationName = "eliminarEstudiante")
    public int eliminarEstudiante(@WebParam(name = "id") int id) {
        return bo.eliminar(id);
    }
}
