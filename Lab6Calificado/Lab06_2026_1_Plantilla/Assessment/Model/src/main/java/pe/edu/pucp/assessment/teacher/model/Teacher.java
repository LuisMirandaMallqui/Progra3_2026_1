package pe.edu.pucp.assessment.teacher.model;

public class Teacher {
    private int idTeacher;
    private String pucpCode;
    private String firstName;
    private String lastName;

    public Teacher() {
    }

    public Teacher(int idTeacher, String pucpCode, String firstName, String lastName) {
        this.idTeacher = idTeacher;
        this.pucpCode = pucpCode;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // para el test de model
    public Teacher(String pucpCode, String firstName, String lastName) {
        this.pucpCode = pucpCode;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    public String getPucpCode() {
        return pucpCode;
    }

    public void setPucpCode(String pucpCode) {
        this.pucpCode = pucpCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
