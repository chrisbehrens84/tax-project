package com.skillstorm.taxappbackend.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.taxappbackend.models.AppUser;
import com.skillstorm.taxappbackend.repositories.AppUserRepository;

@Service
public class AppUserService {

    @Autowired
    AppUserRepository AppUserRepository;

    public List<AppUser> findAllUsers() {
        try {
            return AppUserRepository.findAll();
        } catch (Exception e) {
            throw new EntityNotFoundException("No users found");
        }
    }

}
