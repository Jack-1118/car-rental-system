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

    // Search car by respective field
    public static List<Car> searchCar(String field, String value) {
        List<Car> cars = CarDAO.loadCars();

        // Search by field, loop through the list, check if the field matches the value
        List<Car> searchResults = null;
        


        return searchResults;
    }
    


}
