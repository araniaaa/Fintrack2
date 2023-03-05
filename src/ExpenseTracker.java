import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class ExpenseTracker {
    private JPanel frame;
    private JTable table;
    private JTextField startDateField;
    private JTextField endDateField;
    private JButton dateFilterButton;
    private JComboBox categoryFilterCombo;
    private JLabel toLabel;
    private JLabel dateFilterPanel;
    private JLabel categoryFilterPanel;
    private final DatabaseManager dbManager;
    private final ExpensesFile expensesFile;
    private final Expenses expenses;

    public ExpenseTracker(DatabaseManager dbManager, ExpensesFile expensesFile) {
        this.dbManager = dbManager;
        this.expensesFile = expensesFile;
        this.expenses = new Expenses();
    }

    public void run() {
        //load expenses from file and database
        try {
            List<Expense> fileExpenses = expensesFile.readExpenses();
            expenses.getExpenses().addAll(fileExpenses);
            List<Expense> dbExpenses = dbManager.getExpenses();
            expenses.getExpenses().addAll(dbExpenses);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //create GUI
        JFrame frame = new JFrame("Expense Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        //category filter
        JPanel categoryFilterPanel = new JPanel();
        categoryFilterPanel.setLayout(new BoxLayout(categoryFilterPanel, BoxLayout.X_AXIS));
        categoryFilterPanel.setBorder(BorderFactory.createTitledBorder("Filter by Category:"));

        JComboBox<String> categoryFilterCombo = new JComboBox<>(new String[]{"All", "Food", "Transportation", "Entertainment", "Shopping", "Others"});
        categoryFilterCombo.setSelectedItem("All");
        categoryFilterCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable(categoryFilterCombo, startDateField, endDateField);
            }
        });
        categoryFilterPanel.add(categoryFilterCombo);

        //date filter
        JPanel dateFilterPanel = new JPanel();
        dateFilterPanel.setLayout(new BoxLayout(dateFilterPanel, BoxLayout.X_AXIS));
        dateFilterPanel.setBorder(BorderFactory.createTitledBorder("Filter by Date:"));

        JTextField startDateField = new JTextField();
        startDateField.setColumns(10);
        dateFilterPanel.add(startDateField);

        JLabel toLabel = new JLabel("to");
        dateFilterPanel.add(toLabel);

        JTextField endDateField = new JTextField();
        endDateField.setColumns(10);
        dateFilterPanel.add(endDateField);

        JButton dateFilterButton = new JButton("Filter");
        dateFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable(categoryFilterCombo, startDateField, endDateField);
            }
        });
        dateFilterPanel.add(dateFilterButton);

        //expense table
        String[] columnsNames = {"ID", "Category", "Date", "Amount"};
        DefaultTableModel model = new DefaultTableModel(columnsNames, 0);
        JTable table = new JTable(model);

        //add expenses to table
        for (Expense expense : expenses.getExpenses()) {
            model.addRow(new Object[]{expense.getId(), expense.getCategory(), expense.getDate(), expense.getAmount()});
        }

        //add components to frame
        frame.setLayout(new BorderLayout());
        frame.add(categoryFilterPanel, BorderLayout.NORTH);
        frame.add(dateFilterPanel, BorderLayout.CENTER);
        frame.add(new JScrollPane(table), BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void updateTable(JComboBox<String> categoryFilterCombo, JTextField startDateField, JTextField endDateField) {
        // clear table
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        // filter expenses by category and date
        String categoryFilter = (String) categoryFilterCombo.getSelectedItem();
    }
}