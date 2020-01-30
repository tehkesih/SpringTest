package com.tehkesih.springboot.ui.model.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDetailsRequestModel {

    @NotNull(message = "FirstName must not null")
    @Size(min = 2, message = "First name must not be less than 2 characters")
    private String firstName;

    @NotNull(message = "Last Name cannot be null")
    @Size(min = 2, message = "Last name must not be less than 2 characters")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email does not contains some of the following")
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 16, message = "Password must be equal or greater than 8 characters and less than 16 characters")
    private String password;
}
