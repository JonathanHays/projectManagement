package org.launchcode.projectmanagement.models;
// <!-- 
// created by: Jonathan Hays
//  -->

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
public class User {

    @Id
    private int id;
    @NotNull
    private String username;
    @NotNull
    private String pwHash;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;

    @ManyToOne
    private User createdBy;
    @ManyToOne
    private User modifiedBy;



    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {
        this.createdAt = LocalDateTime.now();
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }  

    public void setUsername(String username) {
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
        this.createdAt = LocalDateTime.now();

    }

    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.pwHash = encoder.encode(password);
        this.createdAt = LocalDateTime.now();
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public int getId() {
        return id;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
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
}
