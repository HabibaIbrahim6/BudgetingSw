import java.io.*;
import java.util.*;

/**
 * The BudgetAnalyzer class analyzes income and expense data from files and provides budget insights.
 * It calculates total income and expenses, displays budget trends, and identifies surplus or deficit.
 */
public class BudgetAnalyzer {
    private static final String INCOME_FILE = "income.txt";
    private static final String EXPENSE_FILE = "expense.txt";

    /**
     * Analyzes the budget by calculating total income and expenses, and displays the surplus or deficit.
     */
    public void analyzeBudget() {
        double totalIncome = calculateTotal(INCOME_FILE);
        double totalExpense = calculateTotal(EXPENSE_FILE);

        System.out.println("\n===== Budget Analysis =====");
        System.out.printf("Total Income: %.2f\n", totalIncome);
        System.out.printf("Total Expense: %.2f\n", totalExpense);
        System.out.println("---------------------------");

        double balance = totalIncome - totalExpense;
        if (balance > 0) {
            System.out.printf("You have a surplus of: %.2f\n", balance);
        } else if (balance < 0) {
            System.out.printf(" You have a deficit of: %.2f\n", Math.abs(balance));
        } else {
            System.out.println("⚖️ Your income equals your expenses.");
        }
    }

    /**
     * Calculates the total amount from a given file, summing up the amounts listed in the file.
     * @param filename the name of the file containing the income or expense data
     * @return the total amount from the file
     */
    private double calculateTotal(String filename) {
        double total = 0.0;
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2) {
                        try {
                            total += Double.parseDouble(parts[1].trim());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number in " + filename + ": " + parts[1]);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("" + filename + " not found. Starting with zero.");
        }
        return total;
    }

    /**
     * Displays the budget trends by grouping income and expenses by date and printing them.
     */
    public void displayBudgetTrends() {
        Map<String, Double> incomeByDate = groupByDate(INCOME_FILE);
        Map<String, Double> expenseByDate = groupByDate(EXPENSE_FILE);

        System.out.println("\n===== Budget Trends =====");
        System.out.printf("%-15s %-15s %-15s\n", "Date", "Total Income", "Total Expense");
        System.out.println("----------------------------------------");

        Set<String> allDates = new TreeSet<>();
        allDates.addAll(incomeByDate.keySet());
        allDates.addAll(expenseByDate.keySet());

        for (String date : allDates) {
            System.out.printf("%-15s %-15.2f %-15.2f\n",
                date,
                incomeByDate.getOrDefault(date, 0.0),
                expenseByDate.getOrDefault(date, 0.0));
        }
    }

    /**
     * Groups the data from the given file by date and calculates the total amount for each date.
     * @param filename the name of the file containing the income or expense data
     * @return a map with dates as keys and the total amount for each date as values
     */
    private Map<String, Double> groupByDate(String filename) {
        Map<String, Double> result = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length >= 3) {
                        try {
                            String date = parts[2].trim();
                            double amount = Double.parseDouble(parts[1].trim());
                            result.put(date, result.getOrDefault(date, 0.0) + amount);
                        } catch (NumberFormatException e) {
                            System.out.println(" Invalid data in " + filename + ": " + line);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(" " + filename + " not found.");
        }
        return result;
    }
}
