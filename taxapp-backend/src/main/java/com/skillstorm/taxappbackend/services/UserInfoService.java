package com.skillstorm.taxappbackend.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.taxappbackend.models.UserInfo;
import com.skillstorm.taxappbackend.repositories.AppUserRepository;
import com.skillstorm.taxappbackend.repositories.UserInfoRepository;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;
    private AppUserRepository appUserRepository;

    public UserInfo getUserInfoByAppUserId(Long appUserId) {
      return userInfoRepository.findByAppUserUserId(appUserId);
    }

    public List<UserInfo> getAllUserInfo() {
        return userInfoRepository.findAll();
    }

    public UserInfo createUserInfo(UserInfo userInfo) {
        try {
            return userInfoRepository.save(userInfo);
        } catch(Exception e){
            throw new EntityNotFoundException("User Info Aleady Exists");
        }
    }

    public UserInfo updateUserInfo(Long userInfoId, UserInfo userInfo) {
        UserInfo existingUserInfo = userInfoRepository.findById(userInfoId).orElse(null);
        if (existingUserInfo != null) {
            // Update the fields of existingUserInfo with the fields from userInfo
            if(userInfo.getFirstName() !=null){
                existingUserInfo.setFirstName(userInfo.getFirstName());
            }
            
            if(userInfo.getLastName() !=null){
                existingUserInfo.setLastName(userInfo.getLastName());
            }

            if(userInfo.getAddress() !=null){
                 existingUserInfo.setAddress(userInfo.getAddress());
            }

            if(userInfo.getCity() !=null){
                existingUserInfo.setCity(userInfo.getCity());
            }

            if(userInfo.getZipcode() !=null){
                existingUserInfo.setZipcode(userInfo.getZipcode());
            }

            if(userInfo.getSsn() !=null){
                existingUserInfo.setSsn(userInfo.getSsn());
            }
            
            
            
            return userInfoRepository.save(existingUserInfo);
        }
        return null; // If userInfoId does not exist, return null
    }

    @Transactional
    public boolean deleteUserInfo(Long userInfoId) {
        try {
            Optional<UserInfo> userInfoOptional = userInfoRepository.findById(userInfoId);
            if (userInfoOptional.isPresent()) {
                userInfoRepository.deleteById(userInfoId);
                System.out.println(userInfoId);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            // Handle any exceptions or log errors if necessary
            return false;
        }
    }
  
}