import java.util.Date;

public class Expense {
    private int id;
    private String category;
    private Date date;
    private double amount;

    public Expense(int id, String category, Date date, double amount) {
        this.id = id;
        this.category = category;
        this.date = date;
        this.amount = amount;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
