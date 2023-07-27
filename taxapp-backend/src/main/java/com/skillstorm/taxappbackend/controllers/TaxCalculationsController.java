package com.skillstorm.taxappbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.taxappbackend.models.TaxCalculations;
import com.skillstorm.taxappbackend.models.UserInfo;
import com.skillstorm.taxappbackend.services.TaxCalculationsService;
import com.skillstorm.taxappbackend.services.TaxInformationService;


@CrossOrigin
@RestController
@RequestMapping("/taxcalculations")
public class TaxCalculationsController {
  
  @Autowired 
  TaxCalculationsService taxCalculationsService;
  TaxInformationService taxInformationService;

  @GetMapping("/{taxInfoId}")
  public ResponseEntity<TaxCalculations> getTaxCalculationByTaxInfoId(@PathVariable Long taxInfoId) {
    TaxCalculations taxCalculation = taxCalculationsService.getTaxCalculationByTaxInfoId(taxInfoId);
     if (taxCalculation != null) {
            return new ResponseEntity<TaxCalculations>(taxCalculation, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
 }


  // @PostMapping("/create")


  // @PutMapping("/update/{taxCalculationId}")

  
  // @DeleteMapping("delete/{taxCalculationId}");


}
