package carrentalsystem.dao;

import java.util.List;
import java.util.Collections;

import carrentalsystem.model.User;
import carrentalsystem.util.FileUtil;


public class UserDAO {
    private static final String DATA_FILE_PATH = "app/src/main/resources/UserData.txt";
    private static final String DATA_FILE_PATH_ADMIN = "app/src/main/resources/AdminData.txt";

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

}
