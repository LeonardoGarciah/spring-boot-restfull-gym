package com.br.gym.repositories;

import com.br.gym.models.UserModel;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {
    UserDetails findByEmail(String email);
}
