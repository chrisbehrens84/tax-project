package com.skillstorm.taxappbackend.services;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

    public TaxInformation updateTaxInformation(String taxInformationId, TaxInformation updatedTaxInformation) {
        Optional<TaxInformation> existingTaxInformationOptional = taxInformationRepository.findById(taxInformationId);

        if (existingTaxInformationOptional.isPresent()) {
            TaxInformation existingTaxInformation = existingTaxInformationOptional.get();

            // Update the fields with the new values
            existingTaxInformation.setFilingStatus(updatedTaxInformation.getFilingStatus());
            existingTaxInformation.setDependents(updatedTaxInformation.getDependents());
            existingTaxInformation.setW2Wages(updatedTaxInformation.getW2Wages());
            existingTaxInformation.setW2Withheld(updatedTaxInformation.getW2Withheld());
            existingTaxInformation.setIsBlind(updatedTaxInformation.getIsBlind());
            existingTaxInformation.setAge(updatedTaxInformation.getAge());
            existingTaxInformation.setIncome1099(updatedTaxInformation.getIncome1099());
            existingTaxInformation.setTaxPaid1099(updatedTaxInformation.getTaxPaid1099());

            return taxInformationRepository.save(existingTaxInformation);
        } else {
            // Handle case where TaxInformation with given id doesn't exist
            throw new RuntimeException("TaxInformation not found with ID: " + taxInformationId);
        }
    }

    public void deleteTaxInformationById(String id) {
        taxInformationRepository.deleteById(id);
    }
}
