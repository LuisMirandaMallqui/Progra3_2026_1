package pe.edu.pucp.universidad.soapservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;

import pe.edu.pucp.universidad.business.MatriculaBO;
import pe.edu.pucp.universidad.business.implementsBO.MatriculaImplementsBO;
import pe.edu.pucp.universidad.model.Matricula;
import pe.edu.pucp.universidad.soapservices.dto.MatriculaDTO;
import pe.edu.pucp.universidad.soapservices.mapper.UniversidadSoapMapper;

import java.util.ArrayList;
import java.util.List;

@WebService(
        serviceName = "MatriculaWS",
        targetNamespace = "http://soapservices.universidad.pucp.edu.pe/matricula"
)
@XmlSeeAlso({MatriculaDTO.class})
public class MatriculaWS {

    private MatriculaBO bo;

    public MatriculaWS() {
        bo = new MatriculaImplementsBO();
    }

    @WebMethod(operationName = "pingMatricula")
    public String pingMatricula() {
        return "SOAP de matriculas funcionando";
    }

    @WebMethod(operationName = "listarMatriculas")
    public MatriculaDTO[] listarMatriculas() {
        List<Matricula> lista = bo.listarTodos();
        List<MatriculaDTO> respuesta = new ArrayList<>();
        if (lista != null) {
            for (Matricula item : lista) {
                respuesta.add(UniversidadSoapMapper.toDTO(item));
            }
        }
        return respuesta.toArray(new MatriculaDTO[0]);
    }

    @WebMethod(operationName = "buscarMatriculaPorId")
    public MatriculaDTO buscarMatriculaPorId(@WebParam(name = "id") int id) {
        return UniversidadSoapMapper.toDTO(bo.buscarPorId(id));
    }

    @WebMethod(operationName = "insertarMatricula")
    public int insertarMatricula(@WebParam(name = "matricula") MatriculaDTO matricula) {
        Matricula model = UniversidadSoapMapper.toModel(matricula);
        return bo.insertar(model);
    }

    @WebMethod(operationName = "modificarMatricula")
    public int modificarMatricula(@WebParam(name = "id") int id,
                           @WebParam(name = "matricula") MatriculaDTO matricula) {
        Matricula model = UniversidadSoapMapper.toModel(matricula);
        model.setId((long) id);
        return bo.modificar(model);
    }

    @WebMethod(operationName = "eliminarMatricula")
    public int eliminarMatricula(@WebParam(name = "id") int id) {
        return bo.eliminar(id);
    }
}
