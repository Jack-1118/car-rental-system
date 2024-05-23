package carrentalsystem.dao;

import carrentalsystem.model.Booking;
import java.util.List;

import carrentalsystem.model.Car;
import carrentalsystem.util.FileUtil;


public class test {
    public static void main(String[] args) {
        Car car1 = new Car();
        car1.setCarID(1);
        car1.setBrand("Toyota");
        car1.setModel("Corolla");
        car1.setYear(2019);
        car1.setPlateNumber("ABC1234");
        car1.setColour("Red");
        car1.setSeatCapacity(5);
        car1.setFuelType("Petrol");
        car1.setTransmission("Automatic");
        car1.setRentalRate(50.0);
        car1.setRentalLocation("Location A");

        Car car2 = new Car();
        car2.setCarID(2);
        car2.setBrand("Honda");
        car2.setModel("Civic");
        car2.setYear(2020);
        car2.setPlateNumber("DEF5678");
        car2.setColour("Blue");
        car2.setSeatCapacity(5);
        car2.setFuelType("Petrol");
        car2.setTransmission("Automatic");
        car2.setRentalRate(55.0);
        car2.setRentalLocation("Location B");

        Car car3 = new Car();
        car3.setCarID(3);
        car3.setBrand("Ford");
        car3.setModel("Focus");
        car3.setYear(2018);
        car3.setPlateNumber("GHI9012");
        car3.setColour("White");
        car3.setSeatCapacity(5);
        car3.setFuelType("Diesel");
        car3.setTransmission("Manual");
        car3.setRentalRate(45.0);
        car3.setRentalLocation("Location C");

        Car car4 = new Car();
        car4.setCarID(4);
        car4.setBrand("Chevrolet");
        car4.setModel("Malibu");
        car4.setYear(2021);
        car4.setPlateNumber("JKL3456");
        car4.setColour("Black");
        car4.setSeatCapacity(5);
        car4.setFuelType("Petrol");
        car4.setTransmission("Automatic");
        car4.setRentalRate(60.0);
        car4.setRentalLocation("Location D");

        Car car5 = new Car();
        car5.setCarID(5);
        car5.setBrand("Nissan");
        car5.setModel("Altima");
        car5.setYear(2017);
        car5.setPlateNumber("MNO7890");
        car5.setColour("Silver");
        car5.setSeatCapacity(5);
        car5.setFuelType("Petrol");
        car5.setTransmission("Automatic");
        car5.setRentalRate(50.0);
        car5.setRentalLocation("Location E");

        Car car6 = new Car();
        car6.setCarID(6);
        car6.setBrand("BMW");
        car6.setModel("3 Series");
        car6.setYear(2022);
        car6.setPlateNumber("PQR1234");
        car6.setColour("Gray");
        car6.setSeatCapacity(5);
        car6.setFuelType("Petrol");
        car6.setTransmission("Automatic");
        car6.setRentalRate(70.0);
        car6.setRentalLocation("Location F");

        Car car7 = new Car();
        car7.setCarID(7);
        car7.setBrand("Audi");
        car7.setModel("A4");
        car7.setYear(2019);
        car7.setPlateNumber("STU5678");
        car7.setColour("Blue");
        car7.setSeatCapacity(5);
        car7.setFuelType("Petrol");
        car7.setTransmission("Automatic");
        car7.setRentalRate(65.0);
        car7.setRentalLocation("Location G");

        Car car8 = new Car();
        car8.setCarID(8);
        car8.setBrand("Mercedes-Benz");
        car8.setModel("C-Class");
        car8.setYear(2020);
        car8.setPlateNumber("VWX9012");
        car8.setColour("Black");
        car8.setSeatCapacity(5);
        car8.setFuelType("Petrol");
        car8.setTransmission("Automatic");
        car8.setRentalRate(75.0);
        car8.setRentalLocation("Location H");

        Car car9 = new Car();
        car9.setCarID(9);
        car9.setBrand("Hyundai");
        car9.setModel("Elantra");
        car9.setYear(2018);
        car9.setPlateNumber("YZA3456");
        car9.setColour("Red");
        car9.setSeatCapacity(5);
        car9.setFuelType("Petrol");
        car9.setTransmission("Automatic");
        car9.setRentalRate(50.0);
        car9.setRentalLocation("Location I");

        Car car10 = new Car();
        car10.setCarID(10);
        car10.setBrand("Kia");
        car10.setModel("Optima");
        car10.setYear(2017);
        car10.setPlateNumber("BCD7890");
        car10.setColour("White");
        car10.setSeatCapacity(5);
        car10.setFuelType("Petrol");
        car10.setTransmission("Automatic");
        car10.setRentalRate(45.0);
        car10.setRentalLocation("Location J");

        // Save all cars
        CarDAO.saveCar(car1);
        CarDAO.saveCar(car2);
        CarDAO.saveCar(car3);
        CarDAO.saveCar(car4);
        CarDAO.saveCar(car5);
        CarDAO.saveCar(car6);
        CarDAO.saveCar(car7);
        CarDAO.saveCar(car8);
        CarDAO.saveCar(car9);
        CarDAO.saveCar(car10);
    }
}

