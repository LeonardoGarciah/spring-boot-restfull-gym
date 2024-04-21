package com.br.gym.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRecordDto(@NotBlank String name, @NotBlank String email, @NotBlank String password) {
}
