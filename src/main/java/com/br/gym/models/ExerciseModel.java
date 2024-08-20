package com.br.gym.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@Document(collection = "exercise")
public class ExerciseModel implements Serializable {
    private String _id;

    private String name;
    private String imagePreview;
    private String description;
    private String videoUrl;
}
