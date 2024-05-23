package carrentalsystem.ui.admin;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.PlotOrientation;


import java.time.format.DateTimeFormatter;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import carrentalsystem.dao.BookDAO;
import carrentalsystem.dao.CarDAO;
import carrentalsystem.model.Booking;

public class AdminReportMainUI extends JPanel {
    private JComboBox<String> cbPeriod, cbReportType;
    private JButton btnGenerate;

    public AdminReportMainUI() {
        initializeUI();
        setLayout(new BorderLayout());
        add(createControlPanel(), BorderLayout.NORTH);
    }

    private void initializeUI() {
        cbPeriod = new JComboBox<>();
        int startYear = 2023; 
        int currentYear = LocalDate.now().getYear();
        for (int i = startYear; i <= currentYear; i++) {
            cbPeriod.addItem(String.valueOf(i));
        }

        cbReportType = new JComboBox<>(new String[]{"Most Rented Car", "Revenue Generated"});
        btnGenerate = new JButton("Generate Report");

        
        btnGenerate.addActionListener(e -> generateReport());
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 5));
        panel.add(new JLabel("Period:"));
        panel.add(cbPeriod);
        panel.add(cbReportType);
        panel.add(btnGenerate);
        return panel;
    }
    

    private void generateReport() {
        String selectedPeriod = (String) cbPeriod.getSelectedItem();
        String reportType = (String) cbReportType.getSelectedItem();
        List<Booking> bookings = BookDAO.loadBookings();


        // Summarize data based on the report type
        DefaultCategoryDataset dataset = createDataset(bookings, reportType, selectedPeriod);

        // Create and display the chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Car Rental Statistics - " + reportType,
                "Category",
                "Value",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        updateChart(chart);
    }


    private DefaultCategoryDataset createDataset(List<Booking> bookings, String reportType, String selectedYear) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Determine the current year to handle month-by-month revenue if applicable
        int currentYear = LocalDate.now().getYear();
        boolean isCurrentYear = Integer.parseInt(selectedYear) == currentYear;

        // Filter bookings based on the selected year
        List<Booking> filteredBookings = bookings.stream()
            .filter(b -> {
                LocalDate date = LocalDate.parse(b.getStartDate(), formatter);
                return date.getYear() == Integer.parseInt(selectedYear);
            })
            .collect(Collectors.toList());

        switch (reportType) {
            case "Most Rented Car":
                Map<String, Long> brandUsageCount = filteredBookings.stream()
                    .collect(Collectors.groupingBy(booking -> CarDAO.getCarById(booking.getCarID()).getBrand(),
                        Collectors.counting()));
                brandUsageCount.forEach((brand, count) ->
                    dataset.addValue(count, "Rentals", brand));
                break;
            case "Revenue Generated":
                if (isCurrentYear) {
                    for (int month = 1; month <= LocalDate.now().getMonthValue(); month++) {
                        int finalMonth = month;
                        double monthlyRevenue = filteredBookings.stream()
                            .filter(b -> LocalDate.parse(b.getStartDate(), formatter).getMonthValue() == finalMonth)
                            .mapToDouble(Booking::getAmount)
                            .sum();
                        dataset.addValue(monthlyRevenue, "Revenue", "Month " + month);
                    }
                } else {
                    double totalRevenue = filteredBookings.stream()
                        .mapToDouble(Booking::getAmount)
                        .sum();
                    dataset.addValue(totalRevenue, "Revenue", "Total Revenue");
                }
                break;
        }

        return dataset;
    }
    
    

    private void updateChart(JFreeChart chart) {
        ChartPanel chartPanel = new ChartPanel(chart);
        if (this.getComponentCount() > 1) {
            this.remove(1); 
        }
        this.add(chartPanel, BorderLayout.CENTER);
        this.validate();
        this.repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Admin Reports");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new AdminReportMainUI());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
