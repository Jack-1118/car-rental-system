package carrentalsystem.dao;


import carrentalsystem.model.Booking;
import carrentalsystem.util.PdfUtil;

import java.util.List;



public class Main {

    public static void main(String[] args) {
        List<Booking> bookings = BookDAO.loadBookings();
        if (!bookings.isEmpty()) {
            Booking firstBooking = bookings.get(0);
            PdfUtil.generateReceipt(firstBooking);
        }
 
    }
}
