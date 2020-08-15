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

    public void menuProvider() throws IOException {

        boolean isRun = true;
        while (isRun) {
            menuPrinter.printUserMenu();
            int userChoice = inputProvider.getNumberFromUser("Enter option: ");
            switch (userChoice) {
                case 1:
                    mentorService.initializeMentorsFullNames();
                    break;
                case 2:
                    mentorService.initializeMentorsNickName(inputProvider);
                    break;
                case 3:
                    applicantService.initializeApplicantsFullName(inputProvider);
                    break;
                case 4:
                    applicantService.initializeApplicantByEmailEnding(inputProvider);
                    break;
                case 5:
                    applicantService.initializeApplicantByApplicationCode(inputProvider);
                    break;
                case 6:
                    applicantService.initializeGetApplicantByFullName(inputProvider);
                    break;
                case 7:
                    applicantService.initializeGetApplicantByDomain(inputProvider);
                    break;
                case 8:
                    applicantService.initializeDeleteApplicantByDomain(inputProvider);
                    break;
                case 9:
                    applicantService.initializeAddApplicant(inputProvider);
                    break;
                case 10:
                    applicantService.initializeGetAllApplicants(inputProvider);
                    break;
                case 11:
                    mentorService.initializeSearchInMentorsByPhrase(inputProvider);
                    applicantService.initializeSearchInApplicantsByPhrase(inputProvider);
                    break;
                case 12:
                    applicantMenuProvider();
                    break;
                case 0:
                    isRun = false;
                default:
                    break;
            }
        }
    }

    private void applicantMenuProvider() throws IOException {
        applicantService.initializeGetAllApplicants(inputProvider);
        List<Applicant> applicants = applicantService.initializeApplicantById(inputProvider);
        int applicantId = applicants.get(0).getIdUser();
        boolean isRun = true;
        while (isRun) {
            menuPrinter.printApplicantMenu();
            int userChoice = inputProvider.getNumberFromUser("Enter option: ");
            switch (userChoice) {
                case 1:
                    changeFirstName(applicantId);
                    break;
                case 2:
                    changeLastName(applicantId);
                    break;
                case 3:
                    changePhoneNumber(applicantId);
                    break;
                case 4:
                    changeEmail(applicantId);
                    break;
                case 5:
                    changeApplicationCode(applicantId);
                    break;
                case 0:
                    isRun = false;
                default:
                    break;
            }
        }
    }

    private void changeApplicationCode(int applicantId) {
        int newApplicationCode = inputProvider.getNumberFromUser("Enter a new application code: ");
        applicantService.initializeUpdateApplicant(applicantId, "application_code", Integer.toString(newApplicationCode));
        applicantService.initializeApplicantPrinting(applicantService.getApplicantById(applicantId));
    }

    private void changeEmail(int applicantId) throws IOException {
        String newEmail = inputProvider.takeStringInput("Enter a new email: ");
        applicantService.initializeUpdateApplicant(applicantId, "email", newEmail);
        applicantService.initializeApplicantPrinting(applicantService.getApplicantById(applicantId));
    }

    private void changePhoneNumber(int applicantId) throws IOException {
        String newPhoneNumber = inputProvider.takeStringInput("Enter a new phone number: ");
        applicantService.initializeUpdateApplicant(applicantId, "phone_number", newPhoneNumber);
        applicantService.initializeApplicantPrinting(applicantService.getApplicantById(applicantId));
    }

    private void changeLastName(int applicantId) throws IOException {
        String newLastName = inputProvider.takeStringInput("Enter a new last name: ");
        applicantService.initializeUpdateApplicant(applicantId, "last_name", newLastName);
        applicantService.initializeApplicantPrinting(applicantService.getApplicantById(applicantId));
    }

    private void changeFirstName(int applicantId) throws IOException {
        String newName = inputProvider.takeStringInput("Enter a new name: ");
        applicantService.initializeUpdateApplicant(applicantId, "first_name", newName);
        applicantService.initializeApplicantPrinting(applicantService.getApplicantById(applicantId));
    }


}
