package pe.edu.pucp.assessment.exam.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.edu.pucp.assessment.teacher.model.Teacher;

public class Assessment {

    private int durationInMinutes;
    private Date startDate;
    private List<Teacher> teachers;
    private List<AssessmentItem> assessmentItems;
    private double finalScore;

    public Assessment() {
        this.teachers = new ArrayList<Teacher>();
        this.assessmentItems = new ArrayList<AssessmentItem>();
    }

    public Assessment(int durationInMinutes, Date startDate, List<Teacher> teachers, List<AssessmentItem> assessmentItems) {
        this.durationInMinutes = durationInMinutes;
        this.startDate = startDate;
        this.teachers = teachers;
        this.assessmentItems = assessmentItems;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<AssessmentItem> getAssessmentItems() {
        return assessmentItems;
    }

    public void setAssessmentItems(List<AssessmentItem> assessmentItems) {
        this.assessmentItems = assessmentItems;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }

}
