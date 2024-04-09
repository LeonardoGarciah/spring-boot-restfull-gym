package com.br.gym.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "EXERCISES")
@Getter
@Setter
public class ExerciseModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String imagePreview;
    private String description;
    private String videoUrl;

    @OneToMany
    private Set<TrainingExerciseModel> trainings;
}
