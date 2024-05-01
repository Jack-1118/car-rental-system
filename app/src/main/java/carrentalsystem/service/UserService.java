package carrentalsystem.service;

import java.util.List;

import carrentalsystem.dao.UserDAO;
import carrentalsystem.model.User;
import java.io.IOException;

public class UserService {

    private UserService() {
        // Private constructor to prevent instantiation
    }

    //Register
    public static String register(String username, String password){
        boolean usernameChecker = false ;
        boolean passwordChecker = false ;

        
        if (username == null || username.trim().isEmpty()) {
            return "Username cannot be empty";
        }
        
        List<User> users = UserDAO.loadUsers();

        for (User user: users){
            if(user.getUsername().equals(username)){
                return "Username already been taken";
            }
        }
        usernameChecker = true;
        
        if(password == null || password.trim().isEmpty()){
            return "Password cannot be empty";
        } else if (password.length() < 8){
            return "Password must be at least 8 character";
        }
        passwordChecker = true;
        
        // If both username and password are valid, register the user
        if (usernameChecker && passwordChecker) {
            
                // Create a new User object with provided username and password
                
                User user = new User(username, password);
                
                // Save the user
                UserDAO.saveUser(user);
                
                return "Success";
            } 
        return "Error occurred while registering";
            
        

      
    }
        


    
    

    //Login
    public static String login(String username, String password) {
        List<User> users = UserDAO.loadUsers();

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
