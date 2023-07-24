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
            return ResponseEntity.ok(userInfo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/{appUserId}")
    public ResponseEntity<UserInfo> createUserInfo(@RequestBody UserInfo userInfo, @RequestParam Long userId) {
        AppUser appUser = appUserService.findUserById(userId);
        if (appUser == null) {
            return ResponseEntity.badRequest().build(); // Return bad request if the AppUser does not exist
        }

        userInfo.setAppUser(appUser); // Associate the AppUser with the UserInfo

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

    @DeleteMapping("/{userInfoId}")
    public ResponseEntity<Void> deleteUserInfo(@PathVariable Long userInfoId) {
        boolean deleted = userInfoService.deleteUserInfo(userInfoId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}