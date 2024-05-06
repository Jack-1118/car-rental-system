package carrentalsystem.service;

import java.util.List;

import carrentalsystem.dao.CarDAO;
import carrentalsystem.model.Car;


public class CarService {
    
    //Add new car
    public static void addNewCar(Car car) {
        CarDAO.saveCar(car);
    }


    //View all cars
    public static List<Car> viewCars() {
        return CarDAO.loadCars();
    }

    


}
