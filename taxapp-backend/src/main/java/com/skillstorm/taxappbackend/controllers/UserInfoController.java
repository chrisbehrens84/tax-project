package com.skillstorm.taxappbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.taxappbackend.models.AppUser;
import com.skillstorm.taxappbackend.models.UserInfo;
import com.skillstorm.taxappbackend.services.AppUserService;
import com.skillstorm.taxappbackend.services.UserInfoService;

@CrossOrigin
@RestController
@RequestMapping("/userinfo")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;
    AppUserService appUserService;

    @GetMapping
    public ResponseEntity<List<UserInfo>> getAllUserInfo() {
        List<UserInfo> allUserInfo = userInfoService.getAllUserInfo();
        return new ResponseEntity<List<UserInfo>>(allUserInfo,HttpStatus.OK);
    }

    @GetMapping("/{appUserId}")
    public ResponseEntity<UserInfo> getUserInfoByAppUserId(@PathVariable Long appUserId) {
        UserInfo userInfo = userInfoService.getUserInfoByAppUserId(appUserId);
        if (userInfo != null) {
            return new ResponseEntity<UserInfo>(userInfo, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // need to get the AppuserId and post with this
    @PostMapping("/{appUserId}")
    public ResponseEntity<UserInfo> createUserInfo(@RequestBody UserInfo userInfo, @PathVariable Long appUserId) {
        AppUser appUser = appUserService.findUserById(appUserId);
        System.out.println(appUser);
        if (appUser == null) {
            return ResponseEntity.notFound().build(); // Return bad request if the AppUser does not exist
        }

        userInfo.setAppUser(appUser); // Associate the AppUser with the UserInfo
       System.out.println(userInfo);
        UserInfo createdUserInfo = userInfoService.createUserInfo(userInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserInfo);
    }

    @PutMapping("/{userInfoId}")
    public ResponseEntity<UserInfo> updateUserInfo(@PathVariable Long userInfoId, @RequestBody UserInfo userInfo) {
        UserInfo updatedUserInfo = userInfoService.updateUserInfo(userInfoId, userInfo);
        if (updatedUserInfo != null) {
            return ResponseEntity.ok(updatedUserInfo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //delete mapping
    // @DeleteMapping("/delete")
    // public ResponseEntity<Integer> deleteUserInfo(@RequestBody UserInfo userInfo) {
    //     int deleted = userInfoService.deleteUserInfo(userInfo);
    //     return new ResponseEntity<Integer>(deleted, HttpStatus.OK);
    // }

    @DeleteMapping("/{userInfoId}")
    public ResponseEntity<String> deleteUserInfo(@PathVariable Long userInfoId) {
        try {
            boolean isDeleted = userInfoService.deleteUserInfo(userInfoId);
            if (isDeleted) {
                return ResponseEntity.ok("UserInfo with ID " + userInfoId + " has been deleted.");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete UserInfo.");
        }
    }


}