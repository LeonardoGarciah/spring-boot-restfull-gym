package com.br.gym.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TRAININGS")
@Getter
@Setter
public class TrainingModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private UserModel student;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private UserModel teacher;
}
