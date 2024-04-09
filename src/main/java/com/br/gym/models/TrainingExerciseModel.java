package com.br.gym.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TRAINING_EXERCISES")
@Getter
@Setter
public class TrainingExerciseModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private int series;
    private int repetitions;
    private int weight;
    private int restTime;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private ExerciseModel exercise;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private TrainingModel training;
}
