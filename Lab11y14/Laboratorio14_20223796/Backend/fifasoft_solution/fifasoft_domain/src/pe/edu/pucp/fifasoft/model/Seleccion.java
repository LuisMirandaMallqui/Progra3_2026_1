package pe.edu.pucp.fifasoft.model;

public class Seleccion {

    private int idSeleccion;
    private DirectorTecnico directorTecnico;
    private String nombre;
    private String confederacion;
    private char grupo;
    private int rankingFifa;
    private String urlBandera;
    private boolean clasificado;

    public int getIdSeleccion() {
        return idSeleccion;
    }

    public void setIdSeleccion(int idSeleccion) {
        this.idSeleccion = idSeleccion;
    }

    public DirectorTecnico getDirectorTecnico() {
        return directorTecnico;
    }

    public void setDirectorTecnico(DirectorTecnico directorTecnico) {
        this.directorTecnico = directorTecnico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getConfederacion() {
        return confederacion;
    }

    public void setConfederacion(String confederacion) {
        this.confederacion = confederacion;
    }

    public char getGrupo() {
        return grupo;
    }

    public void setGrupo(char grupo) {
        this.grupo = grupo;
    }

    public int getRankingFifa() {
        return rankingFifa;
    }

    public void setRankingFifa(int rankingFifa) {
        this.rankingFifa = rankingFifa;
    }

    public String getUrlBandera() {
        return urlBandera;
    }

    public void setUrlBandera(String urlBandera) {
        this.urlBandera = urlBandera;
    }

    public boolean isClasificado() {
        return clasificado;
    }

    public void setClasificado(boolean clasificado) {
        this.clasificado = clasificado;
    }
}
