package view;

public class MenuPrinter {

    public void printUserMenu() {
        System.out.println("Menu:\n"

                + "1 - Display first name and last name of the mentors\n"
                + "2 - Display the nick_name-s of all mentors working at chosen city\n"
                + "3 - Display full name and phone number of the applicant by name\n"
                + "4 - Display full name and phone number of the applicant by e-mail address ending\n"
                + "5 - Display full data of the applicant by application code\n"
                + "6 - Update Applicant phone number by first name and last name\n"
                + "7 - Display full data of the applicant by first name and last name\n"
                + "8 - Remove all the applicants, who applied by email domain\n"
                + "9 - Add applicant\n"
                + "10 - Show all applicants data\n"
                + "11 - Search phrase in all database\n"
                + "12 - Update applicant\n"
                + "0 - Back to previous menu\n");
    }

}
