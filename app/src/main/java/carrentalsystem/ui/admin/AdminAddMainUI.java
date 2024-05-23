package carrentalsystem.ui.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import carrentalsystem.model.User;
import carrentalsystem.dao.UserDAO;
import java.text.SimpleDateFormat;
import com.toedter.calendar.JDateChooser;
import java.util.Date;

public class AdminAddMainUI extends JPanel {
    private JTextField usernameField, fullNameField;
    private JPasswordField passwordField;
    private JRadioButton maleButton, femaleButton;
    private ButtonGroup genderGroup;
    private JButton addButton;
    private JDateChooser dobChooser;

    public AdminAddMainUI() {
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Username:"), gbc);
        usernameField = new JTextField(10); // Smaller width
        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Password:"), gbc);
        passwordField = new JPasswordField(10); // Smaller width
        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Full Name:"), gbc);
        fullNameField = new JTextField(10); // Smaller width
        gbc.gridx = 1;
        add(fullNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Gender:"), gbc);
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        gbc.gridx = 1;
        add(genderPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Date of Birth:"), gbc);
        dobChooser = new JDateChooser();
        dobChooser.setDateFormatString("dd-MM-yyyy");
        gbc.gridx = 1;
        add(dobChooser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        addButton = new JButton("Add Admin");
        add(addButton, gbc);

        addButton.addActionListener(this::addAdmin);
    }

    private void addAdmin(ActionEvent e) {
        String username = usernameField.getText().trim();
        String fullName = fullNameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String gender = maleButton.isSelected() ? "Male" : "Female";
        Date dob = dobChooser.getDate();
    
        if (username.isEmpty() || fullName.isEmpty() || password.isEmpty() || gender.isEmpty() || dob == null) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        if (password.length() < 8) {
            JOptionPane.showMessageDialog(this, "Password must be at least 8 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Check if the username already exists
        if (UserDAO.usernameExists(username)) {
            JOptionPane.showMessageDialog(this, "Username already exists. Please try another one.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = sdf.format(dob);
    
        User newUser = new User();
        newUser.setUsername(username.toUpperCase());
        newUser.setPassword(password);
        newUser.setFullName(fullName);
        newUser.setGender(gender);
        newUser.setDateOfBirth(formattedDate);
    
        UserDAO.saveAdmin(newUser);  
        JOptionPane.showMessageDialog(this, "Admin added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
  
