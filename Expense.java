import java.util.Date;

/**
 * This class represents an expense.
 * It holds information about the expense such as ID, amount, category, date, and description.
 */
public class Expense {
    private String expenseId;
    private double amount;
    private String category;
    private Date date;
    private String description;

    /**
     * Constructs an Expense object with the given details.
     * @param expenseId the ID of the expense
     * @param amount the amount of the expense
     * @param category the category of the expense
     * @param date the date of the expense
     * @param description a description of the expense
     */
    public Expense(String expenseId, double amount, String category, Date date, String description) {
        this.expenseId = expenseId;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.description = description;
    }

    /**
     * Gets the expense ID.
     * @return the expense ID
     */
    public String getexpenseId() {
        return expenseId;
    }

    /**
     * Gets the amount of the expense.
     * @return the amount
     */
    public double getamount() {
        return amount;
    }

    /**
     * Gets the category of the expense.
     * @return the category
     */
    public String getcategory() {
        return category;
    }

    /**
     * Gets the date of the expense.
     * @return the date
     */
    public Date getdate() {
        return date;
    }

    /**
     * Gets the description of the expense.
     * @return the description
     */
    public String getdescription() {
        return description;
    }
}
