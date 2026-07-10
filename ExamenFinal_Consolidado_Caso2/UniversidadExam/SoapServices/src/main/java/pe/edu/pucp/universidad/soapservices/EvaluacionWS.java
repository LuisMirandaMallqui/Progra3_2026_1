package pe.edu.pucp.universidad.soapservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;

import pe.edu.pucp.universidad.business.EvaluacionBO;
import pe.edu.pucp.universidad.business.implementsBO.EvaluacionImplementsBO;
import pe.edu.pucp.universidad.model.Evaluacion;
import pe.edu.pucp.universidad.soapservices.dto.EvaluacionDTO;
import pe.edu.pucp.universidad.soapservices.mapper.UniversidadSoapMapper;

import java.util.ArrayList;
import java.util.List;

@WebService(
        serviceName = "EvaluacionWS",
        targetNamespace = "http://soapservices.universidad.pucp.edu.pe/evaluacion"
)
@XmlSeeAlso({EvaluacionDTO.class})
public class EvaluacionWS {

    private EvaluacionBO bo;

    public EvaluacionWS() {
        bo = new EvaluacionImplementsBO();
    }

    @WebMethod(operationName = "pingEvaluacion")
    public String pingEvaluacion() {
        return "SOAP de evaluaciones funcionando";
    }

    @WebMethod(operationName = "listarEvaluaciones")
    public EvaluacionDTO[] listarEvaluaciones() {
        List<Evaluacion> lista = bo.listarTodos();
        List<EvaluacionDTO> respuesta = new ArrayList<>();
        if (lista != null) {
            for (Evaluacion item : lista) {
                respuesta.add(UniversidadSoapMapper.toDTO(item));
            }
        }
        return respuesta.toArray(new EvaluacionDTO[0]);
    }

    @WebMethod(operationName = "buscarEvaluacionPorId")
    public EvaluacionDTO buscarEvaluacionPorId(@WebParam(name = "id") int id) {
        return UniversidadSoapMapper.toDTO(bo.buscarPorId(id));
    }

    @WebMethod(operationName = "insertarEvaluacion")
    public int insertarEvaluacion(@WebParam(name = "evaluacion") EvaluacionDTO evaluacion) {
        Evaluacion model = UniversidadSoapMapper.toModel(evaluacion);
        return bo.insertar(model);
    }

    @WebMethod(operationName = "modificarEvaluacion")
    public int modificarEvaluacion(@WebParam(name = "id") int id,
                           @WebParam(name = "evaluacion") EvaluacionDTO evaluacion) {
        Evaluacion model = UniversidadSoapMapper.toModel(evaluacion);
        model.setId((long) id);
        return bo.modificar(model);
    }

    @WebMethod(operationName = "eliminarEvaluacion")
    public int eliminarEvaluacion(@WebParam(name = "id") int id) {
        return bo.eliminar(id);
    }
}
