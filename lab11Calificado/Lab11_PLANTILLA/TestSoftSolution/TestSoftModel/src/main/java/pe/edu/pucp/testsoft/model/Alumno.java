package pe.edu.pucp.testsoft.model;

// DTO de dominio. Campos == columnas de la tabla `alumno` y == propiedades del Alumno.cs en C#.
// Sin lógica: solo datos. Constructor vacío OBLIGATORIO para que JAX-WS (SOAP) y JSON-B (REST)
// puedan instanciarlo por reflexión al (de)serializar.
public class Alumno {
    private int id;
    private String codigo;
    private String nombre;
    private String apellidos;
    private String correo;
    private String estado;   // CHAR(1) en BD ('A'/'I'). String, no char: serializa limpio en SOAP/JSON.

    public Alumno() { }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
