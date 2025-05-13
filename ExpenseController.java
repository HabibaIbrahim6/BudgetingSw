import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Controller class to manage expense operations such as adding, saving, and listing expenses.
 */
public class ExpenseController {
    private static final String EXPENSE_FILE = "expense.txt";
    private List<Expense> expenses = new ArrayList<>();

    /**
     * Adds a new expense and saves it to the file.
     *
     * @param amount      The amount of the expense.
     * @param category    The category of the expense (e.g., food, transport).
     * @param date        The date of the expense.
     * @param description A short description of the expense.
     * @return The created Expense object if successfully saved, otherwise null.
     */
    public Expense addExpense(double amount, String category, Date date, String description) {
        String expenseId = "EXP-" + System.currentTimeMillis();
        Expense newExpense = new Expense(expenseId, amount, category, date, description);

        if (saveExpenseToFile(newExpense)) {
            expenses.add(newExpense);
            return newExpense;
        }
        return null;
    }

    /**
     * Saves an expense to the file in CSV format.
     *
     * @param expense The expense to save.
     * @return true if the expense was saved successfully, false otherwise.
     */
    private boolean saveExpenseToFile(Expense expense) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String expenseData = expense.getexpenseId() + "," +
                    expense.getamount() + "," +
                    expense.getcategory() + "," +
                    dateFormat.format(expense.getdate()) + "," +
                    expense.getdescription() + "\n";

            FileWriter fw = new FileWriter(EXPENSE_FILE, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(expenseData);
            bw.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error saving expense: " + e.getMessage());
            return false;
        }
    }

    /**
     * Loads all expenses from the file and returns them as a list.
     *
     * @return A list of all expenses stored in the file.
     */
    public List<Expense> listExpenses() {
        expenses.clear(); // Clear current list before reloading

        try {
            File file = new File(EXPENSE_FILE);
            if (!file.exists()) {
                return expenses;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String expenseId = parts[0];
                    double amount = Double.parseDouble(parts[1]);
                    String category = parts[2];
                    Date date = dateFormat.parse(parts[3]);
                    String description = parts[4];

                    expenses.add(new Expense(expenseId, amount, category, date, description));
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error reading expenses: " + e.getMessage());
        }

        return expenses;
    }
}
