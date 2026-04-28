package pe.com.citasvet.daoimpl;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import pe.com.citasvet.config.DBManager;
import pe.com.citasvet.dao.IPacienteDAO;
import pe.com.citasvet.modelo.Paciente;

public class PacienteDAOImpl extends BaseDAOImpl implements IPacienteDAO {
    private Paciente pacienteDto;

    @Override
    public int insertar(Paciente paciente) {
        int resultado = 0; // por defecto con error
        //Procedure:
        /*
        CREATE PROCEDURE insertarPaciente(
    	IN p_idTutor INT,
        IN p_nombre VARCHAR(100),
        IN p_especie VARCHAR(10),
        IN p_raza VARCHAR(50),
        IN p_edad INT,
        IN p_estado VARCHAR(50),
        OUT p_id INT)
        */
        // REFACTORIZADO
        try {
            this.IniciarTransaccion(); //getInstance y getConexion, seteando autocomit en false para la logica de transacciones
            callableStatement = this.conexion.prepareCall("{CALL insertarPaciente(?,?,?,?,?,?,?)}");
            callableStatement.registerOutParameter("p_id", Types.INTEGER);
            callableStatement.setInt("p_idTutor", paciente.getTutor().getId());
            callableStatement.setString("p_nombre", paciente.getNombre());
            callableStatement.setString("p_especie", paciente.getEspecie());
            callableStatement.setString("p_raza", paciente.getRaza());
            callableStatement.setInt("p_edad", paciente.getEdad());
            callableStatement.setString("p_estado", paciente.getEstado());
            resultado = callableStatement.executeUpdate();
            if (resultado == 0) {
                System.err.println("El registro no se inserto.");
                return resultado; // se devuelve el valor de error
            }
            int idGenerado = callableStatement.getInt("p_id");
            paciente.setId(idGenerado);
            this.comitarTransaccion();
            resultado = idGenerado;
            return resultado; // Se devuelve el id tras insertar
        } catch (SQLException ex) {
            System.err.println("Error al insertar paciente: " + ex.getMessage());
            try {
                this.rollbackTransaccion();//para cancelar la transaccion
            } catch (SQLException ex1) {
                System.err.println("Error en rollback: " + ex1.getMessage());
            }
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar conexión: " + ex.getMessage());
            }
        }
        return resultado;

        // caso base
        /*
        String sql = ""
                + "insert into paciente("
                + "idTutor, "
                + "nombre, "
                + "especie, "
                + "raza, "
                + "edad, "
                + "estado) "
                + "values"
                + "(?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, paciente.getTutor().getId());
            ps.setString(2, paciente.getNombre());
            ps.setString(3, paciente.getEspecie());
            ps.setString(4, paciente.getRaza());
            ps.setInt(5, paciente.getEdad());
            ps.setString(6, paciente.getEstado());

            if (ps.executeUpdate() == 0) {
                System.err.println("El registro no se inserto.");
                return 0;
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                return rs.next() ? rs.getInt(1) : -1;
            }
        } catch (SQLException e) {
            System.err.println("Error SQL durante la insercion: " + e.getMessage());
            throw new RuntimeException("No se pudo insertar el registro.", e);
        } catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al insertar el registro.", e);
        }
        */
    }

    @Override
    public boolean modificar(Paciente pacienteDto) {
        // Refactorizado
        int resultado = 0;
        try {
            this.IniciarTransaccion(); //getInstance y getConexion, seteando autocomit en false para la logica de transacciones
            callableStatement = this.conexion.prepareCall("{CALL modificarPaciente(?,?,?,?,?,?,?)}");
            callableStatement.setInt("p_id", pacienteDto.getId());
            callableStatement.setInt("p_idTutor", pacienteDto.getTutor().getId());
            callableStatement.setString("p_nombre", pacienteDto.getNombre());
            callableStatement.setString("p_especie", pacienteDto.getEspecie());
            callableStatement.setString("p_raza", pacienteDto.getRaza());
            callableStatement.setInt("p_edad", pacienteDto.getEdad());
            callableStatement.setString("p_estado", pacienteDto.getEstado());
            resultado = callableStatement.executeUpdate();
            if (resultado == 0) {
                System.err.println("El registro no se inserto.");
                return false; // se devuelve el valor de error
            }
            this.comitarTransaccion();
            return true; // Se devuelve true tras modificar de forma correcta
        } catch (SQLException e) {
            System.err.println("Error SQL durante la modificacion: " + e.getMessage());
            throw new RuntimeException("No se pudo modificar el registro.", e);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión - " + e);
            }
        }
        //  Original
        /*
        String sql = ""
                + "update paciente "
                + "set idTutor = ?, "
                + "nombre = ?, "
                + "especie = ?, "
                + "raza = ?, "
                + "edad = ?, "
                + "estado = ? "
                + "where id = ?";
        try (Connection conn = DBManager.getInstance().getConnection();
             PreparedStatement cmd = conn.prepareStatement(sql)) {

            cmd.setInt(1, paciente.getTutor().getId());
            cmd.setString(2, paciente.getNombre());
            cmd.setString(3, paciente.getEspecie());
            cmd.setString(4, paciente.getRaza());
            cmd.setInt(5, paciente.getEdad());
            cmd.setString(6, paciente.getEstado());
            cmd.setInt(7, paciente.getId());

            return cmd.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error SQL durante la modificacion: " + e.getMessage());
            throw new RuntimeException("No se pudo modificar el registro.", e);
        } catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al modificar el registro.", e);
        }
        */
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "delete from paciente where id = ?";
        try (Connection conn = DBManager.getInstance().getConnection();
             PreparedStatement cmd = conn.prepareStatement(sql)) {
            cmd.setInt(1, id);
            return cmd.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error SQL durante la eliminacion: " + e.getMessage());
            throw new RuntimeException("No se pudo eliminar el registro.", e);
        } catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al eliminar el registro.", e);
        }
    }

    @Override
    public Paciente buscar(int id) {
        // Refactorizado
        this.pacienteDto = new Paciente();
        this.pacienteDto.setId(id);
        try {
            this.IniciarTransaccion(); //getInstance y getConexion, seteando autocomit en false para la logica de transacciones
            callableStatement = this.conexion.prepareCall("{CALL buscarPacientePorId(?)}");
            callableStatement.setInt("p_id", pacienteDto.getId());
            this.resultSet = callableStatement.executeQuery();
            this.procesarResultSet(id);
            this.comitarTransaccion();
        } catch (SQLException e) {
            System.err.println("Error en buscar - " + e);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión - " + e);
            }
        }
        return pacienteDto;
        // Original
        /*
        String sql = "select * from paciente where id = ?";
        try (Connection conn = DBManager.getInstance().getConnection();
             PreparedStatement cmd = conn.prepareStatement(sql)) {

            cmd.setInt(1, id);

            try (ResultSet rs = cmd.executeQuery()) {
                if (!rs.next()) {
                    System.err.println("No se encontro el empleado con id: " + id);
                    return null;
                }

                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setTutor(new TutorDAOImpl().buscar(rs.getInt("idTutor")));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setEspecie(rs.getString("especie"));
                paciente.setRaza(rs.getString("raza"));
                paciente.setEdad(rs.getInt("edad"));
                paciente.setEstado(rs.getString("estado"));

                return paciente;
            }
        } catch (SQLException e) {
            System.err.println("Error SQL durante la busqueda: " + e.getMessage());
            throw new RuntimeException("No se pudo buscar el registro.", e);
        } catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al buscar el registro.", e);
        }
        */
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.pacienteDto = mapearPaciente();
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.pacienteDto = null;
    }

    private Paciente mapearPaciente() throws SQLException {
        Paciente paciente = new Paciente();
        paciente.setId(resultSet.getInt("id"));
        paciente.setTutor(new TutorDAOImpl().buscar(resultSet.getInt("idTutor")));
        paciente.setNombre(resultSet.getString("nombre"));
        paciente.setEspecie(resultSet.getString("especie"));
        paciente.setRaza(resultSet.getString("raza"));
        paciente.setEdad(resultSet.getInt("edad"));
        paciente.setEstado(resultSet.getString("estado"));
        return paciente;
    }

    @Override
    public List<Paciente> listar() { // ME FALTA ESTE
        String sql = "select * from paciente";
        try (Connection conn = DBManager.getInstance().getConnection();
             PreparedStatement cmd = conn.prepareStatement(sql);
             ResultSet rs = cmd.executeQuery()) {

            List<Paciente> pacientes = new ArrayList<>();
            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setTutor(new TutorDAOImpl().buscar(rs.getInt("idTutor")));
                paciente.setEspecie(rs.getString("especie"));
                paciente.setRaza(rs.getString("raza"));
                paciente.setEdad(rs.getInt("edad"));
                paciente.setEstado(rs.getString("estado"));
                pacientes.add(paciente);
            }

            return pacientes;
        } catch (SQLException e) {
            System.err.println("Error SQL durante el listado: " + e.getMessage());
            throw new RuntimeException("No se pudo listar el registro.", e);
        } catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al listar los registros.", e);
        }
    }
}
