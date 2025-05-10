package dev.taxiapp.taxi_app.DTOs;

import java.time.LocalDate;
import java.util.List;

import dev.taxiapp.taxi_app.Utils.UserRoles;

public record UserInfoResponseDTO(
    Long id,
    String firstName,
    String lastName,
    String email,
    String phoneNumber,
    LocalDate birthDate,
    List<UserRoles> roles
) {
    
}
