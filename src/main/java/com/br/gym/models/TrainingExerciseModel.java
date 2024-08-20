package com.br.gym.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Document(collection = "training-exercise")
public class TrainingExerciseModel implements Serializable {
    private String _id;

    private int series;
    private int repetitions;
    private int weight;
    private int restTime;

    private ExerciseModel exercise;
}
