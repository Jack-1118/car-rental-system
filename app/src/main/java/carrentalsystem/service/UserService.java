package carrentalsystem.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import carrentalsystem.dao.UserDAO;
import carrentalsystem.model.User;


public class UserService {
    

    // //Login
    // public static String login(String username, String password) {
    //     List<User> users = UserDAO.loadUsers();

    //     // loop through the list of users to find the user with the matching username and password
    //     for (User user : users) {
    //         if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
    //             UserDAO.saveSessionData(user);
    //             return "customer";
    //         } 
    //     }
    //     // return random value if no matching user is found
    //     return "no";


    // } 



    public static void main(String[] args) {
        // test the login method
        // System.out.println(login("admin1", "password1")); // admin
        // System.out.println(login("user1", "password1")); // user
        // System.out.println(login("user1", "password2")); // null
    }
}
