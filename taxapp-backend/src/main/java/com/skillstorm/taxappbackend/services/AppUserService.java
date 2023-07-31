package com.skillstorm.taxappbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.taxappbackend.models.AppUser;
import com.skillstorm.taxappbackend.repositories.AppUserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

    @Autowired
    AppUserRepository appUserRepository;

    public AppUser createUser(String email, String password) {
        if (!emailExists(email)) {
            AppUser user = new AppUser();
            user.setEmail(email);
            user.setPassword(password);
            return appUserRepository.save(user);
        } else {
            throw new RuntimeException("Email already exists" + email);
        }
    }

    public AppUser updateUser(String id, AppUser updatedUser) {
        Optional<AppUser> existingUserOptional = appUserRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            AppUser existingUser = existingUserOptional.get();
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setSsn(updatedUser.getSsn());
            existingUser.setAddress(updatedUser.getAddress());
            existingUser.setCity(updatedUser.getCity());
            existingUser.setZip(updatedUser.getZip());

            return appUserRepository.save(existingUser);
        } else {
            return null; // Handle case when user is not found
        }
    }

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    public AppUser getUserById(String id) {
        return appUserRepository.findById(id).orElse(null);
    }

    public void deleteUserById(String id) {
        appUserRepository.deleteById(id);
    }

    public boolean emailExists(String email) {
        return appUserRepository.existsByEmail(email);
    }
    // update
}
