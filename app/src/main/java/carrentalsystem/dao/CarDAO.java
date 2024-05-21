package carrentalsystem.dao;

import java.util.List;


import java.util.Collections;
import java.util.Comparator;


import carrentalsystem.model.Car;
import carrentalsystem.util.FileUtil;
import carrentalsystem.model.Booking;


public class CarDAO {

    // file path for the data file
    private static final String DATA_FILE_PATH = "app/src/main/resources/CarData.txt";
    private static final String BOOKING_FILE_PATH = "app/src/main/resources/BookingData.txt";

    // Save a single car to the data file
    public static void saveCar(Car car) {
        FileUtil.saveFile(DATA_FILE_PATH, Collections.singletonList(car));
    }

    // Load the list of cars from the data file
    public static List<Car> loadCars() {
        List<Car> cars = FileUtil.loadFile(DATA_FILE_PATH, Car.class);
        Collections.sort(cars, Comparator.comparing(Car::getCarID));
        return cars;
    }
    
    public static Car getCarById(int id) {
        List<Car> cars = FileUtil.loadFile(DATA_FILE_PATH, Car.class);
    
        for (Car car : cars) {
            if (car.getCarID() == id) {
                return car;  // returns the car without altering ID sequence
            }
        }
        return null;
    }


    // Get the maximum Car ID
    public static int assignCarID() {
        List<Car> cars = FileUtil.loadFile(DATA_FILE_PATH, Car.class);
        return cars.stream().mapToInt(Car::getCarID).max().orElse(0) + 1;
    }
    


    // modify the car in the data file
    public static void modifyCar(Car car) {
        FileUtil.modifyRecord(DATA_FILE_PATH, Car.class, c -> c.getCarID() == car.getCarID(), car);
    }

    // delete the car in the data file
    public static void deleteCar(Car car) {
        FileUtil.deleteRecord(DATA_FILE_PATH, Car.class, c -> c.getCarID() == car.getCarID());
        FileUtil.deleteRecord(BOOKING_FILE_PATH, Booking.class, c -> c.getCarID() == car.getCarID());
    }
}
