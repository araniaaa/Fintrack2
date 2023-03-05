import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class ExpensesFile {
    private String filename;
    public ExpensesFile(String filename){
        this.filename = filename;
    }

    public List<Expense> readExpenses() throws IOException, ClassNotFoundException {
        List<Expense> expenses = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            expenses = (List<Expense>) ois.readObject();
        }catch (FileNotFoundException e) {
            //file not found, create a new one
            saveExpenses(expenses);
        }
        return expenses;
    }

    public void saveExpenses(List<Expense> expenses) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(expenses);
        }
    }
}
