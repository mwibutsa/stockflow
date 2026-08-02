package com.mwibutsa.stockflow.auth.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotBlank
    @Email(message = "Please provide a valid email")
    private String email;

    @NotBlank
    @Min(value = 8, message = "Password must be at least 8 characters")
    @Max(value = 30, message = "Password must not be more than 30 characters")
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
}
