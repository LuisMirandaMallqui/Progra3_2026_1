package pe.edu.pucp.testsoft.model.pregunta;

public class Pregunta {
//    id int auto_increment primary key,
//    enunciado varchar(300) not null
    private int id;
    private String enunciado;

    public Pregunta() {
    }

    public Pregunta(int id, String enunciado) {
        this.id = id;
        this.enunciado = enunciado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }
}
