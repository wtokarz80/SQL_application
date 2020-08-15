package service;

import dao.IApplicantDAO;
import model.Applicant;
import view.InputProvider;
import view.PrintData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApplicantService {

    private final IApplicantDAO<Applicant> applicantDAO;
    private final PrintData printData;

    public ApplicantService(IApplicantDAO<Applicant> applicantDAO, PrintData printData) {
        this.applicantDAO = applicantDAO;
        this.printData = printData;
    }

    public void initializeApplicantsFullName(InputProvider inputProvider) throws IOException {
        String userName = inputProvider.takeStringInput("Enter name of the applicant: ");
        List<Applicant> applicants = getFullNamesByApplicantName(userName);
        initializeApplicantsFullNamePrinting(applicants);
    }

    public void initializeApplicantByEmailEnding(InputProvider inputProvider) throws IOException {
        String userEmailEnding = "%" + inputProvider.takeStringInput("Enter email ending of the applicant: ");
        getFullNameByApplicantEmailEnding(userEmailEnding);
    }

    public List<Applicant> initializeApplicantByApplicationCode(InputProvider inputProvider){
        int userCode = inputProvider.getNumberFromUser("Enter application code: ");
        List<Applicant> applicantList = getApplicantByCode(userCode);
        initializeApplicantPrinting(applicantList);
        return applicantList;
    }

    public List<Applicant> initializeApplicantById(InputProvider inputProvider){
        int id = inputProvider.getNumberFromUser("Enter id of applicant: ");
        List<Applicant> applicantList = getApplicantById(id);
        initializeApplicantPrinting(applicantList);
        return applicantList;
    }

    public void initializeGetApplicantByFullName(InputProvider inputProvider) throws IOException {
        String firstName = inputProvider.takeStringInput("Enter first name of the applicant: ");
        String lastName = inputProvider.takeStringInput("Enter last name of the applicant: ");
        String newPhoneNumber = inputProvider.takeStringInput("Enter new phone number of the applicant: ");
        String[] fullNameToUpdatePhone = {firstName, lastName};
        String[] elementsToUpdate = {newPhoneNumber};
        List<Applicant> applicantListToUpdate = getApplicantByFullName(fullNameToUpdatePhone);
        updatePhoneByFullName(applicantListToUpdate, elementsToUpdate);
    }

    public void initializeGetApplicantByDomain(InputProvider inputProvider) throws IOException {
        String userFirstName = inputProvider.takeStringInput("Enter first name of the applicant: ");
        String userLastName = inputProvider.takeStringInput("Enter last name of the applicant: ");
        String[] fullName = {userFirstName, userLastName};
        List<Applicant> applicantList = getApplicantByFullName(fullName);
        initializeApplicantPrinting(applicantList);
    }

    public void initializeDeleteApplicantByDomain(InputProvider inputProvider) throws IOException {
        String userDomain = "%" + inputProvider.takeStringInput("Enter domain of the applicants: ");
        deleteApplicantByDomain(userDomain);
    }

    public void initializeAddApplicant(InputProvider inputProvider) throws IOException {
        String firstName = inputProvider.takeStringInput("Enter first name of the applicant: ");
        String lastName = inputProvider.takeStringInput("Enter last name of the applicant: ");
        String phoneNumber = inputProvider.takeStringInput("Enter phone number of the applicant: ");
        String email = inputProvider.takeStringInput("Enter email of the applicant: ");
        int code = inputProvider.getNumberFromUser("Enter application code of the applicant: ");
        Applicant applicant = new Applicant(firstName, lastName, phoneNumber, email, code);
        addApplicantToDb(applicant);
    }

    public void initializeGetAllApplicants(InputProvider inputProvider) {
        List<Applicant> allApplicantsData = getAllApplicantsData();
        initializeApplicantPrinting(allApplicantsData);
    }

    public void initializeSearchInApplicantsByPhrase(InputProvider inputProvider) throws IOException {
        String phrase = '%' + inputProvider.takeStringInput("Enter phrase: ") + '%';
        List<Applicant> allApplicantsData = getApplicantsDataByPhrase(phrase);
        initializeApplicantPrinting(allApplicantsData);
    }

    public List<Applicant> getFullNamesByApplicantName(String name) {
        return applicantDAO.getApplicantFullNamePhoneNumberByName(name);
    }

    public void getFullNameByApplicantEmailEnding(String value) {
        List<Applicant> applicants = applicantDAO.getApplicantFullNamePhoneNumberByEmailEnding(value);
        initializeApplicantsFullNamePrinting(applicants);
    }

    public List<Applicant> getApplicantByCode(int userCode) {
        return applicantDAO.getApplicantDataByCode(userCode);
    }
    public List<Applicant> getApplicantById(int id) {
        return applicantDAO.getApplicantDataById(id);
    }

    public List<Applicant> getApplicantByFullName(String[] fullName) {
        return applicantDAO.getApplicantDataByFullName(fullName);
    }

    public void updatePhoneByFullName(List<Applicant> applicantListToUpdate, String[] elementsToUpdate) {
        Applicant applicantToUpdate = applicantListToUpdate.get(0);
        applicantDAO.updatePhoneByFullName(applicantToUpdate, elementsToUpdate);
    }

    public void initializeUpdateApplicant(int applicationCode, String columnName, String newValue) {
        applicantDAO.updateApplicant(applicationCode, columnName, newValue);

    }

    public void deleteApplicantByDomain(String domain) {
        applicantDAO.deleteByDomain(domain);
    }

    public void addApplicantToDb(Applicant applicant) {
        applicantDAO.addApplicant(applicant);
    }

    public List<Applicant> getAllApplicantsData() {
        return applicantDAO.getAllUsers();
    }

    public List<Applicant> getApplicantsDataByPhrase(String phrase) {
        return this.applicantDAO.getRecordsByPhrase(phrase);
    }

    public void initializeApplicantPrinting(List<Applicant> applicants){
        List<String> toPrint = new ArrayList<>();
        for (Applicant applicant : applicants) {
            toPrint.add(applicant.getIdUser() + ", " + applicant.getFirstName() + " " + applicant.getLastName() + ", " + applicant.getPhoneNumber()
                    + ", " + applicant.getEmail() + ", " + applicant.getApplicationCode());
        }
        printData.printStringList(toPrint);
    }

    public void initializeApplicantsFullNamePrinting(List<Applicant> applicants){
        List<String> toPrint = new ArrayList<>();
        for (Applicant applicant : applicants) {
            toPrint.add(applicant.getFirstName() + " " + applicant.getLastName() + ", " + applicant.getPhoneNumber());
        }
        printData.printStringList(toPrint);
    }



}
