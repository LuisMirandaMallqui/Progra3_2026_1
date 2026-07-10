package pe.edu.pucp.universidad.soapservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;

import pe.edu.pucp.universidad.business.CursoBO;
import pe.edu.pucp.universidad.business.implementsBO.CursoImplementsBO;
import pe.edu.pucp.universidad.model.Curso;
import pe.edu.pucp.universidad.soapservices.dto.CursoDTO;
import pe.edu.pucp.universidad.soapservices.mapper.UniversidadSoapMapper;

import java.util.ArrayList;
import java.util.List;

@WebService(
        serviceName = "CursoWS",
        targetNamespace = "http://soapservices.universidad.pucp.edu.pe/curso"
)
@XmlSeeAlso({CursoDTO.class})
public class CursoWS {

    private CursoBO bo;

    public CursoWS() {
        bo = new CursoImplementsBO();
    }

    @WebMethod(operationName = "pingCurso")
    public String pingCurso() {
        return "SOAP de cursos funcionando";
    }

    @WebMethod(operationName = "listarCursos")
    public CursoDTO[] listarCursos() {
        List<Curso> lista = bo.listarTodos();
        List<CursoDTO> respuesta = new ArrayList<>();
        if (lista != null) {
            for (Curso item : lista) {
                respuesta.add(UniversidadSoapMapper.toDTO(item));
            }
        }
        return respuesta.toArray(new CursoDTO[0]);
    }

    @WebMethod(operationName = "buscarCursoPorId")
    public CursoDTO buscarCursoPorId(@WebParam(name = "id") int id) {
        return UniversidadSoapMapper.toDTO(bo.buscarPorId(id));
    }

    @WebMethod(operationName = "insertarCurso")
    public int insertarCurso(@WebParam(name = "curso") CursoDTO curso) {
        Curso model = UniversidadSoapMapper.toModel(curso);
        return bo.insertar(model);
    }

    @WebMethod(operationName = "modificarCurso")
    public int modificarCurso(@WebParam(name = "id") int id,
                           @WebParam(name = "curso") CursoDTO curso) {
        Curso model = UniversidadSoapMapper.toModel(curso);
        model.setId((long) id);
        return bo.modificar(model);
    }

    @WebMethod(operationName = "eliminarCurso")
    public int eliminarCurso(@WebParam(name = "id") int id) {
        return bo.eliminar(id);
    }
}
