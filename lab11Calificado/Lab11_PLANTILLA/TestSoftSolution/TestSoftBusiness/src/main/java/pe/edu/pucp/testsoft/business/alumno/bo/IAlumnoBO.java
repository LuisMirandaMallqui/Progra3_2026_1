package pe.edu.pucp.testsoft.business.alumno.bo;

import pe.edu.pucp.testsoft.business.bo.IBaseBO;
import pe.edu.pucp.testsoft.model.Alumno;
import java.util.List;

public interface IAlumnoBO extends IBaseBO<Alumno> {
    List<Alumno> buscarPorNombreApellido(String texto) throws Exception;
}
