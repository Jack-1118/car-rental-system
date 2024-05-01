package carrentalsystem.model;


public class Booking {
    // Private Information
    private int bookingID;
    private int carID;
    private String username;
    private String startDate;
    private String endDate;

    // constructor
    public Booking() {
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
    
}
