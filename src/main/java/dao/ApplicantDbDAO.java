package dao;

import model.Applicant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicantDbDAO extends PostgreSQLJDBC implements IApplicantDAO<Applicant> {


    @Override
    public List<Applicant> getApplicantFullNamePhoneNumberByName(String userName) {
        String query = String.format("SELECT * FROM applicants WHERE first_name='%s';",userName);
        return applicantsList(query);
    }

    @Override
    public List<Applicant> getApplicantDataByFullName(String[] fullName) {
        String query = String.format("SELECT * FROM applicants WHERE first_name='%s' AND last_name='%s';",fullName[0], fullName[1]);
        return applicantsList(query);
    }

    @Override
    public List<Applicant> getAllUsers() {
        String query = "SELECT * FROM applicants;";
        return applicantsList(query);
    }


    @Override
    public void updatePhoneByFullName(Applicant applicant, String[] params) {
        String query = String.format("UPDATE applicants SET phone_number='%s' WHERE first_name='%s' AND last_name='%s';",
                params[0], applicant.getFirstName(), applicant.getLastName());
        updateDB(query);
    }

    @Override
    public void deleteByDomain(String domain) {
        String query = String.format("DELETE FROM applicants WHERE email LIKE '%s';",domain);
        updateDB(query);
    }

    @Override
    public void addApplicant(Applicant applicant) {
        String query = String.format("INSERT INTO applicants (first_name, last_name, phone_number, email, application_code) " +
                "VALUES('%s', '%s', '%s', '%s', %d);",
                applicant.getFirstName(), applicant.getLastName(), applicant.getPhoneNumber(), applicant.getEmail(), applicant.getApplicationCode());
        updateDB(query);
    }


    @Override
    public List<Applicant> getApplicantDataByCode(int code) {
        String query = String.format("SELECT * FROM applicants WHERE application_code='%d';",code);
        return applicantsList(query);
    }


    @Override
    public List<Applicant> getApplicantFullNamePhoneNumberByEmailEnding(String emailEnding) {
        String query = String.format("SELECT * FROM applicants WHERE email LIKE '%s';",emailEnding);
        return applicantsList(query);
    }


    private List<Applicant> applicantsList(String query) {
        List<Applicant> applicantFullName = new ArrayList<>();
        try {
            connectToBD();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String phoneNumber = rs.getString("phone_number");
                String email = rs.getString("email");
                int applicationCode = rs.getInt("application_code");
                Applicant applicant = new Applicant(id, firstName, lastName, phoneNumber,email,applicationCode);
                applicant.setFullName(firstName + " " + lastName);
                applicantFullName.add(applicant);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applicantFullName;
    }

    private void updateDB(String query) {
        try {
            connectToBD();
            connection.setAutoCommit(false);
            statement.executeUpdate(query);
            statement.close();
            connection.commit();
            connection.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }


}
