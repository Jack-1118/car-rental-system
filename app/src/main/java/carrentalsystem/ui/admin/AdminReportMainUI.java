package carrentalsystem.ui.admin;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.PlotOrientation;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class AdminReportMainUI extends JPanel {
    private JComboBox<String> cbTimeUnit, cbPeriod, cbReportType;
    private JButton btnGenerate;

    public AdminReportMainUI() {
        initializeUI();
        setLayout(new BorderLayout());
        add(createControlPanel(), BorderLayout.NORTH);
    }

    private void initializeUI() {
        cbTimeUnit = new JComboBox<>(new String[]{"","Month", "Year"});
        cbPeriod = new JComboBox<>(); // This will be dynamically populated based on cbTimeUnit selection
        cbReportType = new JComboBox<>(new String[]{"Most Rented Car", "Revenue Generated", "Peak Rental Period", "Utilization Rate"});
        btnGenerate = new JButton("Generate Report");

        cbTimeUnit.addActionListener(e -> updatePeriodOptions());
        btnGenerate.addActionListener(e -> generateReport());
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 5));
        panel.add(new JLabel("Time Unit:"));
        panel.add(cbTimeUnit);
        panel.add(new JLabel("Period:"));
        panel.add(cbPeriod);
        panel.add(cbReportType);
        panel.add(btnGenerate);
        return panel;
    }

    private void updatePeriodOptions() {
        cbPeriod.removeAllItems();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        if (cbTimeUnit.getSelectedItem().equals("Month")) {
            for (int i = 1; i <= 12; i++) {
                cbPeriod.addItem(String.format("%d", i));
            }
        } else if (cbTimeUnit.getSelectedItem().equals("Year")) {
            for (int i = year - 10; i <= year; i++) {
                cbPeriod.addItem(String.format("%d", i));
            }
        }
    }

    private void generateReport() {
        String selectedPeriod = (String) cbPeriod.getSelectedItem();
        String reportType = (String) cbReportType.getSelectedItem();
        
        // Here you should add logic to fetch data based on these selections
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // TODO:Dummy data logic, replace with real data fetching
        dataset.addValue(200, "Toyota Camry", selectedPeriod);
        dataset.addValue(150, "Honda Accord", selectedPeriod);

        JFreeChart chart = ChartFactory.createBarChart(
                "Car Rental Statistics - " + reportType, // Chart title
                "Category", // Domain axis label
                "Value", // Range axis label
                dataset, // Data
                PlotOrientation.VERTICAL, // Orientation
                true, // Include legend
                true, // Tooltips?
                false // URLs?
        );
        ChartPanel chartPanel = new ChartPanel(chart);
        if (this.getComponentCount() > 1) {
            this.remove(1); // Remove previous chart
        }
        this.add(chartPanel, BorderLayout.CENTER);
        this.validate();
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
