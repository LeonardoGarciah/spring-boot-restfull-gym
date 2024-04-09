package com.br.gym.repositories;

import com.br.gym.models.ExerciseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseModel, UUID> {
}
