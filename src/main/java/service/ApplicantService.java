package service;

import dao.IApplicantDAO;
import model.Applicant;
import view.PrintData;

import java.util.ArrayList;
import java.util.List;

public class ApplicantService {

    private final IApplicantDAO<Applicant> applicantDAO;
    private final PrintData printData;

    public ApplicantService(IApplicantDAO<Applicant> applicantDAO, PrintData printData) {
        this.applicantDAO = applicantDAO;
        this.printData = printData;
    }

    public void getFullNameByUserName(String value) {
        List<Applicant> applicants = applicantDAO.getApplicantFullNamePhoneNumberByName(value);
        List<String> toPrint = new ArrayList<>();
        for (Applicant applicant : applicants) {
            toPrint.add(applicant.getFirstName() + " " + applicant.getLastName() + ", " + applicant.getPhoneNumber());
        }
        printData.printStringList(toPrint);
    }

    public void getFullNameByUserEmail(String value) {
        List<Applicant> applicants = applicantDAO.getApplicantFullNamePhoneNumberByEmailEnding(value);
        List<String> toPrint = new ArrayList<>();
        for (Applicant applicant : applicants) {
            toPrint.add(applicant.getFirstName() + " " + applicant.getLastName() + ", " + applicant.getPhoneNumber());
        }
        printData.printStringList(toPrint);
    }

    public List<Applicant> getApplicantByCode(int userCode) {
        return applicantDAO.getApplicantDataByCode(userCode);
    }

    public List<Applicant> getApplicantByFullName(String[] fullName) {
        return applicantDAO.getApplicantDataByFullName(fullName);
    }

    public void initializeApplicantPrinting(List<Applicant> applicants){
        List<String> toPrint = new ArrayList<>();
        for (Applicant applicant : applicants) {
            toPrint.add(applicant.getIdUser() + ", " + applicant.getFirstName() + " " + applicant.getLastName() + ", " + applicant.getPhoneNumber()
                    + ", " + applicant.getEmail() + ", " + applicant.getApplicationCode());
        }
        printData.printStringList(toPrint);
    }

    public void updatePhoneByFullName(List<Applicant> applicantListToUpdate, String[] elementsToUpdate) {
        Applicant applicantToUpdate = applicantListToUpdate.get(0);
        applicantDAO.updatePhoneByFullName(applicantToUpdate, elementsToUpdate);
    }

    public void deleteByDomain(String domain) {
        applicantDAO.deleteByDomain(domain);
    }

    public void addApplicantToDb(Applicant applicant) {
        applicantDAO.addApplicant(applicant);
    }

    public List<Applicant> getAllApplicantsData() {
        return applicantDAO.getAllUsers();
    }
}
