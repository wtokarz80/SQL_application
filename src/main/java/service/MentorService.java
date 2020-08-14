package service;

import dao.IMentorDAO;
import model.Mentor;
import view.PrintData;

import java.util.*;

public class MentorService {

    private final IMentorDAO<Mentor> mentorDAO;
    private final PrintData printData;


    public MentorService(IMentorDAO<Mentor> mentorDAO, PrintData printData) {
        this.mentorDAO = mentorDAO;
        this.printData = printData;
    }

    public void getFirstNameLastNameAllMentors() {
        List<Mentor> mentors = mentorDAO.getMentorFirstLastNames();
        List<String> toPrint = new ArrayList<>();
        for (Mentor mentor : mentors){
            toPrint.add(mentor.getFirstName() + ", " + mentor.getLastName());
        }
        printData.printStringList(toPrint);
    }

    public void displayMentorNickNames(String city) {
        List<Mentor> mentors = this.mentorDAO.getMentorNicknamesByCity(city);
        List<String> toPrint = new ArrayList<>();
        for (Mentor mentor : mentors){
            toPrint.add(mentor.getNickName());
        }
        printData.printStringList(toPrint);
    }
}
