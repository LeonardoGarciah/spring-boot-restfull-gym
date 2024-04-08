package com.br.gym.controllers;

import com.br.gym.dtos.AuthenticationDto;
import com.br.gym.dtos.RegisterDto;
import com.br.gym.services.AuthorizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthorizationService authorizationService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDto authetinticationDto) {
        return authorizationService.login(authetinticationDto);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register (@RequestBody @Valid RegisterDto registerDto){
        return authorizationService.register(registerDto);
    }
}
