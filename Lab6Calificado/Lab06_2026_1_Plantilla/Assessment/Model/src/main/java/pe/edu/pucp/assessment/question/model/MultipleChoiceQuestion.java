package pe.edu.pucp.assessment.question.model;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestion extends Question {
    private List<String> availableOptions;
    private List<Integer> correctOptions;

    public MultipleChoiceQuestion() {
        this.availableOptions = new ArrayList<String>();
        this.correctOptions = new ArrayList<Integer>();
    }

    public MultipleChoiceQuestion(int code, String prompt, List<String> availableOptions, List<Integer> correctOptions) {
        super(code, prompt);
        this.availableOptions = availableOptions;
        this.correctOptions = correctOptions;
    }

    public void setAvailableOptions(List<String> availableOptions) {
        this.availableOptions = availableOptions;
    }

    public List<Integer> getCorrectOptions() {
        return correctOptions;
    }

    public void setCorrectOptions(List<Integer> correctOptions) {
        this.correctOptions = correctOptions;
    }

    public List<String> getAvailableOptions() {
        return availableOptions;
    }

    public String devolverDatos() {
        String toReturn = prompt + "\n";
        for (int i = 0; i < availableOptions.size(); i++) {
            toReturn += (i + 1) + ". " + availableOptions.get(i) + "\n";
        }
        toReturn += "Seleccione las opciones de su respuesta: ";
        return toReturn;
    }
}
