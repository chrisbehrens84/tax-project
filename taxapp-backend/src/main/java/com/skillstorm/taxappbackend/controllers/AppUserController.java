package com.skillstorm.taxappbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skillstorm.taxappbackend.models.AppUser;
import com.skillstorm.taxappbackend.services.AppUserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class AppUserController {
  
  
  @Autowired
  AppUserService appUserService;
  
  
  @GetMapping
  public List<AppUser> getAllUsers() {
    return appUserService.getAllUsers();
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
  
  //User Signs up
  @PostMapping()
  public ResponseEntity<AppUser> createUser(@RequestParam String email, @RequestParam String password) {
    AppUser user = appUserService.createUser(email, password);
    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }
  
  @PutMapping("/{id}")
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

