package carrentalsystem.ui.admin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import carrentalsystem.dao.BookDAO;
import carrentalsystem.model.Booking;
import carrentalsystem.model.SharedData;

public class AdminBookUI extends JPanel{
    /**
     * Creates new form AdminBookingManagementUI
     */
    public AdminBookUI() {
        initComponents();
        populateTables();
    }

    public void populateTables() {
        List<Booking> bookings = BookDAO.loadBookings();
        LocalDate today = LocalDate.now();
        
        PastBookings(bookings);
        ReviewBookings(bookings, today);
    }

    private void PastBookings(List<Booking> bookings) {
        DefaultTableModel model = (DefaultTableModel) PastBookingTable.getModel();
        model.setRowCount(0);

        Boolean hasBooking = false;
        for (Booking booking : bookings) {
            
            if (booking.getStatus().equals("Approved") || booking.getStatus().equals("Rejected") || booking.getStatus().equals("Cancelled") ){
                hasBooking = true;
                model.addRow(new Object[] {
                    booking.getBookingID(),
                    booking.getCarID(),
                    booking.getUsername(),
                    booking.getStartDate(),
                    booking.getEndDate(),
                    booking.getStatus(),
                    booking.getAmount(),
                    booking.getPaymentStatus()
                });
            }
        
        }
        if (!hasBooking) {
            model.addRow(new Object[]{"no booking", "no booking", "no booking", "no booking", "no booking", "no booking", "no booking", "no booking"});
        }
    }

    private void ReviewBookings(List<Booking> bookings, LocalDate today) {
        DefaultTableModel model = (DefaultTableModel) UnderReviewBooking.getModel();
        model.setRowCount(0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        boolean hasBooking = false;

        for (Booking booking : bookings) {
                try{
                        LocalDate startDate = LocalDate.parse(booking.getStartDate(), formatter); // Convert String to LocalDate

                        if (startDate.isAfter(today) && booking.getStatus().equals("Pending Review")) {
                                hasBooking = true;
                                model.addRow(new Object[]{
                                    booking.getBookingID(),
                                    booking.getCarID(),
                                    booking.getUsername(),
                                    booking.getStartDate(),
                                    booking.getEndDate(),
                                    booking.getStatus(),
                                    booking.getAmount(),
                                    booking.getPaymentStatus()
                                });
                                }
                        }catch (DateTimeParseException e) {
                                System.err.println("Error parsing date: " + booking.getEndDate());
                                e.printStackTrace();
                        }
                }
                if (!hasBooking) {
                        model.addRow(new Object[]{"no booking", "no booking", "no booking", "no booking", "no booking", "no booking", "no booking", "no booking"});
                }

    }

    private void ViewBookingDetail() {
        int selectedRow = PastBookingTable.getSelectedRow();
    
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a booking to view details", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        if (selectedRow == 0 && PastBookingTable.getValueAt(PastBookingTable.getSelectedRow(), 0).equals("no booking")) {
            JOptionPane.showMessageDialog(null, "No Upcoming booking to view details", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        int bookingID = (Integer) PastBookingTable.getValueAt(selectedRow, 0);
        int carID = (Integer) PastBookingTable.getValueAt(selectedRow, 1);
    
        SharedData.setBookingId(bookingID);
        SharedData.setCarId(carID);
    
        // Ensure this UI is properly initialized
        AdminViewBookingDetailUI admin = new AdminViewBookingDetailUI();
        admin.setVisible(true);
    
    
        // Optionally, dispose or hide the previous window if it is no longer needed
    }
    
    private void ReviewBookingDetail() {
        int selectedRow = UnderReviewBooking.getSelectedRow();

        if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null,"Please select a booking to review", "Error",
                                JOptionPane.ERROR_MESSAGE);
                return;
        }

        if(selectedRow == 0 && UnderReviewBooking.getValueAt(UnderReviewBooking.getSelectedRow(), 0).equals("no booking")){
                JOptionPane.showMessageDialog(null,
                                "No booking to review", "Error",
                                JOptionPane.ERROR_MESSAGE);
                return;
        }

        int bookingID = ((Integer) UnderReviewBooking.getValueAt(UnderReviewBooking.getSelectedRow(), 0));
        int carID = ((Integer) UnderReviewBooking.getValueAt(UnderReviewBooking.getSelectedRow(), 1));

        SharedData.setBookingId(bookingID);
        SharedData.setCarId(carID);

        AdminReviewBooking AdminReviewBooking = new AdminReviewBooking();
        AdminReviewBooking.setVisible(true);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        panel1 = new java.awt.Panel();
        PastBookingLabel = new java.awt.Label();
        ViewBookingButton = new java.awt.Button();
        panel2 = new java.awt.Panel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PastBookingTable = new javax.swing.JTable();
        panel3 = new java.awt.Panel();
        UnderReviewLabel = new java.awt.Label();
        ReviewBookingButton = new java.awt.Button();
        panel4 = new java.awt.Panel();
        jScrollPane2 = new javax.swing.JScrollPane();
        UnderReviewBooking = new javax.swing.JTable();

        PastBookingLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        PastBookingLabel.setText("Past Booking");

        ViewBookingButton.setLabel("View Booking");
        ViewBookingButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ViewBookingButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PastBookingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ViewBookingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addComponent(PastBookingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ViewBookingButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PastBookingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                
            },
            new String [] {
                "Booking ID", 
                "Car ID", 
                "Username", 
                "Start Date", 
                "End Date", 
                "Status", 
                "Amount", 
                "Payment Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        PastBookingTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PastBookingTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        PastBookingTable.setShowHorizontalLines(true);
        PastBookingTable.setShowVerticalLines(true);
        PastBookingTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(PastBookingTable);

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );

        UnderReviewLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        UnderReviewLabel.setText("Under Review");

        ReviewBookingButton.setLabel("Review Booking");
        ReviewBookingButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReviewBookingButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UnderReviewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ReviewBookingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addComponent(UnderReviewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ReviewBookingButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        UnderReviewBooking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Booking ID", "Car ID", "Username", "Start Date", "End Date", "Status", "Amount", "Payment Status"
            }
        ){
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        UnderReviewBooking.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        UnderReviewBooking.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        UnderReviewBooking.setShowHorizontalLines(true);
        UnderReviewBooking.setShowVerticalLines(true);
        UnderReviewBooking.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(UnderReviewBooking);

        javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>                        

    private void ViewBookingButtonMouseClicked(java.awt.event.MouseEvent evt) {                                               
        // TODO add your handling code here:
        ViewBookingDetail();
    }                                              

    private void ReviewBookingButtonMouseClicked(java.awt.event.MouseEvent evt) {                                                 
        // TODO add your handling code here:
        ReviewBookingDetail();
    }                                                


    // Variables declaration - do not modify                     
    private java.awt.Label PastBookingLabel;
    private javax.swing.JTable PastBookingTable;
    private java.awt.Button ReviewBookingButton;
    private javax.swing.JTable UnderReviewBooking;
    private java.awt.Label UnderReviewLabel;
    private java.awt.Button ViewBookingButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private java.awt.Panel panel3;
    private java.awt.Panel panel4;
    // End of variables declaration                   
}

