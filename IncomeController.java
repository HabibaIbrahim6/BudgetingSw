import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class IncomeController {

    public void submitIncome(String source, double amount, String date) {
        if (!validateInput(source, amount, date)) {
            System.out.println("Invalid income data. Please try again.");
            return;
        }

        Income income = new Income(source, amount, date);
        saveIncomeToFile(income);
        System.out.println("Income saved successfully.");
    }

    private boolean validateInput(String source, double amount, String date) {
        return source != null && !source.trim().isEmpty()
                && amount > 0
                && date != null && !date.trim().isEmpty();
    }

    private void saveIncomeToFile(Income income) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("income.txt", true))) {
            writer.write(income.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving income to file: " + e.getMessage());
        }
    }

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
