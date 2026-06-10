package pe.edu.pucp.testsoft.model;

// Segunda entidad: muestra una relación (id_alumno) y campos fecha.
// fechas como String para evitar problemas de (de)serialización de Date en SOAP/JSON.
public class Examen {
    private int id;
    private int idAlumno;
    private String titulo;
    private String fechaCreacion;
    private String fechaResolucion;
    private String estado;       // PENDIENTE / RESUELTO
    private int nota;

    public Examen() { }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdAlumno() { return idAlumno; }
    public void setIdAlumno(int idAlumno) { this.idAlumno = idAlumno; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(String fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getFechaResolucion() { return fechaResolucion; }
    public void setFechaResolucion(String fechaResolucion) { this.fechaResolucion = fechaResolucion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getNota() { return nota; }
    public void setNota(int nota) { this.nota = nota; }
}
