package carrentalsystem.service;

import java.util.List;

import carrentalsystem.dao.UserDAO;
import carrentalsystem.model.User;


public class UserService {


    public static String registerUser(String username, String password) throws IllegalArgumentException {
        validateUsername(username);
        validatePassword(password);
//        String role = "user";

        if (isUsernameTaken(username)) {
            throw new IllegalArgumentException("Username already taken");
        }

//        User newUser = new User(username, password, role);
//        UserDAO.saveUser(newUser);
        return "Success";
    }

    private static void validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
    }

    private static void validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
        // Additional password complexity checks can be added here
    }

    private static boolean isUsernameTaken(String username) {
        List<User> users = UserDAO.loadUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    
    //Login
    public static String login(String username, String password) {
        List<User> users = UserDAO.loadUsers();

        // loop through the list of users to find the user with the matching username and password
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return "customer";
            } 
        }
        // return random value if no matching user is found
        return "no";


    } 



    public static void main(String[] args) {
        
        // test the login method
        System.out.println(login("admin1", "password1")); // admin
        System.out.println(login("user1", "password1")); // user
        System.out.println(login("user1", "password2")); // null
    }
}
