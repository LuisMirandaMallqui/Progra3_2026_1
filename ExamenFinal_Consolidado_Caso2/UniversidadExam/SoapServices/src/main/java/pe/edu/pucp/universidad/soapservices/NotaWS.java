package pe.edu.pucp.universidad.soapservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;

import pe.edu.pucp.universidad.business.NotaBO;
import pe.edu.pucp.universidad.business.implementsBO.NotaImplementsBO;
import pe.edu.pucp.universidad.model.Nota;
import pe.edu.pucp.universidad.soapservices.dto.NotaDTO;
import pe.edu.pucp.universidad.soapservices.mapper.UniversidadSoapMapper;

import java.util.ArrayList;
import java.util.List;

@WebService(
        serviceName = "NotaWS",
        targetNamespace = "http://soapservices.universidad.pucp.edu.pe/nota"
)
@XmlSeeAlso({NotaDTO.class})
public class NotaWS {

    private NotaBO bo;

    public NotaWS() {
        bo = new NotaImplementsBO();
    }

    @WebMethod(operationName = "pingNota")
    public String pingNota() {
        return "SOAP de notas funcionando";
    }

    @WebMethod(operationName = "listarNotas")
    public NotaDTO[] listarNotas() {
        List<Nota> lista = bo.listarTodos();
        List<NotaDTO> respuesta = new ArrayList<>();
        if (lista != null) {
            for (Nota item : lista) respuesta.add(UniversidadSoapMapper.toDTO(item));
        }
        return respuesta.toArray(new NotaDTO[0]);
    }

    @WebMethod(operationName = "buscarNotaPorId")
    public NotaDTO buscarNotaPorId(@WebParam(name = "id") int id) {
        return UniversidadSoapMapper.toDTO(bo.buscarPorId(id));
    }

    @WebMethod(operationName = "insertarNota")
    public int insertarNota(@WebParam(name = "nota") NotaDTO nota) {
        return bo.insertar(UniversidadSoapMapper.toModel(nota));
    }

    @WebMethod(operationName = "modificarNota")
    public int modificarNota(@WebParam(name = "id") int id,
                             @WebParam(name = "nota") NotaDTO nota) {
        Nota model = UniversidadSoapMapper.toModel(nota);
        model.setId((long) id);
        return bo.modificar(model);
    }

    @WebMethod(operationName = "eliminarNota")
    public int eliminarNota(@WebParam(name = "id") int id) {
        return bo.eliminar(id);
    }

    @WebMethod(operationName = "calcularPromedioFinal")
    public double calcularPromedioFinal(@WebParam(name = "idMatricula") int idMatricula,
                                        @WebParam(name = "idHorarioCurso") int idHorarioCurso) {
        return bo.calcularPromedioFinal(idMatricula, idHorarioCurso);
    }
}
