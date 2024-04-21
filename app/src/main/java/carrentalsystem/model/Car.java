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
    private boolean available;
    private double rentalRate;
    private String rentalLocation;


    // constructor
    public Car() {
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
    
    public boolean getAvailable() {
        return available;
    }
    
    public double getRentalRate() {
        return rentalRate;
    }
    
    public String getRentalLocation() {
        return rentalLocation;
    }

    //setters
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setSeatCapacity(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setRentalRate(double rentalRate) {
        this.rentalRate = rentalRate;
    }

    public void setRentalLocation(String rentalLocation) {
        this.rentalLocation = rentalLocation;
    }

}
