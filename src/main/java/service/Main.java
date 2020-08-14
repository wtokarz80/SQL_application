package service;

import controller.MenuController;
import dao.ApplicantDbDAO;
import dao.IMentorDAO;
import dao.MentorDbDAO;
import model.Mentor;
import view.PrintData;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ApplicantDbDAO applicantDbDAO = new ApplicantDbDAO();
        PrintData printData = new PrintData();
        IMentorDAO<Mentor> mentorDbDAO = new MentorDbDAO();
////        IMentorDAO<Mentor> mentorDAO = new MentorCsvDAO();
        MentorService mentorService = new MentorService(mentorDbDAO, printData);
        ApplicantService applicantService = new ApplicantService(applicantDbDAO, printData);

        MenuController menu = new MenuController(mentorService, applicantService);
        menu.menu();

    }
}
