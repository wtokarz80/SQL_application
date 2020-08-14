package dao;

import model.Mentor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MentorDbDAO extends PostgreSQLJDBC implements IMentorDAO<Mentor>{

    @Override
    public List<Mentor> getMentorFirstLastNames() {
        String query = "SELECT first_name, last_name FROM mentors;";
        return getMentorsList(query);
    }

    @Override
    public List<Mentor> getMentorNicknamesByCity(String city) {
        String query = String.format("SELECT nick_name FROM mentors WHERE city='%s';", city);
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
