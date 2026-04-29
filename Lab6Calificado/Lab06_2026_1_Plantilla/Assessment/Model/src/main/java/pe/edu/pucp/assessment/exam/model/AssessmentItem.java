package pe.edu.pucp.assessment.exam.model;

import pe.edu.pucp.assessment.question.model.Question;

public class AssessmentItem {

    private int idAssessmentItem;
    private int idAssessment; // FK
    private Question question;
    private double score;

    public AssessmentItem() {
    }

    public AssessmentItem(int idAssessmentItem, int idAssessment, Question question, double score) {
        this.idAssessmentItem = idAssessmentItem;
        this.idAssessment = idAssessment;
        this.question = question;
        this.score = score;
    }

    public AssessmentItem(Question question, double score) {
        this.question = question;
        this.score = score;
    }

    public int getIdAssessmentItem() {
        return idAssessmentItem;
    }

    public void setIdAssessmentItem(int idAssessmentItem) {
        this.idAssessmentItem = idAssessmentItem;
    }

    public int getIdAssessment() {
        return idAssessment;
    }

    public void setIdAssessment(int idAssessment) {
        this.idAssessment = idAssessment;
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
