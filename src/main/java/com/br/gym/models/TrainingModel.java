package com.br.gym.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Document(collection = "training")
public class TrainingModel implements Serializable {
    private String _id;

    private String name;
    private String description;


    private String studentId;

    private String teacherId;

    private List<TrainingExerciseModel> trainingExerciseList;
}
