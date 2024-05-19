package carrentalsystem.ui.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.List;


import carrentalsystem.dao.UserDAO;
import carrentalsystem.model.User;

public class AdminCustMainUI extends JPanel {
    private JButton  viewButton, deleteButton, historyButton;
    private JTable customerTable;
    private JScrollPane scrollPane;

    public AdminCustMainUI() {
        setLayout(new BorderLayout());
        initializeComponents();
        setupListeners();
        add(scrollPane, BorderLayout.CENTER);
    }

    private void initializeComponents() {
        // Button panel
        deleteButton = new JButton("Delete Customer");
        historyButton = new JButton("View Booking History");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(deleteButton);
        buttonPanel.add(historyButton);

        // Table setup
        List<User> users = UserDAO.loadUsers();
        DefaultTableModel model = createTableModel(users);
        customerTable = new JTable(model);
        customerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane = new JScrollPane(customerTable);

        // Adding components
        add(buttonPanel, BorderLayout.NORTH);
    }

    private DefaultTableModel createTableModel(List<User> users) {
        String[] columnNames = {"Username", "Full Name", "Gender", "Date of Birth"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (User user : users) {
            model.addRow(new Object[]{
                user.getUsername(),
                user.getFullName(),
                user.getGender(),
                user.getDateOfBirth()
            });
        }
        return model;
    }

    private void setupListeners() {
        deleteButton.addActionListener(e -> deleteSelectedCustomer());
        // historyButton.addActionListener(e -> viewCustomerHistory());
    }




    private void deleteSelectedCustomer() {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow >= 0) {
            String username = (String) customerTable.getValueAt(selectedRow, 0);
            User customerToDelete = UserDAO.getUserByUsername(username); 
            
            //Confirm deletion, display all details
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this customer?\n" + 
                "Username: " + customerToDelete.getUsername() + "\n" +
                "Full Name: " + customerToDelete.getFullName() + "\n" +
                "This action cannot be undone.",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                UserDAO.deleteUser(customerToDelete.getUsername());
                JOptionPane.showMessageDialog(this, "Customer deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                refreshCustList();
        } 
    } else {
            JOptionPane.showMessageDialog(this, "No customer selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    
    }

    private void refreshCustList() {
        List<User> users = UserDAO.loadUsers();
        DefaultTableModel model = createTableModel(users);
        customerTable.setModel(model);
    }

    // TODO:private void viewCustomerHistory() {
    //     int selectedRow = customerTable.getSelectedRow();
    //     if (selectedRow >= 0) {
    //         int customerId = (Integer) customerTable.getValueAt(selectedRow, 0);
    //         // Assuming a method to fetch and display booking history
    //         String history = CustomerDAO.getBookingHistory(customerId);
    //         JOptionPane.showMessageDialog(this, "Booking History: " + history);
    //     } else {
    //         JOptionPane.showMessageDialog(this, "No customer selected.", "Error", JOptionPane.ERROR_MESSAGE);
    //     }
    // }
}
