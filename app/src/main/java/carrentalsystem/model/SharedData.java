package carrentalsystem.model;

public class SharedData {
    private static int bookingId;
    private static int carId;

    public static int getBookingId() {
        return bookingId;
    }

    public static int getCarId() {
        return carId;
    }

    public static void setBookingId(int id) {
        bookingId = id;
    }

    public static void setCarId(int id) {
        carId = id;
    }
}

