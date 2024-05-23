package carrentalsystem.ui.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.List;

import carrentalsystem.dao.BookDAO;
import carrentalsystem.dao.UserDAO;
import carrentalsystem.model.Booking;
import carrentalsystem.model.User;

public class AdminCustMainUI extends JPanel {
    private JButton deleteButton, historyButton;
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
        historyButton.addActionListener(e -> viewCustomerHistory());
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

    private void viewCustomerHistory() {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow >= 0) {
            String username = (String) customerTable.getValueAt(selectedRow, 0);
            List<Booking> bookings = BookDAO.loadBookingsByUserId(username);

            if (bookings.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No booking history for the selected user.", "No History", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JTable bookingTable = new JTable();
                DefaultTableModel model = new DefaultTableModel();
                model.setColumnIdentifiers(new String[]{"Booking ID", "Car ID", "Start Date", "End Date", "Status", "Amount", "Payment Status"});

                for (Booking booking : bookings) {
                    model.addRow(new Object[]{booking.getBookingID(), booking.getCarID(), booking.getStartDate(), booking.getEndDate(), booking.getStatus(), booking.getAmount(), booking.getPaymentStatus()});
                }

                bookingTable.setModel(model);
                JScrollPane scrollPane = new JScrollPane(bookingTable);
                scrollPane.setPreferredSize(new Dimension(700, 150));

                // Display in a dialog
                JOptionPane.showMessageDialog(null, scrollPane, "Booking History for " + username, JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No customer selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
