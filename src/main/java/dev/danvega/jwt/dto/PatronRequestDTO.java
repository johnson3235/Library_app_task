package dev.danvega.jwt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import dev.danvega.jwt.dto.custom_validate.UniquePhoneNumber2;
public class PatronRequestDTO {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Contact information is required")
    @Pattern(regexp = "^01[0125][0-9]{8}$", message = "Invalid Egyptian phone number")
    @UniquePhoneNumber2(message = "Phone number already exists")
    private String contactInformation;
}
