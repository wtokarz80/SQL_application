package controller;

import model.Applicant;
import service.ApplicantService;
import service.MentorService;
import view.InputProvider;
import view.MenuPrinter;

import java.io.IOException;
import java.util.List;

public class MenuController {

    private final MentorService mentorService;
    private final ApplicantService applicantService;

    public MenuController(MentorService mentorService, ApplicantService applicantService) {
        this.mentorService = mentorService;
        this.applicantService = applicantService;
      }

    MenuPrinter menuPrinter = new MenuPrinter();
    InputProvider inputProvider = new InputProvider();

    public void menu() throws IOException {
        menuPrinter.printUserMenu();
        boolean isRun = true;
        while (isRun) {
            int userChoice = inputProvider.getNumberFromUser("Enter option: ");
            switch (userChoice) {
                case 1:
                    getMentorsFullNames();
                    break;
                case 2:
                    getMentorsNickName();
                    break;
                case 3:
                    getApplicantsFullName();
                    break;
                case 4:
                    getApplicantByEmailEnding();
                    break;
                case 5:
                    getApplicantByApplicationCode();
                    break;
                case 6:
                    initializeGetApplicantByFullName();
                    break;
                case 7:
                    initializeGetApplicantByDomain();
                    break;
                case 8:
                    initializeDeleteApplicantByDomain();
                    break;
                case 9:
                    initializeAddApplicant();
                    break;
                case 10:
                    initializeGetAllApplicants();
                    break;
                case 0:
                    isRun = false;
                default:
                    break;
            }
        }
    }

    private void initializeGetAllApplicants() {
        List<Applicant> allApplicantsData = applicantService.getAllApplicantsData();
        applicantService.initializeApplicantPrinting(allApplicantsData);
    }

    private void initializeAddApplicant() throws IOException {
        String applicantFirstName = inputProvider.takeStringInput("Enter first name of the applicant: ");
        String applicantLastName = inputProvider.takeStringInput("Enter last name of the applicant: ");
        String applicantPhoneNumber = inputProvider.takeStringInput("Enter phone number of the applicant: ");
        String applicantEmail = inputProvider.takeStringInput("Enter email of the applicant: ");
        int applicantCode = inputProvider.getNumberFromUser("Enter application code of the applicant: ");
        Applicant applicant = new Applicant();
        applicant.setFirstName(applicantFirstName);
        applicant.setLastName(applicantLastName);
        applicant.setPhoneNumber(applicantPhoneNumber);
        applicant.setEmail(applicantEmail);
        applicant.setApplicationCode(applicantCode);
        this.applicantService.addApplicantToDb(applicant);
    }

    private void initializeDeleteApplicantByDomain() throws IOException {
        String userDomain = "%" + inputProvider.takeStringInput("Enter domain of the applicants: ");
        applicantService.deleteByDomain(userDomain);
    }

    private void initializeGetApplicantByDomain() throws IOException {
        String userFirstName = inputProvider.takeStringInput("Enter first name of the applicant: ");
        String userLastName = inputProvider.takeStringInput("Enter last name of the applicant: ");
        String[] fullName = {userFirstName, userLastName};
        List<Applicant> applicantList = this.applicantService.getApplicantByFullName(fullName);
        this.applicantService.initializeApplicantPrinting(applicantList);
    }

    private void initializeGetApplicantByFullName() throws IOException {
        String firstName = inputProvider.takeStringInput("Enter first name of the applicant: ");
        String lastName = inputProvider.takeStringInput("Enter last name of the applicant: ");
        String newPhoneNumber = inputProvider.takeStringInput("Enter new phone number of the applicant: ");
        String[] fullNameToUpdatePhone = {firstName, lastName};
        String[] elementsToUpdate = {newPhoneNumber};
        List<Applicant> applicantListToUpdate = this.applicantService.getApplicantByFullName(fullNameToUpdatePhone);
        this.applicantService.updatePhoneByFullName(applicantListToUpdate, elementsToUpdate);
    }

    private void getApplicantByEmailEnding() throws IOException {
        String userEmailEnding = "%" + inputProvider.takeStringInput("Enter email ending of the applicant: ");
        this.applicantService.getFullNameByUserEmail(userEmailEnding);
    }

    private void getApplicantsFullName() throws IOException {
        String userName = inputProvider.takeStringInput("Enter name of the applicant: ");
        this.applicantService.getFullNameByUserName(userName);
    }

    private void getMentorsNickName() throws IOException {
        String city = inputProvider.takeStringInput("Enter name of the city: ");
        this.mentorService.displayMentorNickNames(city);
    }

    private void getMentorsFullNames() {
        this.mentorService.getFirstNameLastNameAllMentors();
    }

    private void getApplicantByApplicationCode(){
        int userCode = inputProvider.getNumberFromUser("Enter application code: ");
        List<Applicant> applicantList = this.applicantService.getApplicantByCode(userCode);
        this.applicantService.initializeApplicantPrinting(applicantList);
    }
}
