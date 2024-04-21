package carrentalsystem.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import carrentalsystem.model.Car;


public class CarDAO {

    // file path for the data file
    private static final String DATA_FILE_PATH = "app/src/main/resources/data.txt";
    private final ObjectMapper objectMapper;

    public CarDAO() {
        objectMapper = new ObjectMapper();
    }

    // Save a single car to the data file
    public void saveCar(Car car) {
        try {
            // Convert the car to a JSON string + new line for separation
            String json = objectMapper.writeValueAsString(car) + System.lineSeparator();
            
            // Write/append the JSON string to the specified text file
            Files.write(
                Paths.get(DATA_FILE_PATH),
                json.getBytes(),
                StandardOpenOption.CREATE,  
                StandardOpenOption.APPEND     
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    


}
