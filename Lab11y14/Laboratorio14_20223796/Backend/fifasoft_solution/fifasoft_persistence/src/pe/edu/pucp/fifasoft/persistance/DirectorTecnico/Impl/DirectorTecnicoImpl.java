package pe.edu.pucp.fifasoft.persistance.DirectorTecnico.Impl;

import pe.edu.pucp.fifasoft.config.DBManager;
import pe.edu.pucp.fifasoft.model.DirectorTecnico;
import pe.edu.pucp.fifasoft.persistance.DirectorTecnico.DAO.DirectorTecnicoDAO;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectorTecnicoImpl implements DirectorTecnicoDAO {


    @Override
    public int insertar(DirectorTecnico objeto) {
        return 0;
    }

    @Override
    public int modificar(DirectorTecnico objeto) {
        return 0;
    }

    @Override
    public int eliminar(int id) {
        return 0;
    }

    @Override
    public List<DirectorTecnico> listarTodos() {
        return List.of();
    }

    @Override
    public DirectorTecnico buscarPorId(int id) {
        DirectorTecnico dt = null;
        Map<Integer, Object> entrada = new HashMap<>();
        entrada.put(1, id);
        try (DBManager.ResultadoConsulta rc =
                     DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_DIRECTOR_TECNICO_X_ID", entrada)) {
            ResultSet rs = rc.getRs();
            if (rs.next()) dt = mapear(rs);     // 0 o 1 fila
        } catch (Exception ex) {
            System.out.println("Error al buscar director tecnico por id: " + ex.getMessage());
        }
        return dt;
    }


    private DirectorTecnico mapear(ResultSet rs) throws Exception {
        DirectorTecnico dt = new DirectorTecnico();
        dt.setIdDirectorTecnico(rs.getInt("id_director_tecnico"));
        dt.setNombre(rs.getString("nombre"));
        dt.setNacionalidad(rs.getString("nacionalidad"));
        dt.setEdad(rs.getInt("edad"));
        return dt;
    }
}
