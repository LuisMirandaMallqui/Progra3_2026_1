package pe.edu.pucp.universidad.business;

import pe.edu.pucp.universidad.business.base.BaseBO;
import pe.edu.pucp.universidad.model.Nota;

public interface NotaBO extends BaseBO<Nota> {
    double calcularPromedioFinal(int idMatricula, int idHorarioCurso);
}
