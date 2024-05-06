/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package carrentalsystem.ui.user;

import carrentalsystem.dao.UserDAO;
import carrentalsystem.ui.common.LoginUI;
import carrentalsystem.ui.BasePanel;

/**
 *
 * @author theke
 */
public class UserMainUI extends BasePanel {

    /**
     * Creates new form UserMainUI
     */
    public UserMainUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BookingStatusButton = new javax.swing.JButton();
        LogoutButton1 = new javax.swing.JButton();
        PaymentButton = new javax.swing.JButton();
        BookingButton = new javax.swing.JButton();
        BookingHistoryButton = new javax.swing.JButton();
        ViewCarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        BookingStatusButton.setText("BOOKING STATUS");
        BookingStatusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookingStatusButtonActionPerformed(evt);
            }
        });

        LogoutButton1.setText("LOGOUT");
        LogoutButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoutButton1MouseClicked(evt);
            }
        });

        PaymentButton.setText("PAYMENT");

        BookingButton.setText("BOOKING");
        BookingButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BookingButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BookingButtonMouseEntered(evt);
            }
        });

        BookingHistoryButton.setText("BOOKING HISTORY");
        BookingHistoryButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BookingHistoryButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BookingHistoryButtonMouseEntered(evt);
            }
        });

        ViewCarButton.setText("VIEW CAR");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BookingButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BookingStatusButton, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BookingHistoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PaymentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ViewCarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LogoutButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BookingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BookingHistoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ViewCarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BookingStatusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PaymentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LogoutButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(223, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BookingStatusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookingStatusButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BookingStatusButtonActionPerformed

    private void LogoutButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutButton1MouseClicked
        // TODO add your handling code here:
        UserDAO.clearSessionData();
        LoginUI user = new LoginUI();
        user.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_LogoutButton1MouseClicked

    private void BookingButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BookingButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BookingButtonMouseClicked

    private void BookingButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BookingButtonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_BookingButtonMouseEntered

    private void BookingHistoryButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BookingHistoryButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BookingHistoryButtonMouseClicked

    private void BookingHistoryButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BookingHistoryButtonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_BookingHistoryButtonMouseEntered

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
            java.util.logging.Logger.getLogger(UserMainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserMainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserMainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserMainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserMainUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BookingButton;
    private javax.swing.JButton BookingHistoryButton;
    private javax.swing.JButton BookingStatusButton;
    private javax.swing.JButton LogoutButton1;
    private javax.swing.JButton PaymentButton;
    private javax.swing.JButton ViewCarButton;
    // End of variables declaration//GEN-END:variables
}
