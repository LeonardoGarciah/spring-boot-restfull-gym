package com.br.gym.dtos.training;

import jakarta.validation.constraints.NotNull;

public record CreateTrainingRecordDto(
        @NotNull String name,
        @NotNull String teacher,
        @NotNull String student,
        String description
) {
}
