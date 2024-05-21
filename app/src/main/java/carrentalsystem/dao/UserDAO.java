package carrentalsystem.dao;

import java.util.List;
import java.util.Collections;

import carrentalsystem.model.Booking;
import carrentalsystem.model.Car;
import carrentalsystem.model.User;
import carrentalsystem.util.FileUtil;


public class UserDAO {
    private static final String DATA_FILE_PATH = "app/src/main/resources/UserData.txt";
    private static final String DATA_FILE_PATH_ADMIN = "app/src/main/resources/AdminData.txt";
    private static final String DATA_FILE_PATH_SESSION_DATA = "app/src/main/resources/SessionData.txt";
    private static final String BOOKING_FILE_PATH = "app/src/main/resources/BookingData.txt";

    public static void saveUser(User user) {
        FileUtil.saveFile(DATA_FILE_PATH, Collections.singletonList(user));
    }

    public static List<User> loadUsers() {
        return FileUtil.loadFile(DATA_FILE_PATH, User.class);
    }

    public static void saveAdmin(User user) {
        FileUtil.saveFile(DATA_FILE_PATH_ADMIN, Collections.singletonList(user));
    }

    public static List<User> loadAdmins() {
        return FileUtil.loadFile(DATA_FILE_PATH_ADMIN, User.class);
    }

    public static void saveSessionData(User user) {
        FileUtil.saveFile(DATA_FILE_PATH_SESSION_DATA, Collections.singletonList(user));
    }

    public static List<User> loadSessionData() {
        return FileUtil.loadFile(DATA_FILE_PATH_SESSION_DATA, User.class);
    }

    public static void clearSessionData() {
        FileUtil.clearSession(DATA_FILE_PATH_SESSION_DATA, Collections.emptyList());
    }

    //get user by username
    public static User getUserByUsername(String username) {
        List<User> users = FileUtil.loadFile(DATA_FILE_PATH, User.class);
    
        for (User user: users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    //delete user by username
    public static void deleteUser(String username) {
        FileUtil.deleteRecord(DATA_FILE_PATH, User.class, u -> u.getUsername().equals(username));
        FileUtil.deleteRecord(BOOKING_FILE_PATH, Booking.class, u -> u.getUsername().equals(username));
    }

    // modify the user in the data file
    public static void modifyUser(User user) {
        FileUtil.modifyRecord(DATA_FILE_PATH, User.class, u -> u.getUsername().equals(user.getUsername()), user );

    }

    public static boolean usernameExists(String username) {
        List<User> users = loadAdmins(); // Assuming this method returns all users
        List<User> userss = loadUsers();
        users.addAll(userss);
        return users.stream().anyMatch(user -> user.getUsername().equalsIgnoreCase(username));
    }
}
