package pe.edu.pucp.assessment.exam.model;

import pe.edu.pucp.assessment.question.model.Question;

public class AssessmentItem {

    private Question question;
    private double score;

    public AssessmentItem() {
    }

    public AssessmentItem(Question question, double score) {
        this.question = question;
        this.score = score;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
