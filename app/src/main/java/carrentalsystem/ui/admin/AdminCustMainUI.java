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
        viewButton = new JButton("View Customer");
        deleteButton = new JButton("Delete Customer");
        historyButton = new JButton("View Booking History");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(viewButton);
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
        viewButton.addActionListener(e -> showEditCustomerDialog());
        deleteButton.addActionListener(e -> deleteSelectedCustomer());
        // historyButton.addActionListener(e -> viewCustomerHistory());
    }



    private void showEditCustomerDialog() {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow >= 0) {
            String username = (String) customerTable.getValueAt(selectedRow, 0); // Assuming username is at column 0
            User customer = UserDAO.getUserByUsername(username); // Retrieve the customer details
    
            if (customer != null) {
                JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Edit Customer", Dialog.ModalityType.APPLICATION_MODAL);
                JPanel formPanel = new JPanel(new GridLayout(0, 2));
    
                // Creating text fields pre-filled with customer data
                JTextField usernameField = new JTextField(customer.getUsername(), 20);
                usernameField.setEditable(false); 
                JTextField fullNameField = new JTextField(customer.getFullName(), 20);
                fullNameField.setEditable(false);
                JTextField genderField = new JTextField(customer.getGender(), 20);
                genderField.setEditable(false);
                JTextField dobField = new JTextField(customer.getDateOfBirth(), 20);
                dobField.setEditable(false);
    
                // Add components to formPanel
                formPanel.add(new JLabel("Username:"));
                formPanel.add(usernameField);
                formPanel.add(new JLabel("Fullname:"));
                formPanel.add(fullNameField);
                formPanel.add(new JLabel("Gender:"));
                formPanel.add(genderField);
                formPanel.add(new JLabel("Date of Birth:"));
                formPanel.add(dobField);
    
                // Buttons for cancelling
                JButton cancelButton = new JButton("Cancel");
    
                cancelButton.addActionListener(e -> dialog.dispose());
    
                // Panel for holding the buttons
                JPanel buttonPanel = new JPanel();
                buttonPanel.add(cancelButton);
    
                // Arrange panels in the dialog
                dialog.setLayout(new BorderLayout());
                dialog.add(formPanel, BorderLayout.CENTER);
                dialog.add(buttonPanel, BorderLayout.PAGE_END);
    
                // Show the dialog
                dialog.pack();
                dialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor(this));
                dialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Selected customer could not be found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No customer selected. Please select a customer to view.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteSelectedCustomer() {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow >= 0) {
            String username = (String) customerTable.getValueAt(selectedRow, 0);
            UserDAO.deleteUser(username);
            JOptionPane.showMessageDialog(this, "Customer deleted successfully.");
            // Refresh the table
        } else {
            JOptionPane.showMessageDialog(this, "No customer selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // private void viewCustomerHistory() {
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
