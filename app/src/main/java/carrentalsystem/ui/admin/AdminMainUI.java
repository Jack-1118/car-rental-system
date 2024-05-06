package carrentalsystem.ui.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class AdminMainUI extends JFrame implements ActionListener {
    // Sidebar buttons
    private JButton carButton, bookingButton, customerButton, reportButton, adminButton;

    public AdminMainUI() {
        // Set frame properties
        setTitle("Admin Dashboard");
        setSize(1300, 800); // Increased size for a more spacious layout
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on screen

        // Create the sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(Color.DARK_GRAY); // Set the background color of the sidebar

        // Initialize buttons and add them to the sidebar
        carButton = createSidebarButton("Car Management");
        bookingButton = createSidebarButton("Booking Management");
        customerButton = createSidebarButton("Customer Management");
        reportButton = createSidebarButton("Generate Reports");
        adminButton = createSidebarButton("Admin Management");

        // Add buttons to the sidebar
        sidebar.add(carButton);
        sidebar.add(bookingButton);
        sidebar.add(customerButton);
        sidebar.add(reportButton);
        sidebar.add(adminButton);

        // Main content panel, display Car Management by default
        JPanel mainContent = new AdminCustMainUI(); // Assume AdminCarMainUI is a JPanel
        mainContent.setBackground(Color.WHITE);

        // Set layout and add components
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(sidebar, BorderLayout.WEST);
        getContentPane().add(mainContent, BorderLayout.CENTER);

        // Display the frame
        setVisible(true);
    }

    // Helper method to create and style sidebar buttons
    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button text
        button.setBackground(Color.LIGHT_GRAY); // Set the button background
        button.setForeground(Color.WHITE); // Set the text color
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height)); // Make buttons expand to full width
        button.addActionListener(this);
        return button;
    }

    public void actionPerformed(ActionEvent e) {
        // Handle button clicks, similar to your original implementation
        // ...
    }

    public static void main(String[] args) {
        new AdminMainUI();
    }
}
