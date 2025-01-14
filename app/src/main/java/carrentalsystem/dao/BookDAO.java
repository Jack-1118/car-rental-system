package carrentalsystem.dao;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Comparator;


import carrentalsystem.model.Booking;
import carrentalsystem.util.FileUtil;

public class BookDAO {

    // file path for the data file
    private static final String DATA_FILE_PATH = "app/src/main/resources/BookingData.txt";

    // Save a single Book to the data file
    public static void saveBook(Booking book) {
        FileUtil.saveFile(DATA_FILE_PATH, Collections.singletonList(book));
    }

    // Load the list of Bookings from the data file
    public static List<Booking> loadBookings() {
        List<Booking> Bookings = FileUtil.loadFile(DATA_FILE_PATH, Booking.class);
        Collections.sort(Bookings, Comparator.comparing(Booking::getCarID));
        return Bookings;
    }

    public static List<Booking> loadApprovedBookings() {
        List<Booking> bookings = FileUtil.loadFile(DATA_FILE_PATH, Booking.class);
        bookings = bookings.stream()
                        .filter(booking -> "Approved".equals(booking.getStatus()))
                        .collect(Collectors.toList());
        Collections.sort(bookings, Comparator.comparing(Booking::getCarID));
        return bookings;
    }

    // Load the bookings by user ID
    public static List<Booking> loadBookingsByUserId(String username) {
        List<Booking> bookings = FileUtil.loadFile(DATA_FILE_PATH, Booking.class);
        bookings = bookings.stream()
                        .filter(booking -> username.equals(booking.getUsername()))
                        .collect(Collectors.toList());
        Collections.sort(bookings, Comparator.comparing(Booking::getBookingID));
        return bookings;
    }

    // Load the bookings by booking ID
    public static List<Booking> loadBookingsByBookingId(int bookingID) {
        List<Booking> bookings = FileUtil.loadFile(DATA_FILE_PATH, Booking.class);
        bookings = bookings.stream()
                        .filter(booking -> bookingID == booking.getBookingID())
                        .collect(Collectors.toList());
        Collections.sort(bookings, Comparator.comparing(Booking::getBookingID));
        return bookings;
    }

    
    
    public static Booking getBookingId(int id) {
        List<Booking> books = FileUtil.loadFile(DATA_FILE_PATH, Booking.class);
    
        for (Booking book: books) {
            if (book.getBookingID() == id) {
                return book;  // returns the Booking without altering ID sequence
            }
        }
        return null;
    }


    // Get the maximum Booking ID
    public static int assignBookingID() {
        List<Booking> bookings = FileUtil.loadFile(DATA_FILE_PATH, Booking.class);
        return bookings.stream().mapToInt(Booking::getBookingID).max().orElse(0) + 1;
    }
    


    // modify the booking in the data file
    public static void modifyBooking(Booking book) {
        FileUtil.modifyRecord(DATA_FILE_PATH, Booking.class, c -> c.getBookingID() == book.getBookingID(), book);
    }

}
