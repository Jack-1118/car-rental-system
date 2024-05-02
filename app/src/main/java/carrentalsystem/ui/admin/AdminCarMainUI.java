package carrentalsystem.ui.admin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Pattern;

import carrentalsystem.model.Car;
import carrentalsystem.dao.CarDAO;
import carrentalsystem.util.FormUtil;


public class AdminCarMainUI extends JPanel {
    private JButton addButton, editButton, deleteButton, refreshButton;
    private JTable carTable;
    private JScrollPane scrollPane;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox<String> searchFieldSelector;
    private JPanel buttonPanelWrapper;
    private String[] searchableFields = {"ID", "Brand", "Model", "Year", "Plate Number", "Color", "Seats", "Fuel Type", "Transmission", "Rate", "Location"};


    public AdminCarMainUI() {
        setLayout(new BorderLayout());

        initializeComponents();
        setupListeners();
        add(buttonPanelWrapper, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }


    
    private void initializeComponents() {
        searchField = new JTextField(30);
        searchButton = new JButton("Search");
        searchFieldSelector = new JComboBox<>(searchableFields);
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(searchFieldSelector);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        addButton = new JButton("Add New Car");
        editButton = new JButton("View/Edit Selected Car");
        deleteButton = new JButton("Delete Selected Car");
        refreshButton = new JButton("Refresh List");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        buttonPanelWrapper = new JPanel(new BorderLayout());
        buttonPanelWrapper.add(buttonPanel, BorderLayout.NORTH);
        buttonPanelWrapper.add(searchPanel, BorderLayout.SOUTH);

        List<Car> cars = CarDAO.loadCars();
        DefaultTableModel model = createTableModel(cars);
        carTable = new JTable(model);
        carTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane = new JScrollPane(carTable);
    }


    
    private DefaultTableModel createTableModel(List<Car> cars) {
        String[] columnNames = {"ID", "Brand", "Model", "Year", "Plate Number", "Color", "Seats", "Fuel Type", "Transmission", "Rate", "Location"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Car car : cars) {
            model.addRow(new Object[]{
                car.getCarID(),
                car.getBrand(),
                car.getModel(),
                car.getYear(),
                car.getPlateNumber(),
                car.getColour(),
                car.getSeatCapacity(),
                car.getFuelType(),
                car.getTransmission(),
                car.getRentalRate(),
                car.getRentalLocation()
            });
        }
        return model;
    }

    private void setupListeners() {
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { updateFilter(); }
            public void removeUpdate(DocumentEvent e) { updateFilter(); }
            public void changedUpdate(DocumentEvent e) { updateFilter(); }

            private void updateFilter() {
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) carTable.getModel());
                carTable.setRowSorter(sorter);
                String searchText = searchField.getText().trim();
                if (searchText.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    int colIndex = searchFieldSelector.getSelectedIndex();
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(searchText), colIndex));
                }
            }
        });

        addButton.addActionListener(e -> showAddCarDialog());
        editButton.addActionListener(e -> showEditCarDialog());
        deleteButton.addActionListener(e -> showDeleteCarDialog());
        refreshButton.addActionListener(e -> refreshCarList());
    }




    // Method to show a dialog for adding a new car
    private void showAddCarDialog() {
        // Create a dialog that is modal and blocks user input for other top-level windows
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Add New Car", Dialog.ModalityType.APPLICATION_MODAL);

        // Panel to hold the form fields
        JPanel formPanel = new JPanel(new GridLayout(0, 2)); // 0 rows, 2 columns. Rows increase dynamically

        // Create labels and text fields for car information
        JTextField brandField = new JTextField(20);
        JTextField modelField = new JTextField(20);
        JFormattedTextField yearField = FormUtil.createIntegerField(3000);
        JTextField plateNumberField = new JTextField(20);
        JTextField colourField = new JTextField(20);
        JFormattedTextField seatCapacityField = FormUtil.createIntegerField(500);
        JTextField fuelTypeField = new JTextField(20);
        JTextField transmissionField = new JTextField(20);
        JFormattedTextField rentalRateField = FormUtil.createIntegerField(99999);
        JTextField rentalLocationField = new JTextField(20);

        // Add form fields to the formPanel
        formPanel.add(new JLabel("Brand:"));
        formPanel.add(brandField);
        formPanel.add(new JLabel("Model:"));
        formPanel.add(modelField);
        formPanel.add(new JLabel("Year:"));
        formPanel.add(yearField);
        formPanel.add(new JLabel("Plate Number:"));
        formPanel.add(plateNumberField);
        formPanel.add(new JLabel("Color:"));
        formPanel.add(colourField);
        formPanel.add(new JLabel("Seat Capacity:"));
        formPanel.add(seatCapacityField);
        formPanel.add(new JLabel("Fuel Type:"));
        formPanel.add(fuelTypeField);
        formPanel.add(new JLabel("Transmission:"));
        formPanel.add(transmissionField);
        formPanel.add(new JLabel("Rental Rate:"));
        formPanel.add(rentalRateField);
        formPanel.add(new JLabel("Rental Location:"));
        formPanel.add(rentalLocationField);


        // Buttons for submitting or cancelling
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        // Listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate and create car object
                Car car = new Car();
                car.setCarID(CarDAO.assignCarID());
                car.setBrand(brandField.getText());
                car.setModel(modelField.getText());
                car.setYear((Integer) yearField.getValue());
                car.setPlateNumber(plateNumberField.getText());
                car.setColour(colourField.getText());
                car.setSeatCapacity((Integer) seatCapacityField.getValue());
                car.setFuelType(fuelTypeField.getText());
                car.setTransmission(transmissionField.getText());
                car.setRentalRate((Integer) rentalRateField.getValue());
                car.setRentalLocation(rentalLocationField.getText());

                CarDAO.saveCar(car);
                JOptionPane.showMessageDialog(dialog, "Car added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();

                refreshCarList();
            }
        });

        // Listener for the cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // Dispose of the dialog, effectively cancelling the operation
            }
        });

        // Panel for holding the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);

        // Arrange panels in the dialog
        dialog.setLayout(new BorderLayout());
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.PAGE_END);

        // Prepare and show the dialog
        dialog.pack();
        dialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor(this));
        dialog.setVisible(true);
    }


    // Method to show a dialog for editing a car
    private void showEditCarDialog() {
        int selectedRow = carTable.getSelectedRow();
        if (selectedRow >= 0) {
            // Assume car ID is stored in the first column
            int carID = Integer.parseInt(carTable.getValueAt(selectedRow, 0).toString());
            Car carToEdit = CarDAO.getCarById(carID); // This method should be defined in CarDAO
    
            if (carToEdit != null) {
                JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Edit Car", Dialog.ModalityType.APPLICATION_MODAL);
                JPanel formPanel = new JPanel(new GridLayout(0, 2));
    
                // Pre-fill the form fields with car information
                JTextField brandField = new JTextField(carToEdit.getBrand(), 20);
                JTextField modelField = new JTextField(carToEdit.getModel(), 20);
                JFormattedTextField yearField = FormUtil.createIntegerField(3000);
                yearField.setValue(carToEdit.getYear());
                JTextField plateNumberField = new JTextField(carToEdit.getPlateNumber(), 20);
                JTextField colourField = new JTextField(carToEdit.getColour(), 20);
                JFormattedTextField seatCapacityField = FormUtil.createIntegerField(500);
                seatCapacityField.setValue(carToEdit.getSeatCapacity());
                JTextField fuelTypeField = new JTextField(carToEdit.getFuelType(), 20);
                JTextField transmissionField = new JTextField(carToEdit.getTransmission(), 20);
                JFormattedTextField rentalRateField = FormUtil.createIntegerField(99999); // Assuming rental rate is a double
                rentalRateField.setValue(carToEdit.getRentalRate());
                JTextField rentalLocationField = new JTextField(carToEdit.getRentalLocation(), 20);
                
                // ... Add components to formPanel ...
                formPanel.add(new JLabel("Brand:"));
                formPanel.add(brandField);
                formPanel.add(new JLabel("Model:"));
                formPanel.add(modelField);
                formPanel.add(new JLabel("Year:"));
                formPanel.add(yearField);
                formPanel.add(new JLabel("Plate Number:"));
                formPanel.add(plateNumberField);
                formPanel.add(new JLabel("Color:"));
                formPanel.add(colourField);
                formPanel.add(new JLabel("Seat Capacity:"));
                formPanel.add(seatCapacityField);
                formPanel.add(new JLabel("Fuel Type:"));
                formPanel.add(fuelTypeField);
                formPanel.add(new JLabel("Transmission:"));
                formPanel.add(transmissionField);
                formPanel.add(new JLabel("Rental Rate:"));
                formPanel.add(rentalRateField);
                formPanel.add(new JLabel("Rental Location:"));
                formPanel.add(rentalLocationField);
    
                // Buttons for submitting or cancelling
                JButton submitButton = new JButton("Save Changes");
                JButton cancelButton = new JButton("Cancel");
    
                // Submit button updates the car information
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Validation should be performed before updating
                        carToEdit.setBrand(brandField.getText());
                        carToEdit.setModel(modelField.getText());
                        carToEdit.setYear((Integer) yearField.getValue());
                        carToEdit.setPlateNumber(plateNumberField.getText());
                        carToEdit.setColour(colourField.getText());
                        carToEdit.setSeatCapacity((Integer) seatCapacityField.getValue());
                        carToEdit.setFuelType(fuelTypeField.getText());
                        carToEdit.setTransmission(transmissionField.getText());
                        carToEdit.setRentalRate((Double) rentalRateField.getValue());
                        carToEdit.setRentalLocation(rentalLocationField.getText());
    
                        // Save changes
                        CarDAO.modifyCar(carToEdit);
                        JOptionPane.showMessageDialog(dialog, "Car details updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dialog.dispose();
                        refreshCarList();

                    }
                });
    
                // Cancel button simply closes the dialog
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                    }
                });
    
                // Panel for holding the buttons
                JPanel buttonPanel = new JPanel();
                buttonPanel.add(submitButton);
                buttonPanel.add(cancelButton);
    
                // Arrange panels in the dialog
                dialog.setLayout(new BorderLayout());
                dialog.add(formPanel, BorderLayout.CENTER);
                dialog.add(buttonPanel, BorderLayout.PAGE_END);
    
                // Show the dialog
                dialog.pack();
                dialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor(this));
                dialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Selected car could not be found for editing.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No car selected. Please select a car to edit.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }


    private void showDeleteCarDialog() {
        int selectedRow = carTable.getSelectedRow();
        if (selectedRow >= 0) {
            // Convert the view index to the model index in case sorting/filtering is applied
            int modelRow = carTable.convertRowIndexToModel(selectedRow);
            // Assume car ID is stored in the first column
            int carID = Integer.parseInt(carTable.getModel().getValueAt(modelRow, 0).toString());
            Car carToDelete = CarDAO.getCarById(carID); // This method should be defined in CarDAO
    
            if (carToDelete != null) {
                // Confirm dialog, display all details
                int response = JOptionPane.showConfirmDialog(
                    SwingUtilities.getWindowAncestor(this),
                    "Are you sure you want to delete the car?\n" +
                    "ID: " + carToDelete.getCarID() + "\n" +
                    "Brand: " + carToDelete.getBrand() + "\n" +
                    "Model: " + carToDelete.getModel() + "\n" +
                    "Year: " + carToDelete.getYear() + "\n" +
                    "Plate Number: " + carToDelete.getPlateNumber() + "\n" +
                    "Colour: " + carToDelete.getColour() + "\n" +
                    "Seat Capacity: " + carToDelete.getSeatCapacity() + "\n" +
                    "Fuel Type: " + carToDelete.getFuelType() + "\n" +
                    "Transmission: " + carToDelete.getTransmission() + "\n" +
                    "Rental Rate: " + carToDelete.getRentalRate() + "\n" +
                    "Location: " + carToDelete.getRentalLocation() + "\n" +
                    "This action cannot be undone.",

                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );
    
                if (response == JOptionPane.YES_OPTION) {
                    CarDAO.deleteCar(carToDelete);
                    JOptionPane.showMessageDialog(this, "Car deleted successfully.", "Deletion Successful", JOptionPane.INFORMATION_MESSAGE);
                    refreshCarList();

                }
            } else {
                JOptionPane.showMessageDialog(this, "Selected car could not be found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No car selected. Please select a car to delete.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void refreshCarList() {
        List<Car> cars = CarDAO.loadCars();
        DefaultTableModel model = createTableModel(cars);
        carTable.setModel(model);
    }
    
}
