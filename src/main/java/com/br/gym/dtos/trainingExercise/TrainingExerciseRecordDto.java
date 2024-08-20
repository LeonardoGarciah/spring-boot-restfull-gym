package com.br.gym.dtos.trainingExercise;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TrainingExerciseRecordDto(
        @NotNull int series,
        @NotNull int repetitions,
        Double weight,
        @NotNull int restTime,
        @NotNull String exercise
) {
}
