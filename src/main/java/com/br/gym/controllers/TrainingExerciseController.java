package com.br.gym.controllers;

import com.br.gym.dtos.trainingExercise.TrainingExerciseRecordDto;
import com.br.gym.models.ExerciseModel;
import com.br.gym.models.TrainingExerciseModel;
import com.br.gym.repositories.ExerciseRepository;
import com.br.gym.repositories.TrainingExerciseRepository;
import com.br.gym.repositories.TrainingRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TrainingExerciseController {

    @Autowired
    TrainingExerciseRepository trainingExerciseRepository;

    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    @PostMapping("/training-exercise")
    public ResponseEntity<Object> createTrainingExercise(@RequestBody @Valid TrainingExerciseRecordDto trainingExerciseRecordDto) {
        TrainingExerciseModel trainingExerciseModel = new TrainingExerciseModel();
        BeanUtils.copyProperties(trainingExerciseRecordDto, trainingExerciseModel);

        Optional<ExerciseModel> exercise = exerciseRepository.findById(trainingExerciseRecordDto.exercise());

        if (exercise.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exercise not found.");
        }

        trainingExerciseModel.setExercise(exercise.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(trainingExerciseRepository.save(trainingExerciseModel));
    }

    @GetMapping("/training-exercise")
    public ResponseEntity<Object> getTrainingExercises() {
        return ResponseEntity.ok(trainingExerciseRepository.findAll());
    }

}
