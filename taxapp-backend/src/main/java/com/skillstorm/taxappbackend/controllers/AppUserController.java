package com.skillstorm.taxappbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public ResponseEntity<List<AppUser>> getAllUsers() {
        List<AppUser> users = appUserService.findAllUsers();
        System.out.println("==========" + users + "=============================");
        return new ResponseEntity<List<AppUser>>(users, HttpStatus.OK);
    }
}
