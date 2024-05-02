package carrentalsystem.dao;


import carrentalsystem.model.Car;



public class Main {

    public static void main(String[] args) {
        Car car = new Car();
        car = CarDAO.getCarById(2);
        System.out.println(car.getCarID());

        
    }
}
