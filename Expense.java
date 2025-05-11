// Expense.java
import java.util.Date;

public class Expense {
    private String expenseId;
    private double amount;
    private String category;
    private Date date;
    private String description;

    public Expense(String expenseId, double amount, String category, Date date, String description) {
        this.expenseId = expenseId;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.description = description;
    }

    // Getters
    public String getexpenseId() { return expenseId; }
    public double getamount() { return amount; }
    public String getcategory() { return category; }
    public Date getdate() { return date; }
    public String getdescription() { return description; }
}