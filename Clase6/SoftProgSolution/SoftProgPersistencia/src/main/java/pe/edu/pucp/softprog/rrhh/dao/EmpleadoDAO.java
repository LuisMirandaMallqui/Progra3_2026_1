package pe.edu.pucp.softprog.rrhh.dao;

import pe.edu.pucp.softprog.dao.IDAO;
import pe.edu.pucp.softprog.rrhh.model.Area;
import pe.edu.pucp.softprog.rrhh.model.Empleado;

import java.util.List;

public interface EmpleadoDAO extends IDAO<Empleado> {
    List<Empleado> buscarPorDNI(String DNI);
}
