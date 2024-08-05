import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();

        boolean continueInput = true;
        while (continueInput) {
            String firstName = SafeInput.getNonZeroLenString(scanner, "Enter first name");
            String lastName = SafeInput.getNonZeroLenString(scanner, "Enter last name");
            String idNumber = SafeInput.getRegExString(scanner, "Enter ID number (6 digits)", "\\d{6}");
            String email = SafeInput.getRegExString(scanner, "Enter email", "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
            int yearOfBirth = SafeInput.getInt(scanner, "Enter year of birth (4 digits): ");

            String record = String.format("%s, %s, %s, %s, %d", firstName, lastName, idNumber, email, yearOfBirth);
            records.add(record);

            continueInput = SafeInput.getYNConfirm(scanner, "Do you want to enter another record?");
        }

        String fileName = SafeInput.getNonZeroLenString(scanner, "Enter the file name to save (with .csv extension)");

        try (FileWriter writer = new FileWriter("src/" + fileName)) {
            for (String record : records) {
                writer.write(record + "\n");
            }
            System.out.println("Data saved successfully to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }
}
