package com.br.gym.dtos.exercise;

import jakarta.validation.constraints.NotNull;

public record CreateExerciseRecordDto(
        @NotNull String name,
        String description,
        String videoUrl,
        String imagePreview
) {
}
