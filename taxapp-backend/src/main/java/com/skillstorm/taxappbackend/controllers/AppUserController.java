package com.skillstorm.taxappbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.taxappbackend.models.AppUser;
import com.skillstorm.taxappbackend.services.AppUserService;

@CrossOrigin
@RestController
@RequestMapping("/appUsers")
public class AppUserController {

    @Autowired
    AppUserService appUserService;

    /* Get Mappings */

    @GetMapping
    public ResponseEntity<List<AppUser>> getAllUsers() {
        List<AppUser> users = appUserService.findAllUsers();
        return new ResponseEntity<List<AppUser>>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) {
        AppUser user = appUserService.findUserById(id);
        return new ResponseEntity<AppUser>(user, HttpStatus.OK);
    }

    /* Post Mappings */
    @PostMapping("/create")
    public ResponseEntity<AppUser> createUser(AppUser user) {
        AppUser newUser = appUserService.createUser(user);
        return new ResponseEntity<AppUser>(newUser, HttpStatus.CREATED);
    }
}
