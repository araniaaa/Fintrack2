import java.util.ArrayList;
import java.util.List;

public class Expenses {
    private List<Expense> expenseList;

    public Expenses() {
        expenseList = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        expenseList.add(expense);
    }

    public List<Expense> getExpenses() {
        return expenseList;
    }

    public void updateExpense(int index, Expense newExpense) {
        expenseList.set(index, newExpense);
    }

    public void removeExpense(int index) {
        expenseList.remove(index);
    }
}