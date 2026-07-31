package com.mwibutsa.stockflow.supplier.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BaseSupplierDto {
    @NotBlank
    private String name;

    @NotBlank
    private String contactPerson;

    @NotBlank
    @Email(message = "Provide a valid email address")
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String address;
}
