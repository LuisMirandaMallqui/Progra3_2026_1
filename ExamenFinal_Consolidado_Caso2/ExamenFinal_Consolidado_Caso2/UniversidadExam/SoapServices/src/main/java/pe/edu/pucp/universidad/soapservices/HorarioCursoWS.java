package pe.edu.pucp.universidad.soapservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;

import pe.edu.pucp.universidad.business.HorarioCursoBO;
import pe.edu.pucp.universidad.business.implementsBO.HorarioCursoImplementsBO;
import pe.edu.pucp.universidad.model.HorarioCurso;
import pe.edu.pucp.universidad.soapservices.dto.HorarioCursoDTO;
import pe.edu.pucp.universidad.soapservices.mapper.UniversidadSoapMapper;

import java.util.ArrayList;
import java.util.List;

@WebService(
        serviceName = "HorarioCursoWS",
        targetNamespace = "http://soapservices.universidad.pucp.edu.pe/horario-curso"
)
@XmlSeeAlso({HorarioCursoDTO.class})
public class HorarioCursoWS {

    private HorarioCursoBO bo;

    public HorarioCursoWS() {
        bo = new HorarioCursoImplementsBO();
    }

    @WebMethod(operationName = "pingHorarioCurso")
    public String pingHorarioCurso() {
        return "SOAP de horariosCurso funcionando";
    }

    @WebMethod(operationName = "listarHorariosCurso")
    public HorarioCursoDTO[] listarHorariosCurso() {
        List<HorarioCurso> lista = bo.listarTodos();
        List<HorarioCursoDTO> respuesta = new ArrayList<>();
        if (lista != null) {
            for (HorarioCurso item : lista) {
                respuesta.add(UniversidadSoapMapper.toDTO(item));
            }
        }
        return respuesta.toArray(new HorarioCursoDTO[0]);
    }

    @WebMethod(operationName = "buscarHorarioCursoPorId")
    public HorarioCursoDTO buscarHorarioCursoPorId(@WebParam(name = "id") int id) {
        return UniversidadSoapMapper.toDTO(bo.buscarPorId(id));
    }

    @WebMethod(operationName = "insertarHorarioCurso")
    public int insertarHorarioCurso(@WebParam(name = "horarioCurso") HorarioCursoDTO horarioCurso) {
        HorarioCurso model = UniversidadSoapMapper.toModel(horarioCurso);
        return bo.insertar(model);
    }

    @WebMethod(operationName = "modificarHorarioCurso")
    public int modificarHorarioCurso(@WebParam(name = "id") int id,
                           @WebParam(name = "horarioCurso") HorarioCursoDTO horarioCurso) {
        HorarioCurso model = UniversidadSoapMapper.toModel(horarioCurso);
        model.setId((long) id);
        return bo.modificar(model);
    }

    @WebMethod(operationName = "eliminarHorarioCurso")
    public int eliminarHorarioCurso(@WebParam(name = "id") int id) {
        return bo.eliminar(id);
    }
}
