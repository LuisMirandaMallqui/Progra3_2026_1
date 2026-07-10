package pe.edu.pucp.universidad.soapservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;

import pe.edu.pucp.universidad.business.HorarioCursoDetBO;
import pe.edu.pucp.universidad.business.implementsBO.HorarioCursoDetImplementsBO;
import pe.edu.pucp.universidad.model.HorarioCursoDet;
import pe.edu.pucp.universidad.soapservices.dto.HorarioCursoDetDTO;
import pe.edu.pucp.universidad.soapservices.mapper.UniversidadSoapMapper;

import java.util.ArrayList;
import java.util.List;

@WebService(
        serviceName = "HorarioCursoDetWS",
        targetNamespace = "http://soapservices.universidad.pucp.edu.pe/horario-curso-det"
)
@XmlSeeAlso({HorarioCursoDetDTO.class})
public class HorarioCursoDetWS {

    private HorarioCursoDetBO bo;

    public HorarioCursoDetWS() {
        bo = new HorarioCursoDetImplementsBO();
    }

    @WebMethod(operationName = "pingHorarioCursoDet")
    public String pingHorarioCursoDet() {
        return "SOAP de horariosCursoDet funcionando";
    }

    @WebMethod(operationName = "listarHorariosCursoDet")
    public HorarioCursoDetDTO[] listarHorariosCursoDet() {
        List<HorarioCursoDet> lista = bo.listarTodos();
        List<HorarioCursoDetDTO> respuesta = new ArrayList<>();
        if (lista != null) {
            for (HorarioCursoDet item : lista) {
                respuesta.add(UniversidadSoapMapper.toDTO(item));
            }
        }
        return respuesta.toArray(new HorarioCursoDetDTO[0]);
    }

    @WebMethod(operationName = "buscarHorarioCursoDetPorId")
    public HorarioCursoDetDTO buscarHorarioCursoDetPorId(@WebParam(name = "id") int id) {
        return UniversidadSoapMapper.toDTO(bo.buscarPorId(id));
    }

    @WebMethod(operationName = "insertarHorarioCursoDet")
    public int insertarHorarioCursoDet(@WebParam(name = "horarioCursoDet") HorarioCursoDetDTO horarioCursoDet) {
        HorarioCursoDet model = UniversidadSoapMapper.toModel(horarioCursoDet);
        return bo.insertar(model);
    }

    @WebMethod(operationName = "modificarHorarioCursoDet")
    public int modificarHorarioCursoDet(@WebParam(name = "id") int id,
                           @WebParam(name = "horarioCursoDet") HorarioCursoDetDTO horarioCursoDet) {
        HorarioCursoDet model = UniversidadSoapMapper.toModel(horarioCursoDet);
        model.setId((long) id);
        return bo.modificar(model);
    }

    @WebMethod(operationName = "eliminarHorarioCursoDet")
    public int eliminarHorarioCursoDet(@WebParam(name = "id") int id) {
        return bo.eliminar(id);
    }
}
