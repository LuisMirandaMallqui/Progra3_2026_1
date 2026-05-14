package pe.edu.pucp.Conductor;

public class Conductor {
    private int id;
    private String nombre;
    private String paterno;
    private String materno;
    private String numLicencia;
    private String tipoLicencia; // para pregunta1 lo trato como String para que sirva con inserts
    private int puntosAcumulados;

    public Conductor() {
    }

    public Conductor(int id, String nombre, String paterno, String materno, String numLicencia, String tipoLicencia, int puntosAcumulados) {
        this.id = id;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.numLicencia = numLicencia;
        this.tipoLicencia = tipoLicencia;
        this.puntosAcumulados = puntosAcumulados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getNumLicencia() {
        return numLicencia;
    }

    public void setNumLicencia(String numLicencia) {
        this.numLicencia = numLicencia;
    }

    public String getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(String tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void setPuntosAcumulados(int puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }
}
