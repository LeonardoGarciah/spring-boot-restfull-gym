package com.br.gym.dtos.training;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateTrainingRecordDto(
        @NotNull String name,
        @NotNull String teacherId,
        @NotNull String studentId,
        List<String> trainingExerciseIdList,
        String description
) {
}
