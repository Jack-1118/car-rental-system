package carrentalsystem.dao;


import carrentalsystem.model.Car;


public class Main {

    public static void main(String[] args) {
        Car cars = new Car();
        cars.setBrand("Toyota");
        cars.setModel("Corolla");
        cars.setYear(2015);
        cars.setPlateNumber("ABC123");
        cars.setColour("Red");
        cars.setSeatCapacity(4);
        cars.setFuelType("Petrol");
        cars.setTransmission("Automatic");
        cars.setAvailable(true);
        cars.setRentalRate(100.0);
        cars.setRentalLocation("Sydney");
        CarDAO.saveCar(cars);
        
    }
}
