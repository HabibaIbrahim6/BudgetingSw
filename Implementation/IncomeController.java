package Implementation;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

/**
 * Controller class for managing income-related operations such as
 * submitting and displaying income records.
 */
public class IncomeController {

    /**
     * Submits a new income record after validating input,
     * then saves it to a file.
     *
     * @param source The source of the income (e.g., job, freelance).
     * @param amount The amount of income.
     * @param date   The date of the income.
     */
    public void submitIncome(String source, double amount, String date) {
        if (!validateInput(source, amount, date)) {
            System.out.println("Invalid income data. Please try again.");
            return;
        }

        Income income = new Income(source, amount, date);
        saveIncomeToFile(income);
        System.out.println("Income saved successfully.");
    }

    /**
     * Validates the user input for income submission.
     *
     * @param source The source of income.
     * @param amount The amount of income.
     * @param date   The date of income.
     * @return true if input is valid, false otherwise.
     */
    private boolean validateInput(String source, double amount, String date) {
        return source != null && !source.trim().isEmpty()
                && amount > 0
                && date != null && !date.trim().isEmpty();
    }

    /**
     * Saves the income record to a text file.
     *
     * @param income The income object to save.
     */
    private void saveIncomeToFile(Income income) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("income.txt", true))) {
            writer.write(income.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving income to file: " + e.getMessage());
        }
    }

    /**
     * Displays all saved income records from the file.
     */
    public void displayIncome() {
        try (Scanner scanner = new Scanner(new File("income.txt"))) {
            System.out.println("Income Records:");
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error reading income file: " + e.getMessage());
        }
    }
}
