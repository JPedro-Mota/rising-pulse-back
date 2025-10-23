package com.jpmota.rising_pulse_app.users.DTOs;

import com.jpmota.rising_pulse_app.users.enums.UserRoleEnum;
import lombok.Builder;

@Builder
public record UserRecordDTO(
        int id,
        String name,
        String email,
        UserRoleEnum role
) {


}
