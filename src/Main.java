import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Main {
    public static void main(String[] args) {
        // Create an instance of the DatabaseManager class to manage database operations
        String url = "jdbc:mysql://localhost/phpmyadmin/index.php?route=/database/structure&db=mydatabase";
        String user = "root@localhost";
        String password = "password";
        DatabaseManager dbManager = new DatabaseManager(url, user, password);

        // Create an instance of the ExpensesFile class to read/write expenses to a file
        ExpensesFile expensesFile = new ExpensesFile("expenses.txt");

        // Create an instance of the ExpenseTracker class and run the app
        ExpenseTracker expenseTracker = new ExpenseTracker(dbManager, expensesFile);
        expenseTracker.run();
    }
}
