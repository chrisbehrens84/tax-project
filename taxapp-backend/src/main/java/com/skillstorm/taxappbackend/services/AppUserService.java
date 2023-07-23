package com.skillstorm.taxappbackend.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

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

    public AppUser findUserById(Long id) {
        Optional<AppUser> user = AppUserRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new EntityNotFoundException("No user found with id: " + id);
        }
    }

    public AppUser createUser(AppUser user) {
        Optional<AppUser> existingUser = AppUserRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new EntityNotFoundException("Email: " + user.getEmail() + " already in use");
        }
        System.out.println(user.getEmail() + " " + user.getPassword());
        return AppUserRepository.save(user);
    }

    @Transactional
    public int updateUser(AppUser user, String email, String password) {
        Optional<AppUser> existingUser = AppUserRepository.findById(user.getUserId());

        if (existingUser.isPresent()) {
            AppUser updatedUser = existingUser.get();
            if (email != null) {
                updatedUser.setEmail(email);
            }
            if (password != null) {
                updatedUser.setPassword(password);
            }
            AppUserRepository.save(updatedUser);
            return 1;
        }
        return 0;
    }

    public int deleteUser(AppUser user) {
        Optional<AppUser> existingUser = AppUserRepository.findById(user.getUserId());
        if (existingUser.isPresent()) {
            AppUserRepository.delete(user);
            return 1;
        } else {
            throw new EntityNotFoundException("No user found with that information");
        }
    }

}
