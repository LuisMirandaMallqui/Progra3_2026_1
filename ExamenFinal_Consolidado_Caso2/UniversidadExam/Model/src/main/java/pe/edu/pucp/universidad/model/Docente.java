package pe.edu.pucp.universidad.model;

public class Docente {
    private Long id;
    private Departamento departamento;
    private String codigo;
    private String nombreCompleto;
    private String categoria;
    private String dedicacion;
    private boolean activo;

    public Docente() { }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getDedicacion() { return dedicacion; }
    public void setDedicacion(String dedicacion) { this.dedicacion = dedicacion; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

}