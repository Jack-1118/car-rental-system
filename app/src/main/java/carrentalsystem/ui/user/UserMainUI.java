package carrentalsystem.ui.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMainUI extends JFrame {

    public UserMainUI() {
        setTitle("User Main Menu");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton searchCarButton = new JButton("Search Car to Rent");
        JButton bookingHistoryButton = new JButton("Check Booking History");

        panel.add(searchCarButton);
        panel.add(bookingHistoryButton);

        searchCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the search car window
                JOptionPane.showMessageDialog(UserMainUI.this, "Open Search Car Window");
            }
        });

        bookingHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the booking history window
                JOptionPane.showMessageDialog(UserMainUI.this, "Open Booking History Window");
            }
        });

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserMainUI().setVisible(true);
            }
        });
    }
}
