package pe.edu.pucp.assessment.teacher.model;

public class Teacher {

    private String pucpCode;
    private String firstName;
    private String lastName;

    public Teacher() {
    }

    public Teacher(String pucpCode, String firstName, String lastName) {
        this.pucpCode = pucpCode;
        this.firstName = firstName;
        this.lastName = lastName;
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
