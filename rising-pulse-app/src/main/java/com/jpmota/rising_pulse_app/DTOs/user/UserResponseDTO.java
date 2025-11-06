package com.jpmota.rising_pulse_app.DTOs.user;

import com.jpmota.rising_pulse_app.domain.enums.user.UserRoleEnum;
import lombok.Builder;

@Builder
public record UserResponseDTO(
        long id,
        String name,
        String email,
        UserRoleEnum role
) {


}
