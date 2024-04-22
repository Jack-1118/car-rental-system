package carrentalsystem.dao;


import carrentalsystem.model.User;
import carrentalsystem.service.UserService;

public class Main {

    public static void main(String[] args) {
        System.out.println(UserService.login("admin1", "passwrd1")); // admin
    }
}
