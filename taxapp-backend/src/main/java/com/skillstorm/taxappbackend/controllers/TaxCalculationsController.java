package com.skillstorm.taxappbackend.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.taxappbackend.models.TaxCalculations;
import com.skillstorm.taxappbackend.models.TaxInformation;
import com.skillstorm.taxappbackend.services.TaxCalculationsService;
import com.skillstorm.taxappbackend.services.TaxInformationService;

@RestController
@RequestMapping("/tax-calculations")
public class TaxCalculationsController {

    @Autowired
    TaxCalculationsService taxCalculationsService;

    @Autowired
    TaxInformationService taxInformationService;

    /* Post Mappings */
    // @PostMapping("/{taxInfoId}")
    // public ResponseEntity<TaxCalculations> saveTaxCalculations(@PathVariable
    // String taxInfoId,
    // @RequestBody TaxCalculations taxCalculations) {
    // // Get the TaxInformation by its ID
    // TaxInformation taxInformation =
    // taxInformationService.getTaxInformationById(taxInfoId);
    // if (taxInformation == null) {
    // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }
    // // Associate the TaxInformation with the TaxCalculations
    // taxCalculations.setTaxInformation(taxInformation);
    // // Save the TaxCalculations
    // TaxCalculations savedTaxCalculations =
    // taxCalculationsService.saveTaxCalculations(taxCalculations);
    // return ResponseEntity.ok(savedTaxCalculations);
    // }

    @PostMapping("/generate/{taxInformationId}")
    public ResponseEntity<TaxCalculations> generateTaxCalculations(@PathVariable String taxInformationId) {
        TaxCalculations taxCalculations = new TaxCalculations();
        TaxInformation taxInformation = taxInformationService.getTaxInformationById(taxInformationId);

        if (taxInformation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // Associate the TaxInformation with the TaxCalculations
        taxCalculations.setTaxInformation(taxInformation);

        TaxCalculations finaCalculations = taxCalculationsService.generateTaxCalculations(taxInformationId,
                taxCalculations);
        return new ResponseEntity<TaxCalculations>(finaCalculations, HttpStatus.OK);
    }

    /** Get Mappings */
    @GetMapping
    public List<TaxCalculations> getAllTaxCalculations() {
        return taxCalculationsService.getAllTaxCalculations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaxCalculations> getTaxCalculationsById(@PathVariable String id) {
        TaxCalculations taxCalculations = taxCalculationsService.getTaxCalculationsById(id);
        if (taxCalculations != null) {
            return new ResponseEntity<>(taxCalculations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tax-information/{taxInformationId}")
    public ResponseEntity<TaxCalculations> getTaxCalculationsByTaxInformationId(@PathVariable String taxInformationId) {
        return taxCalculationsService.getTaxCalculationsByTaxInformationId(taxInformationId);
    }

    /** Put mappings */
    @PutMapping("/{id}")
    public ResponseEntity<TaxCalculations> updateTaxCalculations(@PathVariable String id,
            @RequestBody TaxCalculations updatedTaxCalculations) {
        TaxCalculations taxCalculations = taxCalculationsService.updateTaxCalculations(id, updatedTaxCalculations);
        return new ResponseEntity<TaxCalculations>(taxCalculations, HttpStatus.OK);
    }

    /** Delete Mappings */
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteTaxCalculationsById(@PathVariable String id) {
        return taxCalculationsService.deleteTaxCalculationsById(id);
    }
}
