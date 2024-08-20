package com.br.gym.repositories;

import com.br.gym.models.ExerciseModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExerciseRepository extends MongoRepository<ExerciseModel, String> {
}
