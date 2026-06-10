package pe.edu.pucp.testsoft.services.soap.academico;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import pe.edu.pucp.testsoft.business.examen.bo.IExamenBO;
import pe.edu.pucp.testsoft.business.examen.impl.ExamenBOImpl;
import pe.edu.pucp.testsoft.model.Examen;

import java.util.List;

@WebService(serviceName = "ExamenWS", targetNamespace = "http://services.testsoft.pucp.edu.pe/")
public class ExamenWS {

    private IExamenBO examenBO;

    public ExamenWS() {
        examenBO = new ExamenBOImpl();
    }

    @WebMethod(operationName = "listarExamenesPendientesPorAlumno")
    public List<Examen> listarExamenesPendientesPorAlumno(@WebParam(name = "idAlumno") int idAlumno) {
        List<Examen> examenes = null;
        try { examenes = examenBO.listarPendientesPorAlumno(idAlumno); }
        catch (Exception ex) { System.out.println(ex.getMessage()); }
        return examenes;
    }

    @WebMethod(operationName = "buscarExamenPorId")
    public Examen buscarExamenPorId(@WebParam(name = "idExamen") int idExamen) {
        Examen examen = null;
        try { examen = examenBO.buscarPorId(idExamen); }
        catch (Exception ex) { System.out.println(ex.getMessage()); }
        return examen;
    }

    @WebMethod(operationName = "actualizarResultadoExamen")
    public int actualizarResultadoExamen(@WebParam(name = "idExamen") int idExamen,
                                         @WebParam(name = "estado") String estado,
                                         @WebParam(name = "nota") int nota) {
        int resultado = 0;
        try { resultado = examenBO.actualizarResultado(idExamen, estado, nota); }
        catch (Exception ex) { System.out.println(ex.getMessage()); }
        return resultado;
    }
}
