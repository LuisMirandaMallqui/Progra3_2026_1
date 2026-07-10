package pe.edu.pucp.fifasoft.persistance.DirectorTecnico.DAO;

import pe.edu.pucp.fifasoft.persistance.dao.IDAO;
import pe.edu.pucp.fifasoft.model.DirectorTecnico;


import java.util.List;

public interface DirectorTecnicoDAO extends IDAO<DirectorTecnico> {
    DirectorTecnico buscarPorId(int id);              // patrón SELECT INTO: devuelve id (0 si no existe)
}




