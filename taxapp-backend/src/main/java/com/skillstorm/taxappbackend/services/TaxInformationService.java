package com.skillstorm.taxappbackend.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.taxappbackend.models.TaxInformation;
import com.skillstorm.taxappbackend.repositories.TaxInformationRepository;

@Service
public class TaxInformationService {

    @Autowired
    TaxInformationRepository taxInformationRepository;

    public List<TaxInformation> getAllTaxInformation() {
        try {
            return taxInformationRepository.findAll();
        } catch (Exception e) {
            throw new EntityNotFoundException("No tax information found");
        }
    }

    public TaxInformation getTaxInformationById(Long id) {
        Optional<TaxInformation> taxInformation = taxInformationRepository.findById(id);
        if (taxInformation.isPresent()) {
            return taxInformation.get();
        } else {
            throw new EntityNotFoundException("No tax information found with id: " + id);
        }
    }

    @Transactional
    public int updateTaxInformationByBody(TaxInformation taxInformation, String filingStatus, Long age, Long dependents,
            boolean isBlind, Long incomeW2, Long income1099, Long taxesPaidW2, Long taxesPaid1099, String employer,
            String companyName) {
        Optional<TaxInformation> taxInformationOptional = taxInformationRepository
                .findById(taxInformation.getTaxInfoId());
        if (taxInformationOptional.isPresent()) {
            if (filingStatus != null) {
                taxInformationOptional.get().setFilingStatus(filingStatus);
            }
            if (age != null) {
                taxInformationOptional.get().setAge(age);
            }
            if (dependents != null) {
                taxInformationOptional.get().setDependents(dependents);
            }
            if (isBlind != false) {
                taxInformationOptional.get().setBlind(isBlind);
            }
            if (incomeW2 != null) {
                taxInformationOptional.get().setIncomeW2(incomeW2);
            }
            if (income1099 != null) {
                taxInformationOptional.get().setIncome1099(income1099);
            }
            if (taxesPaidW2 != null) {
                taxInformationOptional.get().setTaxesPaidW2(taxesPaidW2);
            }
            if (taxesPaid1099 != null) {
                taxInformationOptional.get().setTaxesPaid1099(taxesPaid1099);
            }
            if (employer != null) {
                taxInformationOptional.get().setEmployer(employer);
            }
            if (companyName != null) {
                taxInformationOptional.get().setCompanyName(companyName);
            }
            return 1;
        }
        return 0;
    }

}
