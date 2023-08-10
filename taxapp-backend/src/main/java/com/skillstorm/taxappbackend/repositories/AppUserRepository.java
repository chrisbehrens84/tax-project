package com.skillstorm.taxappbackend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.skillstorm.taxappbackend.models.AppUser;

public interface AppUserRepository extends MongoRepository<AppUser, String> {

    boolean existsByEmail(String email);
   

    AppUser findByEmail(String email);
}
