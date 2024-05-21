package carrentalsystem.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
// import java.util.List;

// import javax.swing.JFrame;
import carrentalsystem.dao.UserDAO;
// import carrentalsystem.model.User;

public class BasePanel extends javax.swing.JFrame {
    public BasePanel() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    UserDAO.clearSessionData();
                    System.out.println("Method checking");
                    setDefaultCloseOperation(EXIT_ON_CLOSE);
                } catch (Exception ex) {
                    System.out.println("Error clearing session data: " + ex.getMessage());
                }
            }
        });
    }
}
