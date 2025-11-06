package com.jpmota.rising_pulse_app.DTOs.user;

import com.jpmota.rising_pulse_app.domain.enums.user.UserRoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserRecordDTO(
        @NotBlank String name,
        @NotBlank String password,
        @NotBlank @Email String email,
        @NotNull UserRoleEnum role
        ) {
}
