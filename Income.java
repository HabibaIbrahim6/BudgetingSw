public class Income {
    private String source;
    private double amount;
    private String date;

    public Income(String source, double amount, String date) {
        this.source = source;
        this.amount = amount;
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return source + "," + amount + "," + date;
    }
    
}
