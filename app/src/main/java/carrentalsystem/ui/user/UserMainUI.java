/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package carrentalsystem.ui.user;

import carrentalsystem.dao.UserDAO;
import carrentalsystem.ui.common.LoginUI;
import carrentalsystem.ui.BasePanel;

import java.text.SimpleDateFormat;

import java.util.Calendar;

import java.util.Date;

import java.util.List;

import java.util.concurrent.TimeUnit;


import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import carrentalsystem.dao.BookDAO;
import carrentalsystem.dao.CarDAO;
import carrentalsystem.model.Booking;
import carrentalsystem.model.Car;
import carrentalsystem.model.SharedData;
import javax.swing.table.DefaultTableModel;

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
                CarList();
                populatePaymentList();
        }

        public void CarList() {
                DefaultTableModel model = (DefaultTableModel) BookingTable.getModel();
                model.setRowCount(0); // Clear existing data

                List<Car> cars = CarDAO.loadCars();

                boolean hasCar = false;

                for (Car car : cars) {
                        hasCar = true;
                        model.addRow(new Object[] {
                                        car.getCarID(),
                                        car.getBrand(),
                                        car.getModel(),
                                        car.getYear(),
                                        car.getColour(),
                                        car.getSeatCapacity(),
                                        car.getFuelType(),
                                        car.getTransmission(),
                                        car.getRentalRate(),
                                        car.getRentalLocation()
                        });

                }

                if (!hasCar) {
                        model.addRow(new Object[] { "no car", "no car", "no car", "no car", "no car", "no car",
                                        "no car" });
                }
        }

        public void BookCar() {
                // Get the input from user
                Date startDate = StartDate.getDate();
                Date endDate = EndDate.getDate();
                int selectedRow = BookingTable.getSelectedRow();
                int carID = ((Integer) BookingTable.getValueAt(BookingTable.getSelectedRow(), 0)).intValue();
                System.out.println("Selected Car: " + carID);

                // Check if the user has selected a car and all fields are filled
                if (startDate == null || endDate == null || selectedRow == -1) {
                        JOptionPane.showMessageDialog(null, "Please Field all Selection.");
                        return;
                } else if (selectedRow == -1) {
                        JOptionPane.showMessageDialog(null, "Please Select a Car.");
                        return;
                }

                // Validate the date range
                if (endDate.before(startDate)) {
                        JOptionPane.showMessageDialog(null,
                                        "Invalida Date Range. Please select a valid date range.");
                        return;
                }
                calculateDay(startDate, endDate);
                System.out.println("Days: " + calculateDay(startDate, endDate));

                try {
                        List<Booking> bookingList = BookDAO.loadBookings();
                        for (Booking booking : bookingList) {
                                if (booking.getCarID() == carID) {
                                        // Retreive the existing booking start and end date inside the
                                        // database
                                        Date bookingStartDate = new SimpleDateFormat("dd-MM-yyyy")
                                                        .parse(booking.getStartDate());
                                        Date bookingEndDate = new SimpleDateFormat("dd-MM-yyyy")
                                                        .parse(booking.getEndDate());

                                        // Start date cannot be before the booking start date and end
                                        // date cannot be after the booking end date
                                        if (startDate.before(bookingEndDate) || startDate
                                                        .equals(bookingEndDate) &&
                                                        (endDate.after(bookingStartDate) || endDate
                                                                        .equals(bookingStartDate))
                                                        &&
                                                        (booking.getStatus().equals("Approved")
                                                                        || booking.getStatus().equals(
                                                                                        "Pending Review"))

                                        ) {
                                                JOptionPane.showMessageDialog(null,
                                                                "Car is not available for the selected date range.");
                                                return;
                                        }
                                }
                        }

                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        String formattedStartDate = sdf.format(startDate);
                        String formattedEndDate = sdf.format(endDate);
                        String status = "Pending Review";
                        String paymentStatus = "Pending Payment";
                        double amount = calculateDay(startDate, endDate) * Double.parseDouble(
                                        BookingTable.getValueAt(BookingTable.getSelectedRow(), 8)
                                                        .toString());
                        String username = UserDAO.loadSessionData().get(0).getUsername();
                        int bookingID = BookDAO.assignBookingID();

                        Booking newBooking = new Booking(bookingID, carID, username, formattedStartDate,
                                        formattedEndDate, status, amount, paymentStatus);
                        BookDAO.saveBook(newBooking);

                        StartDate.setDate(null);
                        EndDate.setDate(null);
                        BookingTable.clearSelection();
                        JOptionPane.showMessageDialog(null,
                                        "Your booking will take 3 Working Days to be approved. Thank you for your patience.");

                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        private int calculateDay(Date startDate, Date endDate) {
                // Convert String into Date
                Calendar start = Calendar.getInstance();
                start.setTime(startDate);
                Calendar end = Calendar.getInstance();
                end.setTime(endDate);

                // compare if the start date and end date are the same
                if (start.get(Calendar.YEAR) == end.get(Calendar.YEAR) &&
                                start.get(Calendar.MONTH) == end.get(Calendar.MONTH) &&
                                start.get(Calendar.DAY_OF_MONTH) == end.get(Calendar.DAY_OF_MONTH)) {
                        return 1;
                }

                // else calculate the difference in days
                long differenceInMilliSeconds = endDate.getTime() - startDate.getTime();
                long differenceInDays = TimeUnit.DAYS.convert(differenceInMilliSeconds,
                                TimeUnit.MILLISECONDS) + 1;
                return (int) differenceInDays;
        }

        private void ClearField(String msg, String title) {
                int response = JOptionPane.showConfirmDialog(null, msg, title,
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                        StartDate.setDate(null);
                        EndDate.setDate(null);
                        BookingTable.clearSelection();
                }
        }

        private void populatePaymentList() {
                DefaultListModel<String> displayBookings = new DefaultListModel<>();
                List<Booking> bookings = BookDAO.loadBookings();
                String username = UserDAO.loadSessionData().get(0).getUsername();
                for (Booking booking : bookings) {
                        if (booking.getUsername().equals(username) &&
                                        booking.getPaymentStatus().equals("Pending Payment") &&
                                        booking.getStatus().equals("Approved")) {

                                String displayText = "Booking ID: " + booking.getBookingID() +
                                                                ", Car ID: " + booking.getCarID() +
                                                                ", Start Date: " + booking.getStartDate() +
                                                                ", Status: " + booking.getPaymentStatus() +
                                                                ", Amount: " + booking.getAmount();
                                displayBookings.addElement(displayText); // Replace 'add' with 'addElement'
                                System.out.println(displayText);
                        }
                }
                PendingPaymentList.setModel(displayBookings);  // assuming your JList variable is nameList
        }
        
        private void Payment() {

                String selectedBooking = PendingPaymentList.getSelectedValue();
                if (selectedBooking == null) {
                        JOptionPane.showMessageDialog(null, "Please select a booking to proceed.");
                        return;
                }

                int bookingID = Integer.parseInt(selectedBooking.split(",")[0].split(":")[1].trim());
                int carID = Integer.parseInt(selectedBooking.split(",")[1].split(":")[1].trim());

                SharedData.setBookingId(bookingID);
                SharedData.setCarId(carID);

                UserPaymentUI user = new UserPaymentUI();
                // user.setSelectedBooking(selectedBooking);

                user.setVisible(true);
                UserMainUI.this.dispose();

        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        private void initComponents() {

                RentalStoreNameLabel = new java.awt.Label();
                Profile = new javax.swing.JButton();
                panel1 = new java.awt.Panel();
                AvailableCarLabel = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                BookingTable = new javax.swing.JTable();
                HistoryButton = new javax.swing.JButton();
                LogoutButton = new javax.swing.JButton();
                panel4 = new java.awt.Panel();
                StartDateLabel = new java.awt.Label();
                StartDate = new com.toedter.calendar.JDateChooser();
                EndDateLbel = new java.awt.Label();
                EndDate = new com.toedter.calendar.JDateChooser();
                ClearButton = new javax.swing.JButton();
                BookCarButton = new javax.swing.JButton();
                PaymentButton = new javax.swing.JButton();
                jScrollPane3 = new javax.swing.JScrollPane();
                PendingPaymentList = new javax.swing.JList<>();
                BookingStatusLabel = new java.awt.Label();
                PaymentNoticeLabel = new java.awt.Label();
                panel2 = new java.awt.Panel();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                setResizable(false);

                RentalStoreNameLabel.setAlignment(java.awt.Label.CENTER);
                RentalStoreNameLabel.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
                RentalStoreNameLabel.setText("SOYA CAR RENTAL");


                AvailableCarLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                AvailableCarLabel.setText("Available Car");

                BookingTable.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {

                                },
                                new String[] {
                                                "ID",
                                                "Brand",
                                                "Model",
                                                "Year",
                                                "Colour",
                                                "Seat Capacity",
                                                "Fuel Type",
                                                "Transmission",
                                                "Rental Rate",
                                                "Rental Location"
                                }) {
                        boolean[] canEdit = new boolean[] {
                                        false, false, false, false, false, false, false, false, false, false
                        };

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit[columnIndex];
                        }
                });
                BookingTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                BookingTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
                BookingTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
                BookingTable.setShowHorizontalLines(true);
                BookingTable.setShowVerticalLines(true);
                BookingTable.getTableHeader().setReorderingAllowed(false);
                jScrollPane1.setViewportView(BookingTable);

               
                javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
                panel1.setLayout(panel1Layout);
                panel1Layout.setHorizontalGroup(
                                panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel1Layout.createSequentialGroup()
                                                                .addGroup(panel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(panel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(17, 17, 17)
                                                                                                .addComponent(AvailableCarLabel,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                201,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                                .addGroup(panel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(jScrollPane1,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                916,
                                                                                                                Short.MAX_VALUE)))
                                                                .addContainerGap()));
                panel1Layout.setVerticalGroup(
                                panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(AvailableCarLabel)
                                                                .addGap(27, 27, 27)
                                                                .addComponent(jScrollPane1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                216,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(33, Short.MAX_VALUE)));

                LogoutButton.setText("Logout");
                LogoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                LogoutButtonMouseClicked(evt);
                        }
                });

                Calendar calendar = Calendar.getInstance(); // Get the current date
                calendar.add(Calendar.DATE, 3); // Add 3 days
                Date dateThreeDaysLater = calendar.getTime(); // Convert it back to a Date object

                StartDate.setMinSelectableDate(dateThreeDaysLater);

                StartDateLabel.setText("Start Date");
                StartDate.setMaxSelectableDate(new java.util.Date(253370739683000L));
                StartDate.setMinSelectableDate(dateThreeDaysLater);

                EndDateLbel.setText("End Date");
                EndDate.setMaxSelectableDate(new java.util.Date(253370739683000L));
                EndDate.setMinSelectableDate(dateThreeDaysLater);

                ClearButton.setText("Clear");
                ClearButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                ClearButtonMouseClicked(evt);
                        }
                });

                BookCarButton.setText("Book Car");
                BookCarButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                BookCarButtonMouseClicked(evt);

                        }
                });

                PaymentButton.setText("Payment");
                PaymentButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                PaymentButtonMouseClicked(evt);
                        }
                });

                PendingPaymentList.setModel(new javax.swing.AbstractListModel<String>() {
                        String[] strings = { "displayBookings" };
                        public int getSize() { return strings.length; }
                        public String getElementAt(int i) { return strings[i]; }
                    });
                PendingPaymentList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
                PendingPaymentList.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                PendingPaymentList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
                public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                        PendingPaymentListValueChanged(evt);
                }
                });
                

                BookingStatusLabel.setText("Booking Status - Pending Payment");
                jScrollPane3.setViewportView(PendingPaymentList);

                PaymentNoticeLabel.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
                PaymentNoticeLabel.setText(
                                "Payment must be made by the day before your start date to avoid automatic cancellation.");

                javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
                panel4.setLayout(panel4Layout);
                panel4Layout.setHorizontalGroup(
                                panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel4Layout
                                                                .createSequentialGroup()
                                                                .addGroup(panel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(panel4Layout
                                                                                                .createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addGroup(panel4Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(StartDateLabel,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(StartDate,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                156,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(38, 38, 38)
                                                                                                .addGroup(panel4Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(EndDate,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                156,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(EndDateLbel,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                .addGroup(panel4Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(45, 45, 45)
                                                                                                .addComponent(ClearButton)
                                                                                                .addGap(122, 122, 122)
                                                                                                .addComponent(BookCarButton)))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                44, Short.MAX_VALUE)
                                                                .addGroup(panel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addGroup(panel4Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(panel4Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(PaymentNoticeLabel,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(BookingStatusLabel,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                214,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(96, 96, 96))
                                                                                .addGroup(panel4Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jScrollPane3)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(PaymentButton,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                77,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap()))));
                panel4Layout.setVerticalGroup(
                                panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel4Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(panel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(StartDateLabel,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(EndDateLbel,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(BookingStatusLabel,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(panel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(panel4Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(panel4Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(StartDate,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(EndDate,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(28, 28, 28)
                                                                                                .addGroup(panel4Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(ClearButton)
                                                                                                                .addComponent(BookCarButton)))
                                                                                .addGroup(panel4Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(panel4Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jScrollPane3,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                53,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(PaymentButton,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                53,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(PaymentNoticeLabel,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap(31, Short.MAX_VALUE)));

                RentalStoreNameLabel.setAlignment(java.awt.Label.CENTER);
                RentalStoreNameLabel.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
                RentalStoreNameLabel.setText("SOYA CAR RENTAL");

                HistoryButton.setText("Booking History");
                HistoryButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                HistoryButtonMouseClicked(evt);

                        }
                });

                Profile.setText("Profile");
                Profile.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                ProfileMouseClicked(evt);
                        }
                });

                javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
                panel2.setLayout(panel2Layout);
                panel2Layout.setHorizontalGroup(
                                panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(HistoryButton,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                166,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(42, 42, 42)
                                                                .addComponent(RentalStoreNameLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                420,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(25, 25, 25)
                                                                .addComponent(Profile,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                102,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(LogoutButton,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                102,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));
                panel2Layout.setVerticalGroup(
                                panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(panel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(panel2Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(Profile,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                53,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(LogoutButton,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                53,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(HistoryButton,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                53,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(RentalStoreNameLabel,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                56,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(panel4,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(panel1,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(panel2,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)))
                                                                .addContainerGap()));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(38, Short.MAX_VALUE)
                                                                .addComponent(panel2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(21, 21, 21)
                                                                .addComponent(panel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(panel4,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(10, 10, 10)));
                pack();
        }// </editor-fold>

        private void HistoryButtonMouseClicked(java.awt.event.MouseEvent evt) {
                // TODO add your handling code here:
                UserBookingHistoryUI UserBookingHistoryUI = new UserBookingHistoryUI();
                UserBookingHistoryUI.setVisible(true);
                UserMainUI.this.dispose();
        }

        private void LogoutButtonMouseClicked(java.awt.event.MouseEvent evt) {
                UserDAO.clearSessionData();
                LoginUI user = new LoginUI();
                user.setVisible(true);
                this.dispose();
        }

        private void ModelListActionPerformed(java.awt.event.ActionEvent evt) {
                // TODO add your handling code here:
        }

        private void ProfileMouseClicked(java.awt.event.MouseEvent evt) {
                // TODO add your handling code here:
                UserProfileUI userProfileUI = new UserProfileUI();
                userProfileUI.setVisible(true);
                this.dispose();
        }

        private void ClearButtonMouseClicked(java.awt.event.MouseEvent evt) {
                // TODO add your handling code here:
                ClearField("Are you sure you want to clear the fields?", "Clear Fields");
        }

        private void BookCarButtonMouseClicked(java.awt.event.MouseEvent evt) {
                // TODO add your handling code here:
                BookCar();
        }

        private void PaymentButtonMouseClicked(java.awt.event.MouseEvent evt) {
                // TODO add your handling code here:
                Payment();
        }

        private void PendingPaymentListValueChanged(javax.swing.event.ListSelectionEvent evt) {
                // TODO add your handling code here:
                
        }

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
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                                        .getInstalledLookAndFeels()) {
                                if ("Nimbus".equals(info.getName())) {
                                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                        break;
                                }
                        }
                } catch (ClassNotFoundException ex) {
                        java.util.logging.Logger.getLogger(UserMainUI.class.getName())
                                        .log(java.util.logging.Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(UserMainUI.class.getName())
                                        .log(java.util.logging.Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(UserMainUI.class.getName())
                                        .log(java.util.logging.Level.SEVERE, null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(UserMainUI.class.getName())
                                        .log(java.util.logging.Level.SEVERE, null, ex);
                }
                // </editor-fold>

                /* Create and display the form */
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                new UserMainUI().setVisible(true);
                        }
                });
        }

        // Variables declaration - do not modify
        private javax.swing.JLabel AvailableCarLabel;
        private javax.swing.JButton BookCarButton;
        private javax.swing.JButton ClearButton;
        private com.toedter.calendar.JDateChooser EndDate;
        private java.awt.Label EndDateLbel;
        private javax.swing.JButton HistoryButton;
        private javax.swing.JButton LogoutButton;
        // private javax.swing.JComboBox<String> ModelList;
        private javax.swing.JButton PaymentButton;
        private javax.swing.JButton Profile;
        private com.toedter.calendar.JDateChooser StartDate;
        private java.awt.Label StartDateLabel;
        private javax.swing.JList<String> PendingPaymentList;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JScrollPane jScrollPane3;
        private javax.swing.JTable BookingTable;
        private java.awt.Label RentalStoreNameLabel;
        private java.awt.Label PaymentNoticeLabel;
        private java.awt.Label BookingStatusLabel;
        private java.awt.Panel panel1;

        private java.awt.Panel panel2;
        private java.awt.Panel panel4;
        // End of variables declaration
}