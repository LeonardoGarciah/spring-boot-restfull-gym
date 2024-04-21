package com.br.gym.controllers;

import com.br.gym.dtos.user.CreateUserRecordDto;
import com.br.gym.dtos.user.UpdateUserRecordDto;
import com.br.gym.dtos.user.UserResponseDto;
import com.br.gym.models.UserModel;
import com.br.gym.repositories.UserRepository;
import com.br.gym.security.TokenService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class UserController {
    final UserRepository userRepository;
    private final TokenService tokenService;

    public UserController(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> saveUser(@RequestBody @Valid CreateUserRecordDto createUserRecordDto) {
        UserModel user = new UserModel();

        BeanUtils.copyProperties(createUserRecordDto, user);

        UserResponseDto userResponseDto = user.convertToUserResponseDto();

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possivel criar o usuário");
        }
    }

    @PutMapping("/users")
    public ResponseEntity<Object> updateUser(@RequestHeader(name = "Authorization") String token, @RequestBody @Valid UpdateUserRecordDto updateUserRecordDto) {
        final UUID id = UUID.fromString(tokenService.validateToken(token));

        Optional<UserModel> user = userRepository.findById(id);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        UserModel userModel = user.get();

        userModel.setName(updateUserRecordDto.name());
        userModel.setExperience(updateUserRecordDto.experience());
        userModel.setHeight(updateUserRecordDto.height());
        userModel.setWeight(updateUserRecordDto.weight());
        userModel.setYearsOld(updateUserRecordDto.yearsOld());
        userModel.setFirstAccess(updateUserRecordDto.firstAccess());

        UserModel updatedUser = userRepository.save(userModel);

        return ResponseEntity.ok(updatedUser.convertToUserResponseDto());
    }

    @GetMapping("/users")
    public ResponseEntity<Object> getUsers() {

        List<UserResponseDto> users = userRepository.findAll().stream().map(UserModel::convertToUserResponseDto).collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/infos")
    public ResponseEntity<Object> getUsersInfos(@RequestHeader(name = "Authorization") String token) {
        final UUID id = UUID.fromString(tokenService.validateToken(token));

        Optional<UserModel> user = userRepository.findById(id);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        UserResponseDto userResponseDto = user.get().convertToUserResponseDto();

        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable(value = "id") UUID id) {
        Optional<UserModel> user = userRepository.findById(id);

        UserResponseDto userResponseDto = user.map(UserModel::convertToUserResponseDto).orElse(null);

        if (userResponseDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        return ResponseEntity.ok(userResponseDto);
    }
}
