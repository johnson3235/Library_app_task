package dev.danvega.jwt.dto;


import dev.danvega.jwt.dto.custom_validate.UniquePhoneNumber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class PatronUpdateDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Contact information is required")
    @Pattern(regexp = "^01[0125][0-9]{8}$", message = "Invalid Egyptian phone number")
    @UniquePhoneNumber(message = "Phone number already exists")
    private String contactInformation;

    // Constructors, getters, and setters
}