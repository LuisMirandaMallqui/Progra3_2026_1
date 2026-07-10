package pe.edu.pucp.universidad.model;

public class CursoPrerrequisito {
    private Curso curso;
    private Curso cursoPrerreq;
    private boolean activo;

    public CursoPrerrequisito() { }

    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }

    public Curso getCursoPrerreq() { return cursoPrerreq; }
    public void setCursoPrerreq(Curso cursoPrerreq) { this.cursoPrerreq = cursoPrerreq; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

}