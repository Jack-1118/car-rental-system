package carrentalsystem.model;

public class User {
    // account information
    private String username;
    private String password;
    private String role;

    // personal information
    private String fullName;
    private String contactNumber;
    private String gender;
    private String dateOfBirth;

    // constructor
    public User() {
        
    }

//    public User(String username, String password, String role) {
//        this.username = username;
//        this.password = password;
//        this.role = role;
//  
//    }

    // getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getFullName() {
        return fullName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public string getGender{
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    

    // setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


}
