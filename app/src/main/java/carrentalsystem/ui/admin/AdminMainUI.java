package carrentalsystem.ui.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminMainUI extends JFrame implements ActionListener {
    private JButton carButton, bookingButton, customerButton, reportButton, adminButton;

    public AdminMainUI() {
        // Set frame properties
        setTitle("Admin Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on screen

        // Create buttons
        carButton = new JButton("Car Management");
        bookingButton = new JButton("Booking Management");
        customerButton = new JButton("Customer Management");
        reportButton = new JButton("Generate Reports");
        adminButton = new JButton("Admin Management");

        // Add action listeners to buttons
        carButton.addActionListener(this);
        bookingButton.addActionListener(this);
        customerButton.addActionListener(this);
        reportButton.addActionListener(this);
        adminButton.addActionListener(this);

        // Set layout
        setLayout(new GridLayout(5, 1));

        // Add buttons to the frame
        add(carButton);
        add(bookingButton);
        add(customerButton);
        add(reportButton);
        add(adminButton);

        // Display the frame
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Perform actions based on button clicks 
        switch (e.getActionCommand()) {
            case "Car Management":
                new AdminCarMainUI().setVisible(true);
                dispose();
                break;
            case "Booking Management":
                JOptionPane.showMessageDialog(this, "Open Booking Management Window");
                break;
            case "Customer Management":
                JOptionPane.showMessageDialog(this, "Open Customer Management Window");
                break;
            case "Generate Reports":
                JOptionPane.showMessageDialog(this, "Open Generate Reports Window");
                break;
            case "Admin Management":
                JOptionPane.showMessageDialog(this, "Open Admin Management Window");
                break;
        }
    }

    public static void main(String[] args) {
        new AdminMainUI();
    }
}

