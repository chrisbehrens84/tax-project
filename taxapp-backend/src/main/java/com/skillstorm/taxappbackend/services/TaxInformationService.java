package com.skillstorm.taxappbackend.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skillstorm.taxappbackend.models.AppUser;
import com.skillstorm.taxappbackend.models.TaxCalculations;
import com.skillstorm.taxappbackend.models.TaxInformation;
import com.skillstorm.taxappbackend.repositories.AppUserRepository;
import com.skillstorm.taxappbackend.repositories.TaxCalculationsRepository;
import com.skillstorm.taxappbackend.repositories.TaxInformationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaxInformationService {

    @Autowired
    TaxInformationRepository taxInformationRepository;
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    TaxCalculationsRepository taxCalculationsRepository;
    @Autowired
    TaxCalculationsService taxCalculationsService;

    public TaxInformation saveTaxInformation(TaxInformation taxInformation) {
        taxInformationRepository.save(taxInformation);
        callCreateTaxCalculations(taxInformation.getId());
        return taxInformation;
    }

    public void callCreateTaxCalculations(String id) {
        System.out.println(id);
        TaxCalculations taxCalculations = new TaxCalculations();
        taxCalculationsService.generateTaxCalculations(id, taxCalculations);
    }

    public List<TaxInformation> getAllTaxInformation() {
        return taxInformationRepository.findAll();
    }

    public TaxInformation getTaxInformationById(String id) {
        return taxInformationRepository.findById(id).orElse(null);
    }

    public List<TaxInformation> getTaxInformationByUserId(String userId) {

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

            // ******where we need to delete/recreate taxCalculations or update the
            // taxCalculations*****************
            ResponseEntity<TaxCalculations> taxCalculations = taxCalculationsService
                    .getTaxCalculationsByTaxInformationId(taxInformationId);
            if (taxCalculations != null && taxCalculations.getBody() != null) {

                TaxCalculations taxCalculationsBody = taxCalculations.getBody();
                taxCalculationsService.deleteTaxCalculationsById(taxCalculationsBody.getId());
            }

            callCreateTaxCalculations(taxInformationId);

            return taxInformationRepository.save(existingTaxInformation);
        } else {
            // Handle case where TaxInformation with given id doesn't exist
            throw new RuntimeException("TaxInformation not found with ID: " + taxInformationId);
        }
    }

    public void deleteTaxInformationById(String id) {
        ResponseEntity<TaxCalculations> taxCalculations = taxCalculationsService
                .getTaxCalculationsByTaxInformationId(id);
        if (taxCalculations == null) {
            throw new RuntimeException("TaxInformation not found with ID: " + id);
        }
        TaxCalculations taxCalculationsBody = taxCalculations.getBody();
        taxCalculationsService.deleteTaxCalculationsById(taxCalculationsBody.getId());
        taxInformationRepository.deleteById(id);
    }

}
