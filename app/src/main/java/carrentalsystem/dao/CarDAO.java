package carrentalsystem.dao;

import java.util.List;
import java.util.Collections;


import carrentalsystem.model.Car;
import carrentalsystem.util.FileUtil;


public class CarDAO {

    // file path for the data file
    private static final String DATA_FILE_PATH = "app/src/main/resources/CarData.txt";

    // Save a single car to the data file
    public static void saveCar(Car car) {
        FileUtil.saveFile(DATA_FILE_PATH, Collections.singletonList(car));
    }

    // Load the list of cars from the data file
    public static List<Car> loadCars() {
        return FileUtil.loadFile(DATA_FILE_PATH, Car.class);
    }
    
    

}
