package com.skillstorm.taxappbackend.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.taxappbackend.models.TaxCalculations;
import com.skillstorm.taxappbackend.models.TaxInformation;
import com.skillstorm.taxappbackend.repositories.TaxCalculationsRepository;
import com.skillstorm.taxappbackend.repositories.TaxInformationRepository;

@Service
public class TaxCalculationsService {
    
    @Autowired
    TaxCalculationsRepository taxCalculationsRepository;
    TaxInformationRepository taxInformationRepository;


     public TaxCalculations getTaxCalculationByTaxInfoId(Long taxInfoId) {
        return taxCalculationsRepository.findByTaxInformationTaxInfoId(taxInfoId);
    }
  //totalIncome
    //total from all w2's box 1
      // incomeW2 from TaxInformation

  /*totalDedcutions
        **filingStatus come form TaxInformation filingStatus
    // if(filingStatus == "Single" && age >= 65 && isBlind){
          totalDeductions = 17550
       } else if(filingStatus == "Single" && (age >= 65 || isBlind)){
        totalDeductions = 17550
       } else if(filingStatus == "Single"){
        totalDeduction = 13850
       } else if (filingStatus == "Married Filing Jointly" && age >= 65 && isBlind) {
        totalDeduction = 30700
       } else if (filingStatus == "Married Filing Jointly" && (age >= 65 || isBlind)) {
        totalDeduction = 28500
       } else if(filingStatus =="Married Filing Jointly"){
        totalDeductions = 27700
       }

  //totalPaid
   totalPaid =  taxesPaidW2

  //totalCredits
     totalCredits = (numberOfDependendts * 2000)
  */



  /* regTaxes
       let 
  
  
  */
       
       

}
