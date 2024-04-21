package carrentalsystem.model;

public class Car {
    
    // car information
    private String brand;
    private String model;
    private int year;
    private String plateNumber;
    private String colour;
    private int seatCapacity;
    private String fuelType;
    private String transmission;


    // rental information
    private boolean isAvailable;
    private double rentalRate;
    private String rentalLocation;


    // constructor
    public Car(String brand, String model, int year, String plateNumber, String colour, int seatCapacity, String fuelType, String transmission, boolean isAvailable, double rentalRate, String rentalLocation) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.plateNumber = plateNumber;
        this.colour = colour;
        this.seatCapacity = seatCapacity;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.isAvailable = isAvailable;
        this.rentalRate = rentalRate;
        this.rentalLocation = rentalLocation;
    }

    //getters
    public String getBrand() {
        return brand;
    }
    
    public String getModel() {
        return model;
    }
    
    public int getYear() {
        return year;
    }
    
    public String getPlateNumber() {
        return plateNumber;
    }
    
    public String getColour() {
        return colour;
    }
    
    public int getSeatCapacity() {
        return seatCapacity;
    }
    
    public String getFuelType() {
        return fuelType;
    }
    
    public String getTransmission() {
        return transmission;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public double getRentalRate() {
        return rentalRate;
    }
    
    public String getRentalLocation() {
        return rentalLocation;
    }

}
