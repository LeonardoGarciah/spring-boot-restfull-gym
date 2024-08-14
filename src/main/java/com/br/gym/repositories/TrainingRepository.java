package com.br.gym.repositories;

import com.br.gym.models.TrainingModel;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TrainingRepository extends MongoRepository<TrainingModel, UUID> {
}
