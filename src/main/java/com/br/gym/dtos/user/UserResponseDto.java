package com.br.gym.dtos.user;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

public record UserResponseDto(
        String id, String name, boolean firstAccess, double height, double weight, double yearsOld,
                              String experience) implements Serializable {
}
