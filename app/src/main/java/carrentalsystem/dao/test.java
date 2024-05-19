package carrentalsystem.dao;

import carrentalsystem.model.Booking;
import java.util.List;


public class test {
    public static void main(String[] args) {
        List<Booking> bookings = BookDAO.loadApprovedBookings();

        for (Booking booking : bookings) {
            System.out.println("Booking ID: " + booking.getBookingID());
            System.out.println("Car ID: " + booking.getCarID());
            System.out.println("Username: " + booking.getUsername());
            System.out.println("Start Date: " + booking.getStartDate());
            System.out.println("End Date: " + booking.getEndDate());
            System.out.println("Status: " + booking.getStatus());
            System.out.println("Amount: " + booking.getAmount());
            System.out.println("Payment Status: " + booking.getPaymentStatus());
            System.out.println("----------------------------");
        }
    }
}
