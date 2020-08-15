package service;

import dao.IMentorDAO;
import model.Applicant;
import model.Mentor;
import view.InputProvider;
import view.PrintData;

import java.io.IOException;
import java.util.*;

public class MentorService {

    private final IMentorDAO<Mentor> mentorDAO;
    private final PrintData printData;


    public MentorService(IMentorDAO<Mentor> mentorDAO, PrintData printData) {
        this.mentorDAO = mentorDAO;
        this.printData = printData;
    }

    public void initializeMentorsFullNames() {
        List<Mentor> mentors =  getFirstNameLastNameAllMentors();
        initializeMentorsFullNamePrinting(mentors);
    }

    public void initializeMentorsNickName(InputProvider inputProvider) throws IOException {
        String city = inputProvider.takeStringInput("Enter name of the city: ");
        List<Mentor> mentors = getMentorNickNames(city);
        for (Mentor mentor : mentors) {
            System.out.println(mentor.getNickName());
        }
//        initializeMentorsNickNamePrinting(mentors);
    }

    public void initializeSearchInMentorsByPhrase(InputProvider inputProvider) throws IOException {
        String phrase = '%' + inputProvider.takeStringInput("Enter phrase: ") + '%';
        List<Mentor> allMentorsData = getMentorsDataByPhrase(phrase);
        initializeMentorsPrinting(allMentorsData);
    }

    public List<Mentor> getFirstNameLastNameAllMentors() {
        return mentorDAO.getMentorFirstLastNames();
    }

    public List<Mentor> getMentorNickNames(String city) {
        return mentorDAO.getMentorNicknamesByCity(city);
    }

    public List<Mentor> getMentorsDataByPhrase(String phrase) {
        return this.mentorDAO.getRecordsByPhrase(phrase);
    }

    public void initializeMentorsFullNamePrinting(List<Mentor> mentors){
        List<String> toPrint = new ArrayList<>();
        for (Mentor mentor : mentors){
            toPrint.add(mentor.getFirstName() + ", " + mentor.getLastName());
        }
        printData.printStringList(toPrint);
    }

    public void initializeMentorsPrinting(List<Mentor> mentors){
        List<String> toPrint = new ArrayList<>();
        for (Mentor mentor : mentors) {
            toPrint.add(mentor.getIdUser() + ", " + mentor.getFirstName() + " " + mentor.getLastName() + ", " + mentor.getNickName()
                    + ", " + mentor.getPhoneNumber() + ", " + mentor.getEmail() + ", " + mentor.getCity() + ", " + mentor.getFavouriteNumber());
        }
        printData.printStringList(toPrint);
    }

    public void initializeMentorsNickNamePrinting(List<Mentor> mentors){
        List<String> toPrint = new ArrayList<>();
        for (Mentor mentor : mentors){
            toPrint.add(mentor.getNickName());
        }
        printData.printStringList(toPrint);
    }


}
