package pe.edu.pucp.universidad.soapservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;

import pe.edu.pucp.universidad.business.DepartamentoBO;
import pe.edu.pucp.universidad.business.implementsBO.DepartamentoImplementsBO;
import pe.edu.pucp.universidad.model.Departamento;
import pe.edu.pucp.universidad.soapservices.dto.DepartamentoDTO;
import pe.edu.pucp.universidad.soapservices.mapper.UniversidadSoapMapper;

import java.util.ArrayList;
import java.util.List;

@WebService(
        serviceName = "DepartamentoWS",
        targetNamespace = "http://soapservices.universidad.pucp.edu.pe/departamento"
)
@XmlSeeAlso({DepartamentoDTO.class})
public class DepartamentoWS {

    private DepartamentoBO bo;

    public DepartamentoWS() {
        bo = new DepartamentoImplementsBO();
    }

    @WebMethod(operationName = "pingDepartamento")
    public String pingDepartamento() {
        return "SOAP de departamentos funcionando";
    }

    @WebMethod(operationName = "listarDepartamentos")
    public DepartamentoDTO[] listarDepartamentos() {
        List<Departamento> lista = bo.listarTodos();
        List<DepartamentoDTO> respuesta = new ArrayList<>();
        if (lista != null) {
            for (Departamento item : lista) {
                respuesta.add(UniversidadSoapMapper.toDTO(item));
            }
        }
        return respuesta.toArray(new DepartamentoDTO[0]);
    }

    @WebMethod(operationName = "buscarDepartamentoPorId")
    public DepartamentoDTO buscarDepartamentoPorId(@WebParam(name = "id") int id) {
        return UniversidadSoapMapper.toDTO(bo.buscarPorId(id));
    }

    @WebMethod(operationName = "insertarDepartamento")
    public int insertarDepartamento(@WebParam(name = "departamento") DepartamentoDTO departamento) {
        Departamento model = UniversidadSoapMapper.toModel(departamento);
        return bo.insertar(model);
    }

    @WebMethod(operationName = "modificarDepartamento")
    public int modificarDepartamento(@WebParam(name = "id") int id,
                           @WebParam(name = "departamento") DepartamentoDTO departamento) {
        Departamento model = UniversidadSoapMapper.toModel(departamento);
        model.setId((long) id);
        return bo.modificar(model);
    }

    @WebMethod(operationName = "eliminarDepartamento")
    public int eliminarDepartamento(@WebParam(name = "id") int id) {
        return bo.eliminar(id);
    }
}
