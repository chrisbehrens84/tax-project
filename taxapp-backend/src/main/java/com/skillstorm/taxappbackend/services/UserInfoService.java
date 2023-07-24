package com.skillstorm.taxappbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.taxappbackend.models.UserInfo;
import com.skillstorm.taxappbackend.repositories.UserInfoRepository;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public UserInfo getUserInfoByAppUserId(Long appUserId) {
      return userInfoRepository.findByAppUserUserId(appUserId);
    }

    public List<UserInfo> getAllUserInfo() {
        return userInfoRepository.findAll();
    }

    public UserInfo createUserInfo(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    public UserInfo updateUserInfo(Long userInfoId, UserInfo userInfo) {
        UserInfo existingUserInfo = userInfoRepository.findById(userInfoId).orElse(null);
        if (existingUserInfo != null) {
            // Update the fields of existingUserInfo with the fields from userInfo
            existingUserInfo.setFirstName(userInfo.getFirstName());
            existingUserInfo.setLastName(userInfo.getLastName());
            // Set other fields as needed...
            
            return userInfoRepository.save(existingUserInfo);
        }
        return null; // If userInfoId does not exist, return null
    }

    public boolean deleteUserInfo(Long userInfoId) {
        if (userInfoRepository.existsById(userInfoId)) {
            userInfoRepository.deleteById(userInfoId);
            return true;
        }
        return false; // If userInfoId does not exist, return false
    }
}