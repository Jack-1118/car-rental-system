package carrentalsystem.ui.user;

import java.util.List;



import carrentalsystem.dao.BookDAO;
import carrentalsystem.dao.CarDAO;
import carrentalsystem.dao.UserDAO;
import carrentalsystem.model.Booking;
import carrentalsystem.model.Car;
import carrentalsystem.model.SharedData;
import carrentalsystem.model.User;
import carrentalsystem.ui.BasePanel;
import carrentalsystem.util.PdfUtil;

public class UserBookingDetailsUI extends BasePanel{
    private int currentBookingId; // Unset state is -1
    private int currentCarId; // Unset state is -1


    /**
     * Creates new form UserPaymentUI
     */
    public UserBookingDetailsUI() {
                this.currentBookingId = SharedData.getBookingId();
                this.currentCarId = SharedData.getCarId();

                // Now call initComponents which might use currentBookingId and currentCarId
                initComponents();
                renderBookingData();
                checkPaymentStatus();

                // Test
                System.out.println("Current Booking ID: " + currentBookingId);
                System.out.println("Current Car ID: " + currentCarId);
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
        String PaymentStatus = null;
        String BookingStatus = null;
        String Username = null;

        RentalLocationLabel.setText("Rental Location");

        List<Booking> bookingList = BookDAO.loadBookings();
        for (Booking booking : bookingList) {
            if (booking.getBookingID() == currentBookingId) {
                StartingDate = (booking.getStartDate());
                EndingDate = (booking.getEndDate());
                TotalAmount = (booking.getAmount());
                PaymentStatus = (booking.getPaymentStatus());
                BookingStatus = (booking.getStatus());
                Username = (booking.getUsername());
            }
        }

        StartingDateField.setText(StartingDate);
        EndingDateField.setText(EndingDate);
        PaymentStatusField.setText(PaymentStatus);
        BookingStatusField.setText(BookingStatus);

        
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

    private void BackButton(){
        UserBookingHistoryUI userBookingHistoryUI = new UserBookingHistoryUI();
        userBookingHistoryUI.setVisible(true);
        dispose();
    }
    
    private void checkPaymentStatus(){
        String PaymentStatusCheck = "";
        String BookingStatusCheck = "";

        List<Booking> bookingList = BookDAO.loadBookings();
        for (Booking booking : bookingList) {
            if (booking.getBookingID() == currentBookingId) {
                PaymentStatusCheck = (booking.getPaymentStatus());
                BookingStatusCheck = (booking.getStatus());
            }
        }

        if(PaymentStatusCheck.equals("Paid") && BookingStatusCheck.equals("Approved")){
            PrintReceiptButton.setEnabled(true);
            PrintReceiptButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    PrintReceiptButtonMouseClicked(evt);
                }
            });
        }

    }
    
    private void generateReceipt(int currentBookingId){
        Booking currentBooking = BookDAO.getBookingId(currentBookingId);
        PdfUtil.generateReceipt(currentBooking);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
        PaymentDetailsLabel = new java.awt.Label();
        PaymentStatusField = new javax.swing.JTextField();
        PaymentStatusLabel = new javax.swing.JLabel();
        BookingStatusField = new javax.swing.JTextField();
        BookingStatusLabel = new java.awt.Label();
        panel4 = new java.awt.Panel();
        ReceiptArea = new java.awt.TextArea();        
        ReceiptLabel = new java.awt.Label();
        PaymentPageLabel = new java.awt.Label();
        BackButton = new javax.swing.JButton();
        PrintReceiptButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

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
        BookingIDField.setText("currentBookingId");
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
        DateOfBirthField.setText("DateOfBirth");
        DateOfBirthField.setEnabled(false);


        PaymentDetailsLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        PaymentDetailsLabel.setText("Payment Details");

        

        PaymentStatusField.setEditable(false);
        PaymentStatusField.setText("PaymentStatus");
        PaymentStatusField.setEnabled(false);
        PaymentStatusField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PaymentStatusFieldKeyPressed(evt);
            }
        });


        PaymentStatusLabel.setText("Payment Status");

        BookingStatusField.setEditable(false);
        BookingStatusField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        BookingStatusField.setText("BookingStatus");
        BookingStatusField.setToolTipText("");
        BookingStatusField.setEnabled(false);
        BookingStatusField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BookingStatusFieldKeyPressed(evt);
            }
        });

        BookingStatusLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        BookingStatusLabel.setText("Booking Status");

        PrintReceiptButton.setText("Print Receipt");
        PrintReceiptButton.setEnabled(false);
   
        

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel3Layout.createSequentialGroup()
                        .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel3Layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addComponent(PersonalDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel3Layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(PaymentDetailsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PaymentStatusField)
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(PaymentStatusLabel)
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
                                            .addComponent(DateOfBirthField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 6, Short.MAX_VALUE))
                            .addComponent(BookingStatusField))))
                .addGap(22, 22, 22))
            .addGroup(panel3Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(BookingStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(PrintReceiptButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(PaymentDetailsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(PaymentStatusLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PaymentStatusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(BookingStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BookingStatusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(PrintReceiptButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ReceiptLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        ReceiptLabel.setText("Receipt");

        ReceiptArea.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        ReceiptArea.setEditable(false);
        ReceiptArea.setEnabled(false);
        ReceiptArea.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        ReceiptArea.setName(""); // NOI18N
        ReceiptArea.setText("Receipt");

       

        javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ReceiptLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
            .addGroup(panel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ReceiptArea, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ReceiptLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ReceiptArea, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        PaymentPageLabel.setAlignment(java.awt.Label.CENTER);
        PaymentPageLabel.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        PaymentPageLabel.setText("Booking Details");

        BackButton.setText("<Back");
        BackButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BackButtonMouseClicked(evt);
            }
        });


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BackButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PaymentPageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BackButton)
                        .addGap(80, 80, 80))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PaymentPageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BookingIDFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookingIDFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BookingIDFieldActionPerformed

    private void PaymentStatusFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PaymentStatusFieldKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PaymentStatusFieldKeyPressed

    private void BookingStatusFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BookingStatusFieldKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BookingStatusFieldKeyPressed

    private void PrintReceiptButtonMouseClicked(java.awt.event.MouseEvent evt) {                                                
        // TODO add your handling code here:
        generateReceipt(currentBookingId);
    }//GEN-LAST:event_BookingStatusFieldKeyPressed

    private void BackButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackButtonMouseClicked
        // TODO add your handling code here:
        BackButton();
    }//GEN-LAST:event_BackButtonMouseClicked
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
            java.util.logging.Logger.getLogger(UserBookingDetailsUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserBookingDetailsUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserBookingDetailsUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserBookingDetailsUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserBookingDetailsUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private java.awt.Label BookingDetailLabel;
    private javax.swing.JTextField BookingIDField;
    private javax.swing.JLabel BookingIDLabel;
    private javax.swing.JTextField BookingStatusField;
    private java.awt.Label BookingStatusLabel;
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
    private java.awt.Label PaymentDetailsLabel;
    private java.awt.Label PaymentPageLabel;
    private javax.swing.JTextField PaymentStatusField;
    private javax.swing.JLabel PaymentStatusLabel;
    private java.awt.Label PersonalDetail;
    private java.awt.Label ReceiptLabel;
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
    private java.awt.Panel panel2;
    private java.awt.Panel panel3;
    private java.awt.Panel panel4;
    private java.awt.TextArea ReceiptArea;
    private javax.swing.JButton PrintReceiptButton;
    // End of variables declaration//GEN-END:variables
}



