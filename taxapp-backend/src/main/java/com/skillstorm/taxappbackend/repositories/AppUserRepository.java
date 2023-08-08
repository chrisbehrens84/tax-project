package com.skillstorm.taxappbackend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.skillstorm.taxappbackend.models.AppUser;

public interface AppUserRepository extends MongoRepository<AppUser, String> {

    boolean existsByEmail(String email);
    // Custom methods (if needed) can be added here

    AppUser findByEmail(String email);
}
