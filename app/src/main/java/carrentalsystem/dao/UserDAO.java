package carrentalsystem.dao;

import java.util.List;

import carrentalsystem.model.User;
import carrentalsystem.util.FileUtil;


public class UserDAO {
    private static final String DATA_FILE_PATH = "app/src/main/resources/UserData.txt";

    public void saveUser(User user) {
        FileUtil.saveFile(DATA_FILE_PATH, user);
    }

    public List<User> loadUsers() {
        return FileUtil.loadFile(DATA_FILE_PATH, User.class);
    }

}
