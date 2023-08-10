package com.skillstorm.taxappbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skillstorm.taxappbackend.models.AppUser;
import com.skillstorm.taxappbackend.repositories.AppUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public AppUser createUser(String email, String password) {
        if (!emailExists(email)) {
            AppUser user = new AppUser();
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole("ROLE_USER");
            return appUserRepository.save(user);
        } else {
            return null;
        }
    }

    public AppUser updateUser(String id, AppUser updatedUser) {
        Optional<AppUser> existingUserOptional = appUserRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            AppUser existingUser = existingUserOptional.get();
            existingUser.setEmail(updatedUser.getEmail());
            String updatedPassword = updatedUser.getPassword();
            if (!updatedPassword.startsWith("$2a$")) {
                // If the updated password is not hashed, encode it
                existingUser.setPassword(passwordEncoder.encode(updatedPassword));
            } else {
                existingUser.setPassword(updatedPassword); // Password is already hashed, use as is
            }
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

    public AppUser getUserByEmail(String email) {
        System.out.println(email);
        return appUserRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(email);
        if (appUser == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(appUser.getEmail(),
                appUser.getPassword(), new ArrayList<>());
    }
}
