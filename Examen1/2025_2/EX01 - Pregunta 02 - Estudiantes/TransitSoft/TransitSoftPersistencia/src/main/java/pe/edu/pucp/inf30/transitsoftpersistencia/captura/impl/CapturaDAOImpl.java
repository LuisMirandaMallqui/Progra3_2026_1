package pe.edu.pucp.inf30.transitsoftpersistencia.captura.impl;

import pe.edu.pucp.inf30.transitsoftdbmanager.DBManager;
import pe.edu.pucp.inf30.transitsoftpersistencia.captura.dao.CapturaDAO;
import pe.edu.pucp.transitsoft.modelo.Camara;
import pe.edu.pucp.transitsoft.modelo.Captura;
import pe.edu.pucp.transitsoft.modelo.EstadoCaptura;
import pe.edu.pucp.transitsoft.modelo.Vehiculo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CapturaDAOImpl implements CapturaDAO {
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;

    @Override
    public int insertar(Captura objeto) {
        return 0;
    }

    @Override
    public int modificar(Captura objeto) {
        return 0;
    }

    @Override
    public int eliminar(int id) {
        return 0;
    }

    @Override
    public Captura buscarPorId(int id) {
        return null;
    }

    @Override
    public List<Captura> listarTodos() {
        List<Captura> capturas = new ArrayList<>();
        try (DBManager.ResultadoConsulta resultado = DBManager.getInstance().ejecutarProcedimientoLectura("listarCapturas", null)) {
            ResultSet rs = resultado.getRs();
            while (rs.next()) {
                Captura captura = new Captura();
                captura.setId(rs.getInt("id"));
                captura.setPlaca(rs.getString("placa"));
                captura.setVelocidad(rs.getDouble("velocidad"));
                captura.setFechaCaptura(rs.getDate("fecha_captura"));
                captura.setEstado(EstadoCaptura.valueOf(rs.getString("estado").toUpperCase()));
                Camara camara = new Camara();
//                c.id_camara,
//                ca.modelo AS camara_modelo,
//                ca.codigo_serie AS camara_codigo_serie,
//                ca.latitud AS camara_latitud,
//                ca.longitud AS camara_longitud,
                camara.setId(rs.getInt("id_camara"));
                camara.setModelo(rs.getString("camara_modelo"));
                camara.setCodigoSerie(rs.getString("camara_codigo_serie"));
                camara.setLatitud(rs.getLong("camara_latitud"));
                camara.setLongitud(rs.getLong("camara_longitud"));
                captura.setCamara(camara);
                /*
        v.id AS id_vehiculo,
        v.placa AS vehiculo_placa,
        v.marca AS vehiculo_marca,
        v.modelo AS vehiculo_modelo,
        v.anho AS vehiculo_anho
                 */
                Vehiculo vehiculo = new Vehiculo();
                vehiculo.setId(rs.getInt("id_vehiculo"));
                vehiculo.setPlaca(rs.getString("vehiculo_placa"));
                vehiculo.setMarca(rs.getString("vehiculo_marca"));
                vehiculo.setModelo(rs.getString("vehiculo_modelo"));
                vehiculo.setAnho(rs.getInt("vehiculo_anho"));
                captura.setVehiculo(vehiculo);

                capturas.add(captura);
            }
        } catch (Exception ex) {
            System.out.println("ERROR al listar capturas: " + ex.getMessage());
        }

        return capturas;
    }

    public int actualizar(Captura captura){
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, captura.getEstado().name());
        parametrosEntrada.put(2, captura.getId());
        return DBManager.getInstance().ejecutarProcedimiento("modificarEstadoCaptura", parametrosEntrada, null);
    }
}
