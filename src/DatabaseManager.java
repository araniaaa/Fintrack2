import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private String url;
    private String username;
    private String password;

    public DatabaseManager(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public List<Expense> getExpenses() throws SQLException {
        List<Expense> expenses = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM expenses")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String category = rs.getString("category");
                Date date = rs.getDate("date");
                double amount = rs.getDouble("amount");
                expenses.add(new Expense(id, category, date, amount));
            }
        }

        return expenses;
    }

    public void addExpense(Expense expense) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO expenses (category, date, amount) VALUES (?, ?, ?)")) {
            stmt.setString(1, expense.getCategory());
        }
    }
}

