package com.br.gym.dtos.user;

import jakarta.validation.constraints.NotNull;

public record UpdateUserRecordDto(
        String name,
        String email,
        String password,
        String experience,
        boolean firstAccess,
        double height,
        double weight,
        double yearsOld
) {
}
