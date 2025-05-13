package Implementation;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    private static User currentUser = null;
    private static Scanner scanner = new Scanner(System.in);
    private static ReminderController reminderController = new ReminderController();
    private static IncomeController incomeController = new IncomeController();
    private static ExpenseController expenseController = new ExpenseController(); 
    private static BudgetAnalyzer budgetAnalyzer = new BudgetAnalyzer();

    public static void main(String[] args) {
        while (true) {
            if (currentUser == null) {
                displayGuestMenu();
            } else {
                displayUserMenu();
            }
        }
    }

    private static void displayGuestMenu() {
        System.out.println("\n===== Main Menu =====");
        System.out.println("1. Sign Up");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        switch (choice) {
            case 1 -> handleSignUp();
            case 2 -> handleLogin();
            case 3 -> {
                System.out.println("Exiting...");
                System.exit(0);
            }
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void displayUserMenu() {
        System.out.println("\n===== User Menu =====");
        System.out.println("1. Manage Reminders");
        System.out.println("2. Manage Income");
        System.out.println("3. Manage Expenses");
        System.out.println("4. Budget Analysis");
        System.out.println("5. View Profile");
        System.out.println("6. Logout");
        System.out.print("Choose an option: ");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input. Please enter a number.");
            return;
        }

        switch (choice) {
            case 1 -> manageReminders();
            case 2 -> manageIncome();
            case 3 -> manageExpenses();
            case 4 -> analyzeBudget();
            case 5 -> viewProfile();
            case 6 -> {
                currentUser = null;
                System.out.println("Logged out successfully!");
            }
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void handleSignUp() {
        AuthenticationController.signUp();
        handleLogin();
    }

    private static void handleLogin() {
        currentUser = AuthenticationController.login();
        if (currentUser != null) {
            System.out.println("\nWelcome, " + currentUser.getUsername() + "!");
        }
    }

    private static void viewProfile() {
        System.out.println("\n===== Your Profile =====");
        System.out.println("Username: " + currentUser.getUsername());
        System.out.println("Email: " + currentUser.getEmail());
        System.out.println("Phone: " + currentUser.getPhoneNumber());
    }

    private static void manageReminders() {
       
    }

    private static void manageIncome() {
        while (true) {
            System.out.println("\n===== Income Management =====");
            System.out.println("1. Add New Income");
            System.out.println("2. View Income Summary");
            System.out.println("3. Back to User Menu");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> addIncome();
                case 2 -> incomeController.displayIncome();
                case 3 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void addIncome() {
        System.out.print("Enter income source: ");
        String source = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(" Invalid amount. Please enter a numeric value.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(new Date());

        incomeController.submitIncome(source, amount, date);
    }

    private static void analyzeBudget() {
        while (true) {
            System.out.println("\n===== Budget Analysis =====");
            System.out.println("1. View Budget Summary");
            System.out.println("2. View Budget Trends");
            System.out.println("3. Back to User Menu");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> budgetAnalyzer.analyzeBudget();
                case 2 -> budgetAnalyzer.displayBudgetTrends();
                case 3 -> {
                    return;
                }
                default -> System.out.println(" Invalid choice.");
            }
        }
    }

    private static void manageExpenses() {
        while (true) {
            System.out.println("\n===== Expense Management =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Back to User Menu");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input.");
                continue;
            }

            switch (choice) {
                case 1 -> addExpense();
                case 2 -> viewExpenses();
                case 3 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
    private static void addExpense() {
        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(" Invalid amount.");
            return;
        }

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        Date today = new Date();
        Expense expense = expenseController.addExpense(amount, category, today, description);

        if (expense != null) {
            System.out.println(" Expense added successfully.");
        } else {
            System.out.println(" Failed to add expense.");
        }
    }

    // ✅ عرض المصروفات
    private static void viewExpenses() {
        var expenses = expenseController.listExpenses();
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
        } else {
            for (Expense exp : expenses) {
                System.out.println(" ID: " + exp.getexpenseId() +
                        " | Category: " + exp.getcategory() +
                        " | Amount: " + exp.getamount() +
                        " | Date: " + exp.getdate());
            }
        }
    }
}
