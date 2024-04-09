package com.br.gym.dtos.exercise;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateExerciseRecordDto(
        @NotNull String name,
        @NotNull UUID[] trainings,
        String description,
        String videoUrl,
        String imagePreview
) {
}
