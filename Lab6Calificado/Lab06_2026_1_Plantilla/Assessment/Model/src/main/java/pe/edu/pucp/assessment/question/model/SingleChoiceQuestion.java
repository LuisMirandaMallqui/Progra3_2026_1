package pe.edu.pucp.assessment.question.model;

import java.util.ArrayList;
import java.util.List;

public class SingleChoiceQuestion extends Question {

    private List<String> availableOptions;
    private int correctOption;

    public SingleChoiceQuestion() {
        this.availableOptions = new ArrayList<String>();
    }

    public SingleChoiceQuestion(int code, String prompt, List<String> availableOptions, int correctOption) {
        super(code, prompt);
        this.availableOptions = availableOptions;
        this.correctOption = correctOption;
    }

    public void setAvailableOptions(List<String> availableOptions) {
        this.availableOptions = availableOptions;
    }

    public List<String> getAvailableOptions() {
        return availableOptions;
    }

    public void setCorrectOption(int correctOption) {
        this.correctOption = correctOption;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public String devolverDatos() {
        String toReturn = prompt + "\n";
        for (int i = 0; i < availableOptions.size(); i++) {
            toReturn += (i + 1) + ". " + availableOptions.get(i) + "\n";
        }
        toReturn += "Ingrese su respuesta:";
        return toReturn;
    }

}
