package com.mwibutsa.stockflow.auth.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotBlank
    @Email(message = "Please provide a valid email")
    private String email;

    @NotBlank
    @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters long.")
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
}
