package com.br.gym.controllers;

import com.br.gym.dtos.user.UserRecordDto;
import com.br.gym.models.UserModel;
import com.br.gym.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {
    final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserRecordDto userRecordDto) {
        UserModel user = new UserModel();

        BeanUtils.copyProperties(userRecordDto, user);

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possivel criar o usuário");
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") UUID id, @RequestBody @Valid UserRecordDto userRecordDto) {
        Optional<UserModel> user = userRepository.findById(id);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        UserModel userModel = user.get();

        BeanUtils.copyProperties(userRecordDto, userModel);

        return ResponseEntity.ok(userRepository.save(userModel));
    }

    @GetMapping("/users")
    public ResponseEntity<Object> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable(value = "id") UUID id) {
        Optional<UserModel> user = userRepository.findById(id);

        return user.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found."));

    }
}
