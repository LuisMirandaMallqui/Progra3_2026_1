package pe.edu.pucp.conductor;

public class Conductor {
    private int conductorId;
    private String nombre;
    private String paterno;
    private String materno;
    private String numLicencia;
    private TipoLicencia tipoLicencia; // para pregunta1 lo trato como String para que sirva con inserts
    private int puntosAcumulados;

    public Conductor() {
    }

    public Conductor(int conductorId, String nombre, String paterno, String materno, String numLicencia, TipoLicencia tipoLicencia, int puntosAcumulados) {
        this.conductorId = conductorId;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.numLicencia = numLicencia;
        this.tipoLicencia = tipoLicencia;
        this.puntosAcumulados = puntosAcumulados;
    }

    public int getConductorId() {
        return conductorId;
    }

    public void setConductorId(int conductorId) {
        this.conductorId = conductorId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombres(String nombre) {
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

    public TipoLicencia getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(TipoLicencia tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void setPuntosAcumulados(int puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }
}
