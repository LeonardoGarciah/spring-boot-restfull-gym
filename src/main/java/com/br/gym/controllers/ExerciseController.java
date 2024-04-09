package com.br.gym.controllers;

import com.br.gym.dtos.exercise.CreateExerciseRecordDto;
import com.br.gym.models.ExerciseModel;
import com.br.gym.repositories.ExerciseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ExerciseController {

    @Autowired
    ExerciseRepository exerciseRepository;

    @PostMapping("/exercise")
    public ResponseEntity<Object> createExercise(@RequestBody @Valid CreateExerciseRecordDto createExerciseRecordDto) {
        ExerciseModel exerciseModel = new ExerciseModel();
        BeanUtils.copyProperties(createExerciseRecordDto, exerciseModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(exerciseRepository.save(exerciseModel));
    }

    @PutMapping("/exercise/{id}")
    public ResponseEntity<Object> updateExercise(@PathVariable(value = "id") UUID id, @RequestBody @Valid CreateExerciseRecordDto createExerciseRecordDto) {
        var exercise = exerciseRepository.findById(id);

        if (exercise.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exercise not found.");
        }

        ExerciseModel exerciseModel = exercise.get();

        BeanUtils.copyProperties(createExerciseRecordDto, exerciseModel);

        return ResponseEntity.ok(exerciseRepository.save(exerciseModel));
    }

    @GetMapping("/exercise")
    public ResponseEntity<Object> getExercises() {
        return ResponseEntity.ok(exerciseRepository.findAll());
    }
}
