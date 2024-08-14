package com.br.gym.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Document(collection = "training")
public class TrainingModel implements Serializable {
    @Id
    private String _id;

    private String name;
    private String description;

    private UserModel student;

    private UserModel teacher;
}
