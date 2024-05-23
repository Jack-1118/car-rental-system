package carrentalsystem.util;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.kernel.colors.DeviceRgb;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

import carrentalsystem.model.Booking;
import carrentalsystem.model.Car;
import carrentalsystem.dao.CarDAO;

public class PdfUtil {

    public static void generateReceipt(Booking booking) {
        Car car = CarDAO.getCarById(booking.getCarID()); // Fetch car details

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String dest = fileToSave.getAbsolutePath() + File.separator + "receipt_" + booking.getBookingID() + ".pdf";

            try {
                PdfWriter writer = new PdfWriter(dest);
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf);

                // Title
                Paragraph title = new Paragraph("Car Rental Invoice")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
                    .setFontSize(20);
                document.add(title);

                // Table with styling
                Table table = new Table(UnitValue.createPercentArray(new float[]{1, 2}))
                    .useAllAvailableWidth()
                    .setMarginTop(10);

                // Adding car details with custom styling
                if (car != null) {
                    addCellToTable(table, "Car Brand", true);
                    addCellToTable(table, car.getBrand(), false);
                    addCellToTable(table, "Model", true);
                    addCellToTable(table, car.getModel(), false);
                    addCellToTable(table, "Year", true);
                    addCellToTable(table, String.valueOf(car.getYear()), false);
                    addCellToTable(table, "Plate Number", true);
                    addCellToTable(table, car.getPlateNumber(), false);
                }

                // Customer and payment details
                addCellToTable(table, "Customer", true);
                addCellToTable(table, booking.getUsername(), false);
                addCellToTable(table, "Rental Period", true);
                addCellToTable(table, booking.getStartDate() + " to " + booking.getEndDate(), false);
                addCellToTable(table, "Amount Paid", true);
                addCellToTable(table, "$" + String.format("%.2f", booking.getAmount()), false);
                addCellToTable(table, "Payment Status", true);
                addCellToTable(table, booking.getPaymentStatus(), false);

                document.add(table);
                document.add(new Paragraph("Thank you for choosing us!").setItalic().setMarginTop(10));
                document.close();

                JOptionPane.showMessageDialog(null, "Receipt saved to: " + dest, "Receipt Saved", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "File Error: Could not save the receipt.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void addCellToTable(Table table, String content, boolean header) {
        Cell cell = new Cell().add(new Paragraph(content));
        if (header) {
            cell.setBold();
            cell.setBackgroundColor(new DeviceRgb(211, 211, 211));
        }
        cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
    }
}
