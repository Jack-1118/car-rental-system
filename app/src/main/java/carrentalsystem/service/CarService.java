package carrentalsystem.service;

import java.util.List;

import carrentalsystem.dao.CarDAO;
import carrentalsystem.model.Car;


public class CarService {
    
    //receive the car object and save it to the data file
    public static void addNewCar(Car car) {
        new CarDAO().saveCar(car);
    }


    //return the list of cars
    public static List<Car> viewCars() {
        return new CarDAO().loadCars();
    }

    


}
