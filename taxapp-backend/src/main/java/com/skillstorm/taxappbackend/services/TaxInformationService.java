package com.skillstorm.taxappbackend.services;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.taxappbackend.models.AppUser;
import com.skillstorm.taxappbackend.models.TaxInformation;
import com.skillstorm.taxappbackend.repositories.AppUserRepository;
import com.skillstorm.taxappbackend.repositories.TaxInformationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaxInformationService {
    

    @Autowired
    TaxInformationRepository taxInformationRepository;
    @Autowired
    AppUserRepository appUserRepository;
    

    public TaxInformation saveTaxInformation(TaxInformation taxInformation) {
        return taxInformationRepository.save(taxInformation);
    }

    public List<TaxInformation> getAllTaxInformation() {
        return taxInformationRepository.findAll();
    }

    public TaxInformation getTaxInformationById(String id) {
        return taxInformationRepository.findById(id).orElse(null);
    }

    public List<TaxInformation> getTaxInformationByUserId(String userId) {
        ObjectId userIdObject = new ObjectId(userId);
        System.out.println("User ID String: " + userId);
        System.out.println("User ID ObjectId: " + userIdObject);
        
        List<TaxInformation> taxInformationList = taxInformationRepository.findByUser_Id(userId);
        System.out.println("Found Tax Information: " + taxInformationList);
        
        return taxInformationList;
    }

    public void deleteTaxInformationById(String id) {
        taxInformationRepository.deleteById(id);
    }
}
