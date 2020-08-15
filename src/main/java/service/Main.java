package service;

import controller.MenuController;
import dao.ApplicantDbDAO;
import dao.IApplicantDAO;
import dao.IMentorDAO;
import dao.MentorDbDAO;
import model.Applicant;
import model.Mentor;
import view.PrintData;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        PrintData printData = new PrintData();
        IMentorDAO<Mentor> mentorDbDAO = new MentorDbDAO();
        IApplicantDAO<Applicant> applicantDbDAO = new ApplicantDbDAO();
////        IMentorDAO<Mentor> mentorDAO = new MentorCsvDAO();
        MentorService mentorService = new MentorService(mentorDbDAO, printData);
        ApplicantService applicantService = new ApplicantService(applicantDbDAO, printData);
        MenuController menuController = new MenuController(mentorService, applicantService);
        menuController.menuProvider();
    }
}
