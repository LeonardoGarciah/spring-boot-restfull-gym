package com.br.gym.services;


import java.sql.Date;

import com.br.gym.dtos.auth.AuthenticationRecordDto;
import com.br.gym.dtos.auth.LoginResponseRecordDto;
import com.br.gym.dtos.auth.RegisterDto;
import com.br.gym.enums.UserRoleEnum;
import com.br.gym.models.UserModel;
import com.br.gym.repositories.UserRepository;
import com.br.gym.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationRecordDto data) {
        authenticationManager = context.getBean(AuthenticationManager.class);

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        try {
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((UserModel) auth.getPrincipal());

            return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseRecordDto(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuário ou senha inválida!");
        }
    }


    public ResponseEntity<Object> register(@RequestBody RegisterDto registerDto) {
        if (this.userRepository.findByEmail(registerDto.email()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());

        UserModel newUser = new UserModel(registerDto.name(), registerDto.email(), encryptedPassword, UserRoleEnum.USER);
        newUser.setCreatedAt(new Date(System.currentTimeMillis()));
        this.userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.OK).body("User created with success!");
    }
}



