package carrentalsystem.ui.admin;

import javax.swing.*;

import carrentalsystem.ui.common.LoginUI;

import java.awt.*;
import java.awt.event.*;

public class AdminMainUI extends JFrame implements ActionListener {

    // Sidebar buttons
    private JButton carButton, bookingButton, customerButton, reportButton, adminButton, logoutButton;
    private JPanel mainContent;


    public AdminMainUI() {

        setTitle("Admin Dashboard");
        setSize(1300, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(Color.DARK_GRAY);

        // Initialize buttons and add them to the sidebar
        carButton = createSidebarButton("Car Management");
        bookingButton = createSidebarButton("Booking Management");
        customerButton = createSidebarButton("Customer Management");
        reportButton = createSidebarButton("Generate Reports");
        adminButton = createSidebarButton("Admin Management");
        logoutButton = createSidebarButton("Logout");

        sidebar.add(carButton);
        sidebar.add(bookingButton);
        sidebar.add(customerButton);
        sidebar.add(reportButton);
        sidebar.add(adminButton);
        sidebar.add(logoutButton);

        // Initialize main content panel
        mainContent = new JPanel(new BorderLayout());
        setMainPanel(new AdminCarMainUI()); 

        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(sidebar, BorderLayout.WEST);
        getContentPane().add(mainContent, BorderLayout.CENTER);

        setVisible(true);
    }

    // Helper method to create and style sidebar buttons
    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.WHITE);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == carButton) {
            setMainPanel(new AdminCarMainUI());
        } else if (source == bookingButton) {
            //TODO: setMainPanel(new AdminBookingMainUI());
        } else if (source == customerButton) {
            setMainPanel(new AdminCustMainUI()); 
        } else if (source == reportButton) {
            setMainPanel(new AdminReportMainUI());
        } else if (source == adminButton) {
            setMainPanel(new AdminAddMainUI()); 
        } else if (source == logoutButton) {
            LoginUI loginUI = new LoginUI();
            loginUI.setVisible(true);
            dispose();
        }
    }

    // Method to switch main panel contents
    private void setMainPanel(JPanel panel) {
        mainContent.removeAll();
        mainContent.add(panel);
        mainContent.revalidate();
        mainContent.repaint();
    }

    public static void main(String[] args) {
        new AdminMainUI();

    }
}
