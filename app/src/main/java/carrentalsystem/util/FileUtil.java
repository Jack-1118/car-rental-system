package carrentalsystem.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;





public class FileUtil {
    
    private static ObjectMapper objectMapper = new ObjectMapper();


    private FileUtil() {
        // private constructor to prevent instantiation
    }

    // Save a list of object to a file
    public static <T> void saveFile(String filepath, List<T> list) {
        try {
            for (T object : list) {
                String json = objectMapper.writeValueAsString(object) + System.lineSeparator();
    
                Files.write(
                    Paths.get(filepath),
                    json.getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
                );
            }
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

    public static <T> void deleteRecord(String filePath, Class<T> type, Predicate<T> predicate) {
        List<T> allData = loadFile(filePath, type);
        allData.removeIf(predicate);
    
        // Clear the file
        try {
            new PrintWriter(filePath).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        // Save the modified list back to the file
        saveFile(filePath, allData);
    }








    
    // Modify a record in the file
    public static <T> void modifyRecord(String filePath, Class<T> type, Predicate<T> predicate, T newObject) {
        List<T> allData = loadFile(filePath, type);
        for (int i = 0; i < allData.size(); i++) {
            if (predicate.test(allData.get(i))) {
                allData.set(i, newObject);
                break;
            }
        }
    
        // Clear the file
        try {
            new PrintWriter(filePath).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        // Save the modified list back to the file
        saveFile(filePath, allData);
    }

    public static <T> void clearSession(String filepath, List<T> list) {
        try {
            // Clear the file
            Files.write(
                Paths.get(filepath),
                "".getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
            );

            // Append the objects from the list
            for (T object : list) {
                String json = objectMapper.writeValueAsString(object) + System.lineSeparator();

                Files.write(
                    Paths.get(filepath),
                    json.getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
