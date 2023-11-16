package org.launchcode.projectmanagement.models.dto;
// <!-- 
// created by: Jonathan Hays
//  -->


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterFormDTO extends LoginFormDTO {

    @NotNull(message = "First name cannot be blank")
    @NotBlank(message = "First name cannot be blank")
    @Size(min = 3, message = "First name must be at least 3 letters")
    private String firstName;

    @NotNull(message = "Last name cannot be blank")
    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 3, message = "Last name must be at least 3 letters")
    private String lastName;


    private String verifyPassword;

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
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
