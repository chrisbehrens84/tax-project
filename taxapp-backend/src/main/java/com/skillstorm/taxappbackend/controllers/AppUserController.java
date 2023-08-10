package com.skillstorm.taxappbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.skillstorm.taxappbackend.models.AppUser;
import com.skillstorm.taxappbackend.services.AppUserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class AppUserController {

  @Autowired
  AppUserService appUserService;

  @Autowired
  PasswordEncoder passwordEncoder;

  @GetMapping
  public List<AppUser> getAllUsers() {
    return appUserService.getAllUsers();
  }

  @PostMapping("/email")
  public ResponseEntity<AppUser> getUserByEmail(@RequestParam String email, @RequestParam String password) {
    AppUser appUser = appUserService.getUserByEmail(email);
    boolean isAuthenticated = BCrypt.checkpw(password, appUser.getPassword());
    if (isAuthenticated) {
      return new ResponseEntity<>(appUser, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  }

  @GetMapping("/{id}")
  public ResponseEntity<AppUser> getUserById(@PathVariable String id) {
    AppUser user = appUserService.getUserById(id);
    if (user != null) {
      return new ResponseEntity<>(user, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // User Signs up
  @PostMapping()
  public ResponseEntity<AppUser> createUser(@RequestParam String email, @RequestParam String password) {
    AppUser user = appUserService.createUser(email, password);
    if (user == null) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    } else {
      return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
  }

  @PutMapping("{id}")
  public ResponseEntity<AppUser> updateUser(@PathVariable String id, @RequestBody AppUser updatedUser) {
    AppUser user = appUserService.updateUser(id, updatedUser);
    if (user != null) {
      return new ResponseEntity<>(user, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUserById(@PathVariable String id) {
    AppUser user = appUserService.getUserById(id);
    if (user != null) {
      appUserService.deleteUserById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
