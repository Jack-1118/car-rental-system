package carrentalsystem.dao;

import java.util.List;
import java.util.Collections;

import carrentalsystem.model.User;
import carrentalsystem.util.FileUtil;


public class UserDAO {
    private static final String DATA_FILE_PATH = "app/src/main/resources/UserData.txt";

    public static void saveUser(User user) {
        FileUtil.saveFile(DATA_FILE_PATH, Collections.singletonList(user));
    }

    public static List<User> loadUsers() {
        return FileUtil.loadFile(DATA_FILE_PATH, User.class);
    }

}
