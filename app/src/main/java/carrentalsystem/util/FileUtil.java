package carrentalsystem.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;


public class FileUtil {
    
    private static ObjectMapper objectMapper = new ObjectMapper();


    private FileUtil() {
        // private constructor to prevent instantiation
    }

    // Save an object to a file
    public static void saveFile (String filepath, Object object) {
        try {
            String json = objectMapper.writeValueAsString(object) + System.lineSeparator();

            Files.write(
                Paths.get(filepath),
                json.getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            e.printStackTrace();
    
        }
    }

    // Load a list of objects from a file
    public static <T> List<T> loadFile(String filePath, Class<T> type) {
        List<T> objects = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T object = objectMapper.readValue(line, type);
                objects.add(object);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objects;
    }


    //delete a record from the file
    public static void deleteRecord(String filePath, String record, String field) {
        


        


}
