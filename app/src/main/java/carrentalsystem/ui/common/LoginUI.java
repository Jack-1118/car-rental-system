package carrentalsystem.ui.common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import carrentalsystem.service.UserService;

public class LoginUI extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginUI() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String role = UserService.login(username, password); 

                // direct user to respective main UI based on role
                switch (role) {
                    case "user":
                        new carrentalsystem.ui.user.UserMainUI().setVisible(true);
                        dispose();                      
                        break;
                    case "admin":
                        new carrentalsystem.ui.admin.AdminMainUI().setVisible(true);
                        dispose();
                        break;               
                    default:
                        JOptionPane.showMessageDialog(LoginUI.this, "Invalid username or password. Please try again.");
                        break;
                }

            }
        });

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginUI().setVisible(true);
            }
        });
    }
}
