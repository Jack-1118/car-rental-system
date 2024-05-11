package carrentalsystem.model;


public class Booking {
    // Private Information
    private int bookingID;
    private int carID;
    private String username;
    private String startDate;
    private String endDate;
    private String status;
    private double amount;
    private String paymentStatus;

    // constructor
    public Booking() {
    }

    public Booking(int bookingID, int carID, String username, String startDate, String endDate, String status, double amount, String paymentStatus) {
        this.bookingID = bookingID;
        this.carID = carID;
        this.username = username;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }

    //getter
    public int getBookingID() {
        return bookingID;
    }

    public int getCarID() {
        return carID;
    }

    public String getUsername() {
        return username;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }



    //Setter
    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setStartdate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
}
