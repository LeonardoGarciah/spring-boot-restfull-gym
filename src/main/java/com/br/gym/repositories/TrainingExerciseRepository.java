package com.br.gym.repositories;

import com.br.gym.models.TrainingExerciseModel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TrainingExerciseRepository extends MongoRepository<TrainingExerciseModel, UUID> {
}
