package com.skillstorm.taxappbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skillstorm.taxappbackend.models.AppUser;
import com.skillstorm.taxappbackend.models.TaxInformation;
import com.skillstorm.taxappbackend.services.AppUserService;
import com.skillstorm.taxappbackend.services.TaxInformationService;

import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "true", originPatterns = "https://44.201.48.146")
@RequestMapping("/tax-information")
public class TaxInformationController {

    @Autowired
    TaxInformationService taxInformationService;

    @Autowired
    AppUserService appUserService;

    @PostMapping("/{userId}")
    public ResponseEntity<TaxInformation> saveTaxInformation(@PathVariable String userId,
            @RequestBody TaxInformation taxInformation) {
        // Get the AppUser by its ID
        AppUser appUser = appUserService.getUserById(userId);
        if (appUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Associate the TaxInformation with the AppUser
        taxInformation.setUser(appUser);

        // Save the TaxInformation
        TaxInformation savedTaxInfo = taxInformationService.saveTaxInformation(taxInformation);
        return new ResponseEntity<>(savedTaxInfo, HttpStatus.CREATED);
    }

    @GetMapping
    public List<TaxInformation> getAllTaxInformation() {
        return taxInformationService.getAllTaxInformation();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaxInformation> getTaxInformationById(@PathVariable String id) {
        TaxInformation taxInfo = taxInformationService.getTaxInformationById(id);
        if (taxInfo != null) {
            return new ResponseEntity<>(taxInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public List<TaxInformation> getTaxInformationByUserId(@PathVariable String userId) {
        return taxInformationService.getTaxInformationByUserId(userId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaxInformation> updateTaxInformation(@PathVariable String id,
            @RequestBody TaxInformation updatedTaxInformation) {
        TaxInformation updatedTaxInfo = taxInformationService.updateTaxInformation(id, updatedTaxInformation);
        return new ResponseEntity<>(updatedTaxInfo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaxInformationById(@PathVariable String id) {

        TaxInformation taxInfo = taxInformationService.getTaxInformationById(id);
        if (taxInfo != null) {
            taxInformationService.deleteTaxInformationById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
