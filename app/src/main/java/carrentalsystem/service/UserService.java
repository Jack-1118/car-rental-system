package carrentalsystem.service;

import java.util.List;

import carrentalsystem.dao.UserDAO;
import carrentalsystem.model.User;


public class UserService {

    //Login
    public static String login(String username, String password) {
        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.loadUsers();

        // loop through the list of users to find the user with the matching username and password
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user.getRole();
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
