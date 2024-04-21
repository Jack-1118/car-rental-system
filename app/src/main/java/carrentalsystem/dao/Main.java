package carrentalsystem.dao;

import carrentalsystem.model.Car;

public class Main {

    public static void main(String[] args) {
        Car car1 = new Car();
        car1.setBrand("Honda");
        car1.setModel("Corolla");
        car1.setYear(2015);
        car1.setPlateNumber("ABC123");
        car1.setColour("Red");
        car1.setSeatCapacity(5);
        car1.setFuelType("Petrol");
        car1.setTransmission("Auto");
        car1.setAvailable(true);
        car1.setRentalRate(50.0);
        car1.setRentalLocation("Auckland");


        CarDAO carDAO = new CarDAO();
        Car car2 = carDAO.loadCars().get(3);
        System.out.println(car2.getBrand());



    }
}
