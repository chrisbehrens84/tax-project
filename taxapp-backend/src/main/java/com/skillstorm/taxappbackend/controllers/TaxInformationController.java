package com.skillstorm.taxappbackend.controllers;

import java.util.List;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.taxappbackend.models.TaxInformation;
import com.skillstorm.taxappbackend.models.UserInfo;
import com.skillstorm.taxappbackend.services.TaxInformationService;

@RestController
@RequestMapping("/taxinformation")
public class TaxInformationController {

    @Autowired
    TaxInformationService taxInformationService;

    /* Get mappings */
    @GetMapping
    public ResponseEntity<List<TaxInformation>> getAllTaxInformation() {
        List<TaxInformation> taxInformation = taxInformationService.getAllTaxInformation();
        return new ResponseEntity<List<TaxInformation>>(taxInformation, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public TaxInformation getTaxInformationById(@PathVariable Long id) {
        return taxInformationService.getTaxInformationById(id);
    }

    /* Post Mappings */
    @PostMapping("/create")
    public ResponseEntity<TaxInformation> createTaxInformation(@RequestBody TaxInformation taxInformation) {
        TaxInformation createdTaxInformation = taxInformationService.createTaxInformation(taxInformation);
        return new ResponseEntity<TaxInformation>(createdTaxInformation, HttpStatus.CREATED);
    }

    /* Put Mappings */
    @PutMapping("/update")
    public ResponseEntity<Integer> updateTaxInformationByBody(@RequestBody TaxInformation taxInformation,
            @RequestParam(required = false) String filingStatus, @RequestParam(required = false) Long age,
            @RequestParam(required = false) Long dependents, @RequestParam(required = false) boolean isBlind,
            @RequestParam(required = false) Long incomeW2, @RequestParam(required = false) Long income1099,
            @RequestParam(required = false) Long taxesPaidW2, @RequestParam(required = false) Long taxesPaid1099,
            @RequestParam(required = false) String employer, @RequestParam(required = false) String companyName) {
        int updated = taxInformationService.updateTaxInformationByBody(taxInformation, filingStatus, age, dependents,
                isBlind, incomeW2, income1099, taxesPaidW2, taxesPaid1099, employer, companyName);
        return new ResponseEntity<Integer>(updated, HttpStatus.OK);
    }

    /* Delete Mappings */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteTaxInformationById(@PathVariable Long id) {
        System.out.println("deleteTaxInformationById");
        int deleted = taxInformationService.deleteTaxInformationById(id);
        return new ResponseEntity<Integer>(deleted, HttpStatus.OK);
    }
}