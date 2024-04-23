package carrentalsystem.ui.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import carrentalsystem.model.Car;
import carrentalsystem.service.CarService;

public class AdminCarMainUI extends JFrame implements ActionListener {
    private JTextField filterTextField;
    private JButton filterButton;
    private JTable carTable;
    private JScrollPane scrollPane;

    public AdminCarMainUI() {
        // Set frame properties
        setTitle("Car Management");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on screen

        // Create filter bar
        JPanel filterBar = new JPanel();
        filterBar.setLayout(new FlowLayout(FlowLayout.LEFT));

        filterTextField = new JTextField(20);
        filterButton = new JButton("Filter");
        filterButton.addActionListener(this);

        filterBar.add(filterTextField);
        filterBar.add(filterButton);

        // Load cars using CarService.viewCars()
        List<Car> cars = CarService.viewCars();

        // Create table model and table
        String[] columnNames = {"ID", "Brand", "Model", "Year", "Color", "Seat Capacity", "Fuel Type", "Transmission"};
        Object[][] data = new Object[cars.size()][8];

        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            data[i][0] = car.getCarID();
            data[i][1] = car.getBrand();
            data[i][2] = car.getModel();
            data[i][3] = car.getYear();
            data[i][4] = car.getColour();
            data[i][5] = car.getSeatCapacity();
            data[i][6] = car.getFuelType();
            data[i][7] = car.getTransmission();
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        carTable = new JTable(tableModel);

        // Add scroll pane to frame
        scrollPane = new JScrollPane(carTable);
        add(scrollPane, BorderLayout.CENTER);

        // Add filter bar to frame
        add(filterBar, BorderLayout.NORTH);

        // Display the frame
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == filterButton) {
            String filterText = filterTextField.getText().trim();
            if (!filterText.isEmpty()) {
                // Filter the cars based on the filter text
                List<Car> filteredCars = CarService.viewCars();
                System.out.println("Filtering cars based on: " + filterText);

                // Update the table model with the filtered cars
                DefaultTableModel tableModel = (DefaultTableModel) carTable.getModel();
                tableModel.setRowCount(0);

                for (Car car : filteredCars) {
                    Object[] row = {car.getCarID(), car.getBrand(), car.getModel(), car.getYear(), car.getColour(), car.getSeatCapacity(), car.getFuelType(), car.getTransmission()};
                    tableModel.addRow(row);
                }
            } else {
                // Reload the original list of cars if the filter text is empty
                List<Car> cars = CarService.viewCars();

                DefaultTableModel tableModel = (DefaultTableModel) carTable.getModel();
                tableModel.setRowCount(0);

                for (Car car : cars) {
                    Object[] row = {car.getCarID(), car.getBrand(), car.getModel(), car.getYear(), car.getColour(), car.getSeatCapacity(), car.getFuelType(), car.getTransmission()};
                    tableModel.addRow(row);
                }
            }
        }
    }

    public static void main(String[] args) {
        new AdminCarMainUI();
    }
}

