package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputProvider {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

//    public int takeIntegerInput(String messageForUser) throws IOException {
//        System.out.print(messageForUser);
//        return Integer.parseInt(reader.readLine());
//    }

    public String takeStringInput(String messageForUser) throws IOException {
        String input = "";
        boolean validInput = false;
        while(!validInput) {
            System.out.print(messageForUser);
            input = reader.readLine();
            if (input.replaceAll("\\s+", "").length() > 0) {
                validInput = true;
            }
        }
        return input;
    }

    private String readNextLine(BufferedReader in) throws IOException{
        return in.readLine();
    }

    private int convertStringToInt(String valueString) throws NumberFormatException {
        return Integer.parseInt(valueString);
    }
    public int getNumberFromUser(String title) {
        while (true) { // we end the loop by a return, not by a condition.
            System.out.println(title);
            try {
                String valueString = readNextLine(reader);
                // return leaves the method and therefor the loop too.
                return convertStringToInt(valueString);
                // this method will not return
                // and the loop will not end if convertStringToInt() throws an
                // exception!
            } catch (IOException ex) {
                System.err.println("could not acquire next line from system input: " + ex.getMessage());
            } catch (NumberFormatException ex) {
//                System.err.println("could not convert input string: " + ex.getMessage());
                System.err.println("Invalid input, please try again.");
            }
        }
    }
}
