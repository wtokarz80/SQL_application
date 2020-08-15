package dao;

import model.Mentor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MentorDbDAO extends PostgreSQLJDBC implements IMentorDAO<Mentor>{

    @Override
    public List<Mentor> getMentorFirstLastNames() {
        String query = "SELECT * FROM mentors;";
        return getMentorsList(query);
    }

    @Override
    public List<Mentor> getMentorNicknamesByCity(String city) {
        String query = String.format("SELECT * FROM mentors WHERE city='%s';", city);
        return getMentorsList(query);
    }

    @Override
    public List<Mentor> getRecordsByPhrase(String phrase) {
        String query = String.format("SELECT * FROM mentors WHERE first_name LIKE '%1$s' OR last_name LIKE '%1$s' OR nick_name LIKE '%1$s' " +
                "OR phone_number LIKE '%1$s' OR email LIKE '%1$s' OR city LIKE '%1$s';", phrase);
        return getMentorsList(query);
    }

    private List<Mentor> getMentorsList(String query) {
        List<Mentor> mentors = new ArrayList<>();
        try {
            connectToBD();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String nickName = rs.getString("nick_name");
                String phoneNumber = rs.getString("phone_number");
                String email = rs.getString("email");
                String city = rs.getString("city");
                int favouriteNumber = rs.getInt("favourite_number");
                Mentor mentor = new Mentor(id, firstName, lastName, nickName, phoneNumber, email, city, favouriteNumber);
                mentors.add(mentor);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mentors;
    }
}
