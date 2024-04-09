package com.br.gym.repositories;

import com.br.gym.models.TrainingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TrainingRepository extends JpaRepository<TrainingModel, UUID>{
}
