package carrentalsystem.ui.admin;

import java.util.List;

import carrentalsystem.dao.BookDAO;
import carrentalsystem.dao.CarDAO;
import carrentalsystem.dao.UserDAO;
import carrentalsystem.model.Booking;
import carrentalsystem.model.Car;
import carrentalsystem.model.SharedData;
import carrentalsystem.model.User;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */


/**
 *
 * @author theke
 */
public class AdminReviewBooking extends javax.swing.JFrame {
    private int currentBookingId; // Unset state is -1
    private int currentCarId; // Unset state is -1

    /**
     * Creates new form AdminReviewBooking
     */
    public AdminReviewBooking() {
        this.currentBookingId = SharedData.getBookingId();
        this.currentCarId = SharedData.getCarId();
        initComponents();

        renderBookingData();
    }

    private void renderBookingData(){
        String CarBrand = null;
        String CarModel = null;
        String CarYear = null;
        String CarColour = null;
        String SeatCapacity = null;
        String FuelType = null;
        String Transmission = null;
        String RentalRate = null;
        String RentalLocation = null;
        double CarRentalRate = 0.0;

        List<Car> carList = CarDAO.loadCars();
        for (Car car : carList) {
            if (car.getCarID() == currentCarId) {
                CarBrand = (car.getBrand());
                CarModel = (car.getModel());
                CarYear = Integer.toString(car.getYear());
                CarColour = (car.getColour());
                SeatCapacity = (Integer.toString(car.getSeatCapacity()));
                FuelType = (car.getFuelType());
                Transmission = (car.getTransmission());
                RentalRate = (Double.toString(car.getRentalRate()));
                CarRentalRate = car.getRentalRate();
                RentalLocation = (car.getRentalLocation());
            }
        }

        CarBrandField.setText(CarBrand);
        CarModelField.setText(CarModel);
        CarYearField.setText(CarYear);
        CarColourField.setText(CarColour);
        SeatCapacityField.setText(SeatCapacity);
        FuelTypeField.setText(FuelType);
        TransmissionField.setText(Transmission);
        RentalRateField.setText(RentalRate);
        RentalLocationField.setText(RentalLocation);

        String StartingDate = null;
        String EndingDate = null;
        Double TotalAmount = 0.0;
        String Username = null;

        RentalLocationLabel.setText("Rental Location");

        List<Booking> bookingList = BookDAO.loadBookings();
        for (Booking booking : bookingList) {
            if (booking.getBookingID() == currentBookingId) {
                StartingDate = (booking.getStartDate());
                EndingDate = (booking.getEndDate());
                TotalAmount = (booking.getAmount());
                Username = (booking.getUsername());
            }
        }

        StartingDateField.setText(StartingDate);
        EndingDateField.setText(EndingDate);
        

        
        BookingIDField.setText(Integer.toString(currentBookingId)); // Convert BookingID to a string  Integer.toString(currentBookingId)

        System.out.println("nooking id inside ui" + currentBookingId);


        int TotalDays = Double.valueOf(TotalAmount).intValue() / Double.valueOf(CarRentalRate).intValue();
        ReceiptArea.setText(
                "Car Rental Rate: " + CarRentalRate +
                        "\n" +
                        "Total Days: " + TotalDays +
                        "\n" +
                        "Total Amount: " + TotalAmount
        );

        
        List<User> user = UserDAO.loadUsers();
        for (User users : user){
            if (users.getUsername().equals(Username)) {
                UsernameField.setText(users.getUsername());
                FullNameField.setText(users.getFullName());
                GenderField.setText(users.getGender());
                DateOfBirthField.setText(users.getDateOfBirth());
            }
        }
  

    }

    private void ApproveBooking(){
        List<Booking> bookingList = BookDAO.loadBookings();
        for (Booking booking : bookingList) {
            if (booking.getBookingID() == currentBookingId) {
                booking.setStatus("Approved");
                BookDAO.modifyBooking(booking);
            }
        }
        JOptionPane.showMessageDialog(null, "Booking Approved Successfully");
        dispose();
    }

    private void RejectBooking(){
        List<Booking> bookingList = BookDAO.loadBookings();
        for (Booking booking : bookingList) {
            if (booking.getBookingID() == currentBookingId) {
                booking.setStatus("Rejected");
                BookDAO.modifyBooking(booking);
            }
        }
        JOptionPane.showMessageDialog(null, "Booking Rejected Successfully");
        dispose();
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        panel2 = new java.awt.Panel();
        BookingDetailLabel = new java.awt.Label();
        CarBrandField = new javax.swing.JTextField();
        CarModelField = new javax.swing.JTextField();
        CarYearField = new javax.swing.JTextField();
        CarColourField = new javax.swing.JTextField();
        SeatCapacityField = new javax.swing.JTextField();
        FuelTypeField = new javax.swing.JTextField();
        TransmissionField = new javax.swing.JTextField();
        RentalRateField = new javax.swing.JTextField();
        RentalLocationField = new javax.swing.JTextField();
        StartingDateField = new javax.swing.JTextField();
        EndingDateField = new javax.swing.JTextField();
        StartingDateLabel = new javax.swing.JLabel();
        EndingDateLabel = new javax.swing.JLabel();
        BookingIDLabel = new javax.swing.JLabel();
        CarDetailsLabel = new java.awt.Label();
        CarBrandLabel = new javax.swing.JLabel();
        CarModelLabel = new javax.swing.JLabel();
        CarYearLabel = new javax.swing.JLabel();
        CarColourLabel = new javax.swing.JLabel();
        SeatCapacityLabel = new javax.swing.JLabel();
        FuelTypeLabel = new javax.swing.JLabel();
        TransmissionLabel = new javax.swing.JLabel();
        RentalRateLabel = new javax.swing.JLabel();
        RentalLocationLabel = new javax.swing.JLabel();
        BookingIDField = new javax.swing.JTextField();
        panel3 = new java.awt.Panel();
        PersonalDetail = new java.awt.Label();
        UsernameField = new javax.swing.JTextField();
        UsernameLabel = new javax.swing.JLabel();
        FullNameField = new javax.swing.JTextField();
        FullNameLabel = new javax.swing.JLabel();
        GenderField = new javax.swing.JTextField();
        GenderLabel = new javax.swing.JLabel();
        DateOfBirthLabel = new javax.swing.JLabel();
        DateOfBirthField = new javax.swing.JTextField();
        ReceiptArea = new java.awt.TextArea();
        ReceiptLabel = new java.awt.Label();
        jLabel2 = new javax.swing.JLabel();
        ApproveButton = new javax.swing.JButton();
        RejectButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        BookingDetailLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        BookingDetailLabel.setText("Booking Details");

        CarBrandField.setEditable(false);
        CarBrandField.setText("CarBrand");
        CarBrandField.setEnabled(false);

        CarModelField.setEditable(false);
        CarModelField.setText("CarModel");
        CarModelField.setEnabled(false);

        CarYearField.setEditable(false);
        CarYearField.setText("CarYear");
        CarYearField.setEnabled(false);

        CarColourField.setEditable(false);
        CarColourField.setText("CarColour");
        CarColourField.setEnabled(false);

        SeatCapacityField.setEditable(false);
        SeatCapacityField.setText("SeatCapacity");
        SeatCapacityField.setEnabled(false);

        FuelTypeField.setEditable(false);
        FuelTypeField.setText("FuelType");
        FuelTypeField.setEnabled(false);

        TransmissionField.setEditable(false);
        TransmissionField.setText("Transmission");
        TransmissionField.setEnabled(false);

        RentalRateField.setEditable(false);
        RentalRateField.setText("RentalRate");
        RentalRateField.setEnabled(false);

        RentalLocationField.setEditable(false);
        RentalLocationField.setText("RentalLocation");
        RentalLocationField.setEnabled(false);

        StartingDateField.setEditable(false);
        StartingDateField.setText("StartingDate");
        StartingDateField.setEnabled(false);

        EndingDateField.setEditable(false);
        EndingDateField.setText("EndingDate");
        EndingDateField.setEnabled(false);

        StartingDateLabel.setText("Starting Date");

        EndingDateLabel.setText("Ending Date");

        BookingIDLabel.setText("Booking ID");

        CarDetailsLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        CarDetailsLabel.setText("Car Details");

        CarBrandLabel.setText("Car Brand");

        CarModelLabel.setText("Car Model");

        CarYearLabel.setText("Car Year");

        CarColourLabel.setText("Car Colour");

        SeatCapacityLabel.setText("Seat Capacity");

        FuelTypeLabel.setText("Fuel Type");

        TransmissionLabel.setText("Transmission");

        RentalRateLabel.setText("Rental Rate");

        RentalLocationLabel.setText("Rental Location");

        BookingIDField.setEditable(false);
        BookingIDField.setText("BookingID");
        BookingIDField.setEnabled(false);
        BookingIDField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookingIDFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(CarDetailsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addComponent(RentalRateField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(RentalLocationField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(RentalRateLabel)
                            .addComponent(CarColourLabel)
                            .addComponent(CarBrandLabel)
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CarColourField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(FuelTypeLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TransmissionLabel)
                                    .addComponent(SeatCapacityField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(BookingIDLabel))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BookingIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(BookingDetailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(CarBrandField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panel2Layout.createSequentialGroup()
                                            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                                    .addComponent(StartingDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                                .addGroup(panel2Layout.createSequentialGroup()
                                                    .addComponent(StartingDateLabel)
                                                    .addGap(73, 73, 73)))
                                            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(EndingDateLabel)
                                                .addComponent(EndingDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(CarModelField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CarModelLabel))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(CarYearLabel)
                                            .addComponent(CarYearField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(SeatCapacityLabel))))
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addComponent(FuelTypeField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(RentalLocationLabel)
                                        .addComponent(TransmissionField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(21, Short.MAX_VALUE))))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BookingDetailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BookingIDLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BookingIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StartingDateLabel)
                    .addComponent(EndingDateLabel))
                .addGap(2, 2, 2)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StartingDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EndingDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CarDetailsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(CarBrandLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CarBrandField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CarModelLabel)
                    .addComponent(CarYearLabel))
                .addGap(4, 4, 4)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CarModelField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CarYearField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CarColourLabel)
                    .addComponent(SeatCapacityLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CarColourField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SeatCapacityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FuelTypeLabel)
                    .addComponent(TransmissionLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FuelTypeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TransmissionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RentalRateLabel)
                    .addComponent(RentalLocationLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RentalRateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RentalLocationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PersonalDetail.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        PersonalDetail.setText("Personal Details");

        UsernameField.setEditable(false);
        UsernameField.setText("Username");
        UsernameField.setEnabled(false);

        UsernameLabel.setText("Username");

        FullNameField.setEditable(false);
        FullNameField.setText("FullName");
        FullNameField.setEnabled(false);

        FullNameLabel.setText("FullName");

        GenderField.setEditable(false);
        GenderField.setText("Gender");
        GenderField.setEnabled(false);

        GenderLabel.setText("Gender");

        DateOfBirthLabel.setText("Date of Birth");

        DateOfBirthField.setEditable(false);
        DateOfBirthField.setText("Date of Birth");
        DateOfBirthField.setEnabled(false);

        ReceiptArea.setEditable(false);
        ReceiptArea.setEnabled(false);

        ReceiptLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        ReceiptLabel.setText("Receipt");

        jLabel2.setText("Booking Status");

        ApproveButton.setText("Approve");
        ApproveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ApproveButtonMouseClicked(evt);
            }
        });

        RejectButton.setText("Reject");
        RejectButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RejectButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(PersonalDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(panel3Layout.createSequentialGroup()
                                    .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(UsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(UsernameLabel)
                                        .addComponent(GenderLabel)
                                        .addComponent(GenderField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(FullNameLabel)
                                        .addComponent(FullNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(DateOfBirthLabel)
                                        .addComponent(DateOfBirthField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(ReceiptArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addComponent(ApproveButton)
                                .addGap(36, 36, 36)
                                .addComponent(RejectButton)))))
                .addGap(0, 23, Short.MAX_VALUE))
            .addGroup(panel3Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(ReceiptLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PersonalDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UsernameLabel)
                    .addComponent(FullNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FullNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GenderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DateOfBirthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GenderField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DateOfBirthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ReceiptLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ReceiptArea, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ApproveButton)
                    .addComponent(RejectButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(435, Short.MAX_VALUE)
                .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(432, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>                        

    private void BookingIDFieldActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void ApproveButtonMouseClicked(java.awt.event.MouseEvent evt) {                                           
        // TODO add your handling code here:
        ApproveBooking();
    }                                          

    private void RejectButtonMouseClicked(java.awt.event.MouseEvent evt) {                                          
        // TODO add your handling code here:
        RejectBooking();
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
            java.util.logging.Logger.getLogger(AdminReviewBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminReviewBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminReviewBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminReviewBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminReviewBooking().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton ApproveButton;
    private java.awt.Label BookingDetailLabel;
    private javax.swing.JTextField BookingIDField;
    private javax.swing.JLabel BookingIDLabel;
    private javax.swing.JTextField CarBrandField;
    private javax.swing.JLabel CarBrandLabel;
    private javax.swing.JTextField CarColourField;
    private javax.swing.JLabel CarColourLabel;
    private java.awt.Label CarDetailsLabel;
    private javax.swing.JTextField CarModelField;
    private javax.swing.JLabel CarModelLabel;
    private javax.swing.JTextField CarYearField;
    private javax.swing.JLabel CarYearLabel;
    private javax.swing.JTextField DateOfBirthField;
    private javax.swing.JLabel DateOfBirthLabel;
    private javax.swing.JTextField EndingDateField;
    private javax.swing.JLabel EndingDateLabel;
    private javax.swing.JTextField FuelTypeField;
    private javax.swing.JLabel FuelTypeLabel;
    private javax.swing.JTextField FullNameField;
    private javax.swing.JLabel FullNameLabel;
    private javax.swing.JTextField GenderField;
    private javax.swing.JLabel GenderLabel;
    private java.awt.Label PersonalDetail;
    private java.awt.TextArea ReceiptArea;
    private java.awt.Label ReceiptLabel;
    private javax.swing.JButton RejectButton;
    private javax.swing.JTextField RentalLocationField;
    private javax.swing.JLabel RentalLocationLabel;
    private javax.swing.JTextField RentalRateField;
    private javax.swing.JLabel RentalRateLabel;
    private javax.swing.JTextField SeatCapacityField;
    private javax.swing.JLabel SeatCapacityLabel;
    private javax.swing.JTextField StartingDateField;
    private javax.swing.JLabel StartingDateLabel;
    private javax.swing.JTextField TransmissionField;
    private javax.swing.JLabel TransmissionLabel;
    private javax.swing.JTextField UsernameField;
    private javax.swing.JLabel UsernameLabel;
    private javax.swing.JLabel jLabel2;
    private java.awt.Panel panel2;
    private java.awt.Panel panel3;
    // End of variables declaration                   
}
