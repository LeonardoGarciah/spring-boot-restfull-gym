package com.br.gym.dtos.auth;

import jakarta.validation.constraints.NotNull;

public record RegisterDto(@NotNull String email, @NotNull String name, @NotNull String password ) {
}
