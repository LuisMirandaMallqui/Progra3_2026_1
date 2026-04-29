package pe.edu.pucp.assessment.question.model;

public abstract class Question implements Printable{
    private int code;
    protected String prompt;

    public Question() {
    }

    public Question(int code, String prompt) {
        this.prompt = prompt;
        this.code = code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }

	public abstract String devolverDatos();
}
