package com.br.gym.dtos.training;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateTrainingRecordDto(
        @NotNull String name,
        @NotNull UUID teacher,
        @NotNull UUID student,
        String description
) {
}
