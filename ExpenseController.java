import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExpenseController {
    private static final String EXPENSE_FILE = "expense.txt";
    private List<Expense> expenses = new ArrayList<>();

    public Expense addExpense(double amount, String category, Date date, String description) {
        String expenseId = "EXP-" + System.currentTimeMillis();
        Expense newExpense = new Expense(expenseId, amount, category, date, description);

        if (saveExpenseToFile(newExpense)) {
            expenses.add(newExpense);
            return newExpense;
        }
        return null;
    }

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