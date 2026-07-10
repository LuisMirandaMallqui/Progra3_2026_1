package pe.edu.pucp.fifasoft.persistance.Seleccion.Impl;

import pe.edu.pucp.fifasoft.config.DBManager;
import pe.edu.pucp.fifasoft.model.DirectorTecnico;
import pe.edu.pucp.fifasoft.model.Seleccion;
import pe.edu.pucp.fifasoft.persistance.Seleccion.DAO.SeleccionDAO;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeleccionImpl implements SeleccionDAO {

    @Override
    public int insertar(Seleccion objeto) {
        return 0;
    }

    @Override
    public int modificar(Seleccion objeto) {
        return 0;
    }

    @Override
    public int eliminar(int id) {
        return 0;
    }

    @Override
    public Seleccion buscarPorId(int id) {
        return null;
    }

    @Override
    public List<Seleccion> listarTodos() {
        List<Seleccion> lista = new ArrayList<>();
        try (DBManager.ResultadoConsulta rc =
                     DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_SELECCIONES",null)) {
            ResultSet rs = rc.getRs();
            while (rs.next()) lista.add(mapear(rs));
        } catch (Exception ex) {
            System.out.println("Error al listar selecciones: " + ex.getMessage());
        }
        return lista;
    }



    private Seleccion mapear(ResultSet rs) throws Exception {
        Seleccion sl = new Seleccion();
        sl.setIdSeleccion(rs.getInt("id_seleccion"));
        DirectorTecnico dt = new DirectorTecnico();
        dt.setIdDirectorTecnico(rs.getInt("fid_director_tecnico"));
        sl.setDirectorTecnico(dt);
        sl.setNombre(rs.getString("nombre"));
        sl.setConfederacion(rs.getString("confederacion"));
        sl.setGrupo(rs.getString("grupo").charAt(0));
        sl.setRankingFifa(rs.getInt("ranking_fifa"));
        sl.setUrlBandera(rs.getString("url_bandera"));
        sl.setClasificado(rs.getBoolean("clasificado"));
        return sl;
    }
}
