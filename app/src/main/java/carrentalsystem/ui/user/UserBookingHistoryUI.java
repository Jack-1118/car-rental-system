package carrentalsystem.ui.user;

import carrentalsystem.dao.BookDAO;
import carrentalsystem.dao.UserDAO;
import carrentalsystem.model.Booking;
import carrentalsystem.model.SharedData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.google.common.collect.Table;

public class UserBookingHistoryUI extends javax.swing.JFrame {

    /**
     * Creates new form BookingHistory
     */
    public UserBookingHistoryUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        BackButton = new javax.swing.JButton();
        BookingHistoryLabel = new java.awt.Label();
        UpComingBookingPanel = new java.awt.Panel();
        UpcomingBookingPanel = new javax.swing.JScrollPane();
        UpcomingBookingTable = new javax.swing.JTable();
        UpComingBookingLabel = new java.awt.Label();
        ViewUpComingBookingDetailButton = new javax.swing.JButton();
        ClearUpComingBookingSelectionButton = new javax.swing.JButton();
        UpComingBookingPanel1 = new java.awt.Panel();
        PastBookingPanel = new javax.swing.JScrollPane();
        PastBookingTable = new javax.swing.JTable();
        PastBookingLabel = new java.awt.Label();
        ViewPastBookingDetailButton = new javax.swing.JButton();
        ClearPastBookingSelectionButton = new javax.swing.JButton();
        List<Booking> Booking = BookDAO.loadBookings();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        BackButton.setText("<Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserMainUI userMainUI = new UserMainUI();
                userMainUI.setVisible(true);
                dispose();
            }
        });

        BookingHistoryLabel.setAlignment(java.awt.Label.CENTER);
        BookingHistoryLabel.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        BookingHistoryLabel.setText("Booking History");

        // Create column names
        String[] columnNames = {
                "Booking ID",
                "car ID",
                "Start Date",
                "End Date",
                "Status",
                "Amount",
                "Payment Status"
        };

        // Create 2D array for table data
        String username = UserDAO.loadSessionData().get(0).getUsername();
        // Initialize lists to store row data for both upcoming and past bookings
        List<Object[]> upcomingData = new ArrayList<>();
        List<Object[]> pastData = new ArrayList<>();

        // SDF simple date format
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false); // Prevents parsing dates like 30-02-2020
        Date today = new Date();

        try {
            Date todayFormatted = sdf.parse(sdf.format(today)); // Parsing today's date to reset time part

            for (int i = 0; i < Booking.size(); i++) {
                Date bookingEndDate;
                try {
                    bookingEndDate = sdf.parse(Booking.get(i).getEndDate()); // This might throw ParseException
                } catch (ParseException e) {
                    System.err
                            .println("Error parsing date for booking index " + i + ": " + Booking.get(i).getEndDate());
                    continue; // Skip this booking if the date is invalid
                }

                if (Booking.get(i).getUsername().equals(username)) {
                    Object[] rowData = new Object[] {
                            Booking.get(i).getBookingID(),
                            Booking.get(i).getCarID(),
                            Booking.get(i).getStartDate(),
                            Booking.get(i).getEndDate(),
                            Booking.get(i).getStatus(),
                            Booking.get(i).getAmount(),
                            Booking.get(i).getPaymentStatus()
                    };

                    if ((bookingEndDate.compareTo(todayFormatted) <= 0)
                            || Booking.get(i).getStatus().equals("Cancelled")) {
                        pastData.add(rowData);
                    } else {
                        upcomingData.add(rowData);

                    }
                }
            }
        } catch (ParseException e) {
            System.err.println("Error formatting today's date: " + sdf.format(today));
        }

        // Convert List<Object[]> to Object[][]
        Object[][] upcomingDataArray = new Object[upcomingData.size()][columnNames.length];
        for (int i = 0; i < upcomingData.size(); i++) {
            upcomingDataArray[i] = upcomingData.get(i);
        }

        Object[][] pastDataArray = new Object[pastData.size()][columnNames.length];
        for (int i = 0; i < pastData.size(); i++) {
            pastDataArray[i] = pastData.get(i);
        }

        // Set table data
        UpcomingBookingTable.setModel(new javax.swing.table.DefaultTableModel(upcomingDataArray, columnNames) {
            boolean[] canEdit = new boolean[] {
                    false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        UpcomingBookingTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        UpcomingBookingTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        UpcomingBookingTable.setShowHorizontalLines(true);
        UpcomingBookingTable.setShowVerticalLines(true);
        UpcomingBookingTable.getTableHeader().setReorderingAllowed(false);
        UpcomingBookingPanel.setViewportView(UpcomingBookingTable);

        PastBookingTable.setModel(new javax.swing.table.DefaultTableModel(pastDataArray, columnNames) {
            boolean[] canEdit = new boolean[] {
                    false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        PastBookingTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PastBookingTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        PastBookingTable.setShowHorizontalLines(true);
        PastBookingTable.setShowVerticalLines(true);
        PastBookingTable.getTableHeader().setReorderingAllowed(false);
        PastBookingPanel.setViewportView(PastBookingTable);

        UpComingBookingLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        UpComingBookingLabel.setText("Up Coming Booking");

        ViewUpComingBookingDetailButton.setText("View Booking");
        ViewUpComingBookingDetailButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                int selectedRow = UpcomingBookingTable.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select an Upcoming booking to view details", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int bookingID = ((Integer) UpcomingBookingTable.getValueAt(UpcomingBookingTable.getSelectedRow(), 0));
                int carID = ((Integer) UpcomingBookingTable.getValueAt(UpcomingBookingTable.getSelectedRow(), 1));

                // Use to debug
                System.out.println("Selected row: " + bookingID);
                System.out.println("Selected booking ID: " + carID);

                SharedData.setBookingId(bookingID);
                SharedData.setCarId(carID);

                UserBookingDetailsUI userBookingDetailUI = new UserBookingDetailsUI();
                userBookingDetailUI.setVisible(true);
                dispose();
            }
        });

        ClearUpComingBookingSelectionButton.setText("Clear Selection");
        ClearUpComingBookingSelectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpcomingBookingTable.clearSelection();
            }
        });

        javax.swing.GroupLayout UpComingBookingPanelLayout = new javax.swing.GroupLayout(UpComingBookingPanel);
        UpComingBookingPanel.setLayout(UpComingBookingPanelLayout);
        UpComingBookingPanelLayout.setHorizontalGroup(
                UpComingBookingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(UpComingBookingPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(UpComingBookingPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(UpcomingBookingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 902,
                                                Short.MAX_VALUE)
                                        .addGroup(UpComingBookingPanelLayout.createSequentialGroup()
                                                .addComponent(UpComingBookingLabel,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                UpComingBookingPanelLayout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(ClearUpComingBookingSelectionButton)
                                        .addGap(98, 98, 98)
                                        .addComponent(ViewUpComingBookingDetailButton)
                                        .addGap(310, 310, 310)));
        UpComingBookingPanelLayout.setVerticalGroup(
                UpComingBookingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(UpComingBookingPanelLayout.createSequentialGroup()
                                .addComponent(UpComingBookingLabel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(UpcomingBookingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 188,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(UpComingBookingPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(ViewUpComingBookingDetailButton)
                                        .addComponent(ClearUpComingBookingSelectionButton))
                                .addContainerGap(16, Short.MAX_VALUE)));

        // PastBookingTable.setModel(new javax.swing.table.DefaultTableModel(
        // new Object [][] {
        // {null, null, null, null},
        // {null, null, null, null},
        // {null, null, null, null},
        // {null, null, null, null}
        // },
        // new String [] {
        // "Title 1", "Title 2", "Title 3", "Title 4"
        // }
        // ) {
        // boolean[] canEdit = new boolean [] {
        // false, false, false, false
        // };

        // public boolean isCellEditable(int rowIndex, int columnIndex) {
        // return canEdit [columnIndex];
        // }
        // });
        // PastBookingTable.setCursor(new
        // java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        // PastBookingTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        // PastBookingTable.setShowHorizontalLines(true);
        // PastBookingTable.setShowVerticalLines(true);
        // PastBookingTable.getTableHeader().setReorderingAllowed(false);
        // PastBookingPanel.setViewportView(PastBookingTable);

        PastBookingLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        PastBookingLabel.setText("Past Booking");

        ViewPastBookingDetailButton.setText("View Booking");
        ViewPastBookingDetailButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                int selectedRow = PastBookingTable.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a Past booking to view details", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int bookingID = ((Integer) PastBookingTable.getValueAt(PastBookingTable.getSelectedRow(), 0));
                int carID = ((Integer) PastBookingTable.getValueAt(PastBookingTable.getSelectedRow(), 1));

                // Use to debug
                System.out.println("Selected row: " + bookingID);
                System.out.println("Selected booking ID: " + carID);

                SharedData.setBookingId(bookingID);
                SharedData.setCarId(carID);

                UserBookingDetailsUI userBookingDetailUI = new UserBookingDetailsUI();
                userBookingDetailUI.setVisible(true);
                dispose();
            }
        });

        ClearPastBookingSelectionButton.setText("Clear Selection");
        ClearPastBookingSelectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PastBookingTable.clearSelection();
            }
        });

        javax.swing.GroupLayout UpComingBookingPanel1Layout = new javax.swing.GroupLayout(UpComingBookingPanel1);
        UpComingBookingPanel1.setLayout(UpComingBookingPanel1Layout);
        UpComingBookingPanel1Layout.setHorizontalGroup(
                UpComingBookingPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(UpComingBookingPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(UpComingBookingPanel1Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(PastBookingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 902,
                                                Short.MAX_VALUE)
                                        .addGroup(UpComingBookingPanel1Layout.createSequentialGroup()
                                                .addComponent(PastBookingLabel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UpComingBookingPanel1Layout
                                .createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ClearPastBookingSelectionButton, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(100, 100, 100)
                                .addComponent(ViewPastBookingDetailButton, javax.swing.GroupLayout.PREFERRED_SIZE, 102,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(312, 312, 312)));
        UpComingBookingPanel1Layout.setVerticalGroup(
                UpComingBookingPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(UpComingBookingPanel1Layout.createSequentialGroup()
                                .addComponent(PastBookingLabel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(PastBookingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 192,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(UpComingBookingPanel1Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(ViewPastBookingDetailButton)
                                        .addComponent(ClearPastBookingSelectionButton))
                                .addContainerGap(16, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(BackButton)
                                                .addGap(237, 237, 237)
                                                .addComponent(BookingHistoryLabel,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 298,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(UpComingBookingPanel, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(UpComingBookingPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(BackButton)
                                        .addComponent(BookingHistoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(UpComingBookingPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(UpComingBookingPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pack();
    }// </editor-fold>

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserBookingHistoryUI.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserBookingHistoryUI.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserBookingHistoryUI.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserBookingHistoryUI.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserBookingHistoryUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton BackButton;
    private java.awt.Label BookingHistoryLabel;
    private javax.swing.JButton ClearPastBookingSelectionButton;
    private javax.swing.JButton ClearUpComingBookingSelectionButton;
    private java.awt.Label PastBookingLabel;
    private javax.swing.JScrollPane PastBookingPanel;
    private javax.swing.JTable PastBookingTable;
    private java.awt.Label UpComingBookingLabel;
    private java.awt.Panel UpComingBookingPanel;
    private java.awt.Panel UpComingBookingPanel1;
    private javax.swing.JScrollPane UpcomingBookingPanel;
    private javax.swing.JTable UpcomingBookingTable;
    private javax.swing.JButton ViewPastBookingDetailButton;
    private javax.swing.JButton ViewUpComingBookingDetailButton;
    // End of variables declaration
}