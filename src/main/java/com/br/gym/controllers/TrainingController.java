package com.br.gym.controllers;

import com.br.gym.dtos.training.CreateTrainingRecordDto;
import com.br.gym.models.TrainingModel;
import com.br.gym.repositories.TrainingRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class TrainingController {

    @Autowired
    TrainingRepository trainingRepository;

    @PostMapping("/training")
    public ResponseEntity<Object> createTraining(@RequestBody @Valid CreateTrainingRecordDto createTrainingRecordDto) {
        TrainingModel trainingModel = new TrainingModel();
        BeanUtils.copyProperties(createTrainingRecordDto, trainingModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(trainingRepository.save(trainingModel));
    }

    @PutMapping("/training/{id}")
    public ResponseEntity<Object> updateTraining(@PathVariable(value = "id") UUID id, @RequestBody @Valid CreateTrainingRecordDto createTrainingRecordDto) {
        var training = trainingRepository.findById(id);

        if (training.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Training not found.");
        }

        TrainingModel trainingModel = training.get();

        BeanUtils.copyProperties(createTrainingRecordDto, trainingModel);

        return ResponseEntity.ok(trainingRepository.save(trainingModel));
    }

    @GetMapping("/training/{userId}")
    public ResponseEntity<Object> getTrainings(@PathVariable(value = "userId") UUID userId) {
        return ResponseEntity.ok(trainingRepository.findById(userId));
    }
}
