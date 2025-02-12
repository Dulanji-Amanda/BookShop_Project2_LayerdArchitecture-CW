package org.example.stockverse.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private String User_Id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;

    public UserDTO() {
    }

    public UserDTO(String User_Id, String firstName, String lastName, String username, String email, String password) {
        this.User_Id = User_Id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUserId() {
        return this.User_Id;
    }

    public void setUserId(String userId) {
        this.User_Id = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "User_Id='" + User_Id + '\''+
                ", firstName='" + firstName +'\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}' ;

    }
}