package carrentalsystem.ui.user;



import carrentalsystem.dao.UserDAO;
import carrentalsystem.model.User;
import carrentalsystem.ui.BasePanel;

import java.util.List;
import javax.swing.JOptionPane;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
*/

/**
 *
 * @author theke
 */
public class UserProfileUI extends BasePanel {
    List<User> users;
    /**
     * Creates new form UserProfileUI
     */
    public UserProfileUI() {
        initComponents();
        renderUserProfile();
    }

    private void loadUserData() {
        users = UserDAO.loadUsers(); // Reload the user data from the database
    }
    

    private void renderUserProfile() {
        String Username = UserDAO.loadSessionData().get(0).getUsername();
        loadUserData();
        String Gender = "";
        
        for (User user : users){
            if (user.getUsername().equals(Username)) {
                UsernameField.setText(user.getUsername());
                NewPasswordField.setText(user.getPassword());
                NewFullNameField.setText(user.getFullName());
                Gender = user.getGender();
                if (Gender.equals("Male")) {
                    jRadioButton1.setSelected(true);
                } else {
                    jRadioButton2.setSelected(true);
                }
                DateOfBirthField.setText(user.getDateOfBirth());
            }
        }
    }

    private void updateChanges(String msg, String title){
        int response = JOptionPane.showConfirmDialog(null, msg, title,
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {  
            renderUserProfile();
        }
    }




    private void Reset(){
        updateChanges("Are you sure you want to reset?", "Reset Confirmation");

    }

    

   

    private void Savechanges(){
        String NewPassword = new String(NewPasswordField.getPassword()).trim();
        String NewFullName = NewFullNameField.getText().trim();
        String NewGender = jRadioButton1.isSelected() ? "Male" : (jRadioButton2.isSelected() ? "Female" : "");
         System.out.println(NewGender);
         System.out.println(NewPassword);
        System.out.println(NewFullName);
        
       // Validate the input values
       if ( NewFullName.isEmpty() || NewPassword.isEmpty() || GenderButtonGroup.getSelection() == null) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            return;
        }

        // Validate password
        if (NewPassword.length() < 8) {
            JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long");
            return;
        }
        
        
        try {
            
            

            List<User> user = UserDAO.loadUsers();
            for (User users : user){
                if (users.getUsername().equals(UserDAO.loadSessionData().get(0).getUsername())) {
                    users.setPassword(NewPassword);
                    users.setFullName(NewFullName);
                    users.setGender(NewGender);
                    UserDAO.modifyUser(users);
                    break;
                }
            }
            
            JOptionPane.showMessageDialog(null, "Update Successful!");

            // Reload the user profile
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Registration failed. Please try again.");
            e.printStackTrace();
        }
       
    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        GenderButtonGroup = new javax.swing.ButtonGroup();
        BackButton = new javax.swing.JButton();
        CustomerNotice = new javax.swing.JLabel();
        panel1 = new java.awt.Panel();
        jRadioButton2 = new javax.swing.JRadioButton();
        UsernameField = new javax.swing.JTextField();
        SaveChangesButton = new javax.swing.JButton();
        DateOfBirthLabel = new javax.swing.JLabel();
        DateOfBirthField = new javax.swing.JTextField();
        NewPasswordField = new javax.swing.JPasswordField();
        NewFullNameField = new javax.swing.JTextField();
        UsernameLabel = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        NewPasswordLabel = new javax.swing.JLabel();
        NewFullNameLabel = new javax.swing.JLabel();
        NewGenderLabel = new javax.swing.JLabel();
        ResetButton = new javax.swing.JButton();
        ProfileLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BackButton.setText("<Back");
        BackButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BackButtonMouseClicked(evt);
            }
        });

        CustomerNotice.setText("User are not allowed to changes Username and Date of Birth");

        GenderButtonGroup.add(jRadioButton2);
        jRadioButton2.setText("Female");

        UsernameField.setEditable(false);
        UsernameField.setText("UsernameField");
        UsernameField.setEnabled(false);

        SaveChangesButton.setText("Save Changes");
        SaveChangesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SaveChangesButtonMouseClicked(evt);
            }
        });

        DateOfBirthLabel.setText("Date Of Birth");

        DateOfBirthField.setEditable(false);
        DateOfBirthField.setText("DateOfBirthField");
        DateOfBirthField.setEnabled(false);

        UsernameLabel.setText("Username");

        GenderButtonGroup.add( jRadioButton1);
        jRadioButton1.setText("Male");

        NewPasswordLabel.setText("New Password");

        NewFullNameLabel.setText("New Full Name");

        NewGenderLabel.setText("New Gender");

        ResetButton.setText("Reset");
        ResetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResetButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DateOfBirthLabel)
                    .addComponent(NewFullNameLabel)
                    .addComponent(NewPasswordLabel)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent( jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addComponent(jRadioButton2))
                    .addComponent(UsernameField)
                    .addComponent(NewPasswordField)
                    .addComponent(NewFullNameField)
                    .addComponent(SaveChangesButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NewGenderLabel)
                    .addComponent(DateOfBirthField)
                    .addComponent(UsernameLabel)
                    .addComponent(ResetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(UsernameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(NewPasswordLabel)
                .addGap(8, 8, 8)
                .addComponent(NewPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(NewFullNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NewFullNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(NewGenderLabel)
                .addGap(3, 3, 3)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent( jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(18, 18, 18)
                .addComponent(DateOfBirthLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DateOfBirthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(ResetButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SaveChangesButton)
                .addGap(20, 20, 20))
        );

        ProfileLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        ProfileLabel.setText("Profile");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(BackButton)
                .addGap(343, 343, 343)
                .addComponent(ProfileLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(311, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(CustomerNotice)
                        .addGap(308, 308, 308))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(331, 331, 331))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BackButton)
                    .addComponent(ProfileLabel))
                .addGap(22, 22, 22)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CustomerNotice)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void SaveChangesButtonMouseClicked(java.awt.event.MouseEvent evt) {                                               
        // TODO add your handling code here:
        Savechanges();
    }                                              

    private void BackButtonMouseClicked(java.awt.event.MouseEvent evt) {                                        
        // TODO add your handling code here:
        UserMainUI userMainUI = new UserMainUI();
        userMainUI.setVisible(true);
        this.dispose();
    }                                       

    private void ResetButtonMouseClicked(java.awt.event.MouseEvent evt) {                                         
        // TODO add your handling code here:
        Reset();
    }                                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserProfileUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserProfileUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserProfileUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserProfileUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserProfileUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton BackButton;
    private javax.swing.JLabel CustomerNotice;
    private javax.swing.JTextField DateOfBirthField;
    private javax.swing.JLabel DateOfBirthLabel;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JTextField NewFullNameField;
    private javax.swing.JLabel NewFullNameLabel;
    private javax.swing.JLabel NewGenderLabel;
    private javax.swing.JPasswordField NewPasswordField;
    private javax.swing.JLabel NewPasswordLabel;
    private javax.swing.JLabel ProfileLabel;
    private javax.swing.JButton ResetButton;
    private javax.swing.JButton SaveChangesButton;
    private javax.swing.JTextField UsernameField;
    private javax.swing.JLabel UsernameLabel;
    private javax.swing.ButtonGroup GenderButtonGroup;
    private javax.swing.JRadioButton jRadioButton2;
    private java.awt.Panel panel1;
    // End of variables declaration                   
}

