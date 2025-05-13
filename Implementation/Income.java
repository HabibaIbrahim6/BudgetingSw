package Implementation;
/**
 * Represents an income record with source, amount, and date.
 */
public class Income {
    private String source;
    private double amount;
    private String date;

    /**
     * Constructs an Income object.
     *
     * @param source The source of the income (e.g.., salary, freelance).
     * @param amount The amount of income.
     * @param date   The date the income was received.
     */
    public Income(String source, double amount, String date) {
        this.source = source;
        this.amount = amount;
        this.date = date;
    }

    /**
     * Returns the source of the income.
     *
     * @return The income source.
     */
    public String getSource() {
        return source;
    }

    /**
     * Returns the amount of income.
     *
     * @return The income amount.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Returns the date of the income.
     *
     * @return The income date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns a string representation of the income record in CSV format.
     *
     * @return A string in the format "source,amount,date".
     */
    @Override
    public String toString() {
        return source + "," + amount + "," + date;
    }
}
