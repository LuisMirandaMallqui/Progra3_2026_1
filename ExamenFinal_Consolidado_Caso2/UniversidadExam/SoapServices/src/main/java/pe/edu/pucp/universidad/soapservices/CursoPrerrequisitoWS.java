package pe.edu.pucp.universidad.soapservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;

import pe.edu.pucp.universidad.business.CursoPrerrequisitoBO;
import pe.edu.pucp.universidad.business.implementsBO.CursoPrerrequisitoImplementsBO;
import pe.edu.pucp.universidad.model.CursoPrerrequisito;
import pe.edu.pucp.universidad.soapservices.dto.CursoPrerrequisitoDTO;
import pe.edu.pucp.universidad.soapservices.mapper.UniversidadSoapMapper;

import java.util.ArrayList;
import java.util.List;

@WebService(
        serviceName = "CursoPrerrequisitoWS",
        targetNamespace = "http://soapservices.universidad.pucp.edu.pe/curso-prerrequisito"
)
@XmlSeeAlso({CursoPrerrequisitoDTO.class})
public class CursoPrerrequisitoWS {

    private CursoPrerrequisitoBO bo;

    public CursoPrerrequisitoWS() {
        bo = new CursoPrerrequisitoImplementsBO();
    }

    @WebMethod(operationName = "pingCursoPrerrequisito")
    public String pingCursoPrerrequisito() {
        return "SOAP de cursos prerrequisitos funcionando";
    }

    @WebMethod(operationName = "listarCursoPrerrequisitos")
    public CursoPrerrequisitoDTO[] listarCursoPrerrequisitos() {
        List<CursoPrerrequisito> lista = bo.listarTodos();
        List<CursoPrerrequisitoDTO> respuesta = new ArrayList<>();
        if (lista != null) {
            for (CursoPrerrequisito item : lista) respuesta.add(UniversidadSoapMapper.toDTO(item));
        }
        return respuesta.toArray(new CursoPrerrequisitoDTO[0]);
    }

    @WebMethod(operationName = "buscarCursoPrerrequisitoPorId")
    public CursoPrerrequisitoDTO buscarCursoPrerrequisitoPorId(@WebParam(name = "idCurso") int idCurso,
                                                               @WebParam(name = "idCursoPrerreq") int idCursoPrerreq) {
        return UniversidadSoapMapper.toDTO(bo.buscarPorId(idCurso, idCursoPrerreq));
    }

    @WebMethod(operationName = "insertarCursoPrerrequisito")
    public int insertarCursoPrerrequisito(@WebParam(name = "cursoPrerrequisito") CursoPrerrequisitoDTO dto) {
        return bo.insertar(UniversidadSoapMapper.toModel(dto));
    }

    @WebMethod(operationName = "modificarCursoPrerrequisito")
    public int modificarCursoPrerrequisito(@WebParam(name = "idCurso") int idCurso,
                                           @WebParam(name = "idCursoPrerreq") int idCursoPrerreq,
                                           @WebParam(name = "cursoPrerrequisito") CursoPrerrequisitoDTO dto) {
        CursoPrerrequisito model = UniversidadSoapMapper.toModel(dto);
        if (model.getCurso() != null) model.getCurso().setId((long) idCurso);
        if (model.getCursoPrerreq() != null) model.getCursoPrerreq().setId((long) idCursoPrerreq);
        return bo.modificar(model);
    }

    @WebMethod(operationName = "eliminarCursoPrerrequisito")
    public int eliminarCursoPrerrequisito(@WebParam(name = "idCurso") int idCurso,
                                          @WebParam(name = "idCursoPrerreq") int idCursoPrerreq) {
        return bo.eliminar(idCurso, idCursoPrerreq);
    }
}
