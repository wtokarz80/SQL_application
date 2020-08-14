package model;

public class Applicant extends User{

    int applicationCode;
    String fullName;
    public Applicant(int idUser, String firstName, String lastName, String phoneNumber, String email, int applicationCode) {
        super(idUser, firstName, lastName, phoneNumber, email);
        this.applicationCode = applicationCode;
         }

    public Applicant(){}

    public int getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(int applicationCode) {
        this.applicationCode = applicationCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
