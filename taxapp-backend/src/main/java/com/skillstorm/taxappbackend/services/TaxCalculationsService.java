package com.skillstorm.taxappbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skillstorm.taxappbackend.models.TaxCalculations;
import com.skillstorm.taxappbackend.models.TaxInformation;
import com.skillstorm.taxappbackend.repositories.TaxCalculationsRepository;
import com.skillstorm.taxappbackend.repositories.TaxInformationRepository;

@Service
public class TaxCalculationsService {

    @Autowired
    TaxCalculationsRepository taxCalculationsRepository;

    @Autowired
    TaxInformationRepository taxInformationRepository;

    public TaxCalculations saveTaxCalculations(TaxCalculations taxCalculations) {
        return taxCalculationsRepository.save(taxCalculations);
    }

    public List<TaxCalculations> getAllTaxCalculations() {
        return taxCalculationsRepository.findAll();
    }

    public TaxCalculations getTaxCalculationsById(String id) {
        Optional<TaxCalculations> taxCalculationsOptional = taxCalculationsRepository.findById(id);
        if (taxCalculationsOptional.isPresent()) {
            return taxCalculationsOptional.get();
        }
        return null;
    }

    public ResponseEntity<TaxCalculations> getTaxCalculationsByTaxInformationId(String taxInformationId) {
        Optional<TaxCalculations> taxCalculationsOptional = taxCalculationsRepository
                .findByTaxInformation_Id(taxInformationId);
        if (taxCalculationsOptional.isPresent()) {
            return ResponseEntity.ok(taxCalculationsOptional.get());
        }
        return null;
    }

    public TaxCalculations updateTaxCalculations(String id, TaxCalculations updatedTaxCalculations) {
        Optional<TaxCalculations> existingTaxCalculationsOptional = taxCalculationsRepository.findById(id);
        if (existingTaxCalculationsOptional.isPresent()) {
            TaxCalculations existingTaxCalculations = existingTaxCalculationsOptional.get();
            // Update the fields with the new values
            existingTaxCalculations.setEffective_tax_rate(updatedTaxCalculations.getEffective_tax_rate());
            existingTaxCalculations.setTotalCredits(updatedTaxCalculations.getTotalCredits());
            existingTaxCalculations.setTotalDeductions(updatedTaxCalculations.getTotalDeductions());
            existingTaxCalculations.setTotalIncome(updatedTaxCalculations.getTotalIncome());
            existingTaxCalculations.setTotalPaid(updatedTaxCalculations.getTotalPaid());
            existingTaxCalculations.setTotalTaxableIncome(updatedTaxCalculations.getTotalTaxableIncome());
            existingTaxCalculations.setNetTaxes(updatedTaxCalculations.getNetTaxes());
            existingTaxCalculations.setFinalTaxes(updatedTaxCalculations.getFinalTaxes());
            // Save the updated TaxCalculations
            return taxCalculationsRepository.save(existingTaxCalculations);
        } else {
            throw new RuntimeException("Tax calculations with id: " + id + " not found");
        }
    }

    public ResponseEntity<Integer> deleteTaxCalculationsById(String id) {
        if (taxCalculationsRepository.existsById(id)) {
            taxCalculationsRepository.deleteById(id);
            return ResponseEntity.ok(1);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public TaxCalculations generateTaxCalculations(String taxInformationId, TaxCalculations taxCalculations) {
        // Get the TaxInformation by its ID
        Optional<TaxInformation> taxInformationOptional = taxInformationRepository.findById(taxInformationId);

        if (taxInformationOptional.isPresent()) {
            TaxInformation taxInformation = taxInformationOptional.get();

            // Calculate the information needed for tax calculations
            Double taxableIncome = 0.0;
            Double netTaxes = 0.0;
            Double totalIncome = 0.0;
            Double totalPaid = 0.0;
            Double deductions = 0.0;
            Double totalTaxWithCredits = 0.0;
            Double finalTaxes = 0.0;

            // Get the fields from the TaxInformation
            String filingStatus;
            Integer dependents = 0;
            Integer w2Wages = 0;
            Integer w2Withheld = 0;
            Boolean isBlind;
            Integer age;
            Integer income1099 = 0;
            Integer taxPaid1099 = 0;

            filingStatus = taxInformation.getFilingStatus();
            dependents = taxInformation.getDependents();
            w2Wages = taxInformation.getW2Wages();
            w2Withheld = taxInformation.getW2Withheld();
            isBlind = taxInformation.getIsBlind();
            age = taxInformation.getAge();
            income1099 = taxInformation.getIncome1099();
            taxPaid1099 = taxInformation.getTaxPaid1099();

            if (income1099 == null) {
                income1099 = 0;
            }
            if (taxPaid1099 == null) {
                taxPaid1099 = 0;
            }
            if (w2Wages == null) {
                w2Wages = 0;
            }
            if (w2Withheld == null) {
                w2Withheld = 0;
            }
            System.out.println("=========+" + income1099);
            // Calculate the total income and total paid
            totalIncome += w2Wages + income1099;
            totalPaid += w2Withheld + taxPaid1099;

            // Calculate the deductions
            if (filingStatus.equals("single") && (age >= 65 && isBlind)) {
                deductions += 17550;
            } else if (filingStatus.equals("single") && (age >= 65 || isBlind)) {
                deductions += 15700;
                // todo change to .equals
            } else if (filingStatus == "single") {
                deductions += 13850;
            } else if (filingStatus == "Married Filing Jointly" && age >= 65 && isBlind) {
                deductions += 30700;
            } else if (filingStatus == "Married Filing Jointly" && (age >= 65 || isBlind)) {
                deductions += 28500;
            } else if (filingStatus == "Married Filing Jointly") {
                deductions += 27700;
            }
            // Calculate the taxable income
            taxableIncome += totalIncome - deductions;

            // Calculate the net taxes
            if (filingStatus.equals("single") && taxableIncome >= 578128) {
                netTaxes += (174238 + ((taxableIncome - 578125) * .37));

            } else if (filingStatus.equals("single") && taxableIncome >= 231250) {
                netTaxes += (52832 + ((taxableIncome - 231250) * .35));

            } else if (filingStatus.equals("single") && taxableIncome >= 182100) {
                netTaxes += (37104 + (taxableIncome - 182100) * .32);

            } else if (filingStatus.equals("single") && taxableIncome >= 95375) {
                netTaxes += (16290 + ((taxableIncome - 95375) * .24));

            } else if (filingStatus == "single" && taxableIncome >= 44725) {
                netTaxes += (5147 + ((taxableIncome - 44727) * .22));

            } else if (filingStatus == "single" && taxableIncome >= 11000) {
                netTaxes += (1100 + ((taxableIncome - 44727) * .12));

            } else if (filingStatus == "single" && taxableIncome >= 0) {
                netTaxes += (taxableIncome * .1);

            } else if (filingStatus == "Married Filing Jointly" && taxableIncome >= 693750) {
                netTaxes += (183132 + ((taxableIncome - 578125) * .37));

            } else if (filingStatus == "Married Filing Jointly" && taxableIncome >= 462500) {
                netTaxes += (102195 + ((taxableIncome - 462500) * .35));

            } else if (filingStatus == "Married Filing Jointly" && taxableIncome >= 364200) {
                netTaxes += (70739 + ((taxableIncome - 182100) * .32));

            } else if (filingStatus == "Married Filing Jointly" && taxableIncome >= 190750) {
                netTaxes += (32580 + ((taxableIncome - 95375) * .24));

            } else if (filingStatus == "Married Filing Jointly" && taxableIncome >= 89450) {
                netTaxes += (10294 + ((taxableIncome - 44727) * .22));

            } else if (filingStatus == "Married Filing Jointly" && taxableIncome >= 22000) {
                netTaxes += (8094 + ((taxableIncome - 44727) * .12));

            } else if (filingStatus == "Married Filing Jointly" && taxableIncome >= 0) {
                netTaxes += (taxableIncome * .1);
            }

            // Calculate the final taxes

            if ((netTaxes - (dependents * 2000)) > (0 - (dependents * 1500))) {
                totalTaxWithCredits = (netTaxes - (dependents * 2000));
            } else {
                totalTaxWithCredits = (double) (0 - (dependents * 1500));
            }

            
            finalTaxes = totalTaxWithCredits - totalPaid;

            // Set the fields of the TaxCalculations
            taxCalculations.setTotalIncome(totalIncome);
            taxCalculations.setTotalPaid((double) w2Withheld + taxPaid1099);
            taxCalculations.setTotalDeductions(deductions);
            taxCalculations.setTotalTaxableIncome(taxableIncome);
            taxCalculations.setNetTaxes(netTaxes);
            taxCalculations.setEffective_tax_rate((netTaxes / taxableIncome) * 100);
            taxCalculations.setTotalCredits((double) (dependents * 2000));
            taxCalculations.setFinalTaxes(finalTaxes);
            taxCalculations.setTotalTaxWithCredits(totalTaxWithCredits);

            taxCalculationsRepository.save(taxCalculations);

        } else {
            throw new RuntimeException("Tax information with id: " + taxInformationId + " not found");
        }
        return null;
    }

}