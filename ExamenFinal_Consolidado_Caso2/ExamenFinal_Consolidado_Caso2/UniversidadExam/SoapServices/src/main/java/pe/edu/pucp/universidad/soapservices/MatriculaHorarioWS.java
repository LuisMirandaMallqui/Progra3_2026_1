package pe.edu.pucp.universidad.soapservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;

import pe.edu.pucp.universidad.business.MatriculaHorarioBO;
import pe.edu.pucp.universidad.business.implementsBO.MatriculaHorarioImplementsBO;
import pe.edu.pucp.universidad.model.MatriculaHorario;
import pe.edu.pucp.universidad.soapservices.dto.MatriculaHorarioDTO;
import pe.edu.pucp.universidad.soapservices.mapper.UniversidadSoapMapper;

import java.util.ArrayList;
import java.util.List;

@WebService(
        serviceName = "MatriculaHorarioWS",
        targetNamespace = "http://soapservices.universidad.pucp.edu.pe/matricula-horario"
)
@XmlSeeAlso({MatriculaHorarioDTO.class})
public class MatriculaHorarioWS {

    private MatriculaHorarioBO bo;

    public MatriculaHorarioWS() {
        bo = new MatriculaHorarioImplementsBO();
    }

    @WebMethod(operationName = "pingMatriculaHorario")
    public String pingMatriculaHorario() {
        return "SOAP de matriculas horarios funcionando";
    }

    @WebMethod(operationName = "listarMatriculaHorarios")
    public MatriculaHorarioDTO[] listarMatriculaHorarios() {
        List<MatriculaHorario> lista = bo.listarTodos();
        List<MatriculaHorarioDTO> respuesta = new ArrayList<>();
        if (lista != null) {
            for (MatriculaHorario item : lista) respuesta.add(UniversidadSoapMapper.toDTO(item));
        }
        return respuesta.toArray(new MatriculaHorarioDTO[0]);
    }

    @WebMethod(operationName = "buscarMatriculaHorarioPorId")
    public MatriculaHorarioDTO buscarMatriculaHorarioPorId(@WebParam(name = "idMatricula") int idMatricula,
                                                           @WebParam(name = "idHorarioCurso") int idHorarioCurso) {
        return UniversidadSoapMapper.toDTO(bo.buscarPorId(idMatricula, idHorarioCurso));
    }

    @WebMethod(operationName = "insertarMatriculaHorario")
    public int insertarMatriculaHorario(@WebParam(name = "matriculaHorario") MatriculaHorarioDTO dto) {
        return bo.insertar(UniversidadSoapMapper.toModel(dto));
    }

    @WebMethod(operationName = "insertarMatriculaHorarioValidado")
    public int insertarMatriculaHorarioValidado(@WebParam(name = "matriculaHorario") MatriculaHorarioDTO dto) {
        return bo.insertarValidado(UniversidadSoapMapper.toModel(dto));
    }

    @WebMethod(operationName = "modificarMatriculaHorario")
    public int modificarMatriculaHorario(@WebParam(name = "idMatricula") int idMatricula,
                                         @WebParam(name = "idHorarioCurso") int idHorarioCurso,
                                         @WebParam(name = "matriculaHorario") MatriculaHorarioDTO dto) {
        MatriculaHorario model = UniversidadSoapMapper.toModel(dto);
        if (model.getMatricula() != null) model.getMatricula().setId((long) idMatricula);
        if (model.getHorarioCurso() != null) model.getHorarioCurso().setId((long) idHorarioCurso);
        return bo.modificar(model);
    }

    @WebMethod(operationName = "eliminarMatriculaHorario")
    public int eliminarMatriculaHorario(@WebParam(name = "idMatricula") int idMatricula,
                                        @WebParam(name = "idHorarioCurso") int idHorarioCurso) {
        return bo.eliminar(idMatricula, idHorarioCurso);
    }
}
