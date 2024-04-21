package carrentalsystem.dao;

import carrentalsystem.model.Car;

public class Main {
    public void testSaveCar() {
        Car car = new Car("Toyota", "Vios",     2018, "ABC1234", "Red", 5, "Petrol", "Auto", true, 100.0, "KL");
        CarDAO carDAO = new CarDAO();
        carDAO.saveCar(car);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.testSaveCar();
    }
}
