package dev.taxiapp.taxi_app.DTOs;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;


public record UserInfoDTO(
    @NotBlank(message = "username is required")
    String username,

    @NotBlank
    @Size(min = 8, message = "Password must have at least 8 characters")
    String password,

    @NotBlank(message = "first name is required")
    String firstName,

    String LastName,

    @Past(message = "invalid birth date")
    LocalDate birthDate
) {
    
}
