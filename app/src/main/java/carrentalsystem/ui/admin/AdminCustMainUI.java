package carrentalsystem.ui.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminCustMainUI extends JFrame implements ActionListener {
    private JButton viewListButton, addCustomerButton, editCustomerButton, deleteCustomerButton, viewActivityButton;

    public AdminCustMainUI() {
        // Set frame properties
        setTitle("Customer Management Dashboard");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on screen

        // Initialize buttons
        viewListButton = new JButton("View Customer List");
        addCustomerButton = new JButton("Add New Customer");
        editCustomerButton = new JButton("Edit Customer Information");
        deleteCustomerButton = new JButton("Delete Customer Profile");
        viewActivityButton = new JButton("View Customer Activity");

        // Add action listeners to buttons
        viewListButton.addActionListener(this);
        addCustomerButton.addActionListener(this);
        editCustomerButton.addActionListener(this);
        deleteCustomerButton.addActionListener(this);
        viewActivityButton.addActionListener(this);

        // Set layout and add buttons to the panel
        setLayout(new GridLayout(5, 1, 10, 10)); // 5 rows, 1 column, 10px horizontal and vertical gaps
        add(viewListButton);
        add(addCustomerButton);
        add(editCustomerButton);
        add(deleteCustomerButton);
        add(viewActivityButton);

        // Display the frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Placeholder for button click actions
        Object source = e.getSource();
        if (source == viewListButton) {
            JOptionPane.showMessageDialog(this, "Viewing Customer List...");
        } else if (source == addCustomerButton) {
            JOptionPane.showMessageDialog(this, "Adding a New Customer...");
        } else if (source == editCustomerButton) {
            JOptionPane.showMessageDialog(this, "Editing Customer Information...");
        } else if (source == deleteCustomerButton) {
            JOptionPane.showMessageDialog(this, "Deleting Customer Profile...");
        } else if (source == viewActivityButton) {
            JOptionPane.showMessageDialog(this, "Viewing Customer Activity...");
        }
    }

    public static void main(String[] args) {
        new AdminCustMainUI();
    }
}

