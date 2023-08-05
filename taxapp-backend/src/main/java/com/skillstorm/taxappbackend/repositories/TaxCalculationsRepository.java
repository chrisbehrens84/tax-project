package com.skillstorm.taxappbackend.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.skillstorm.taxappbackend.models.TaxCalculations;

public interface TaxCalculationsRepository extends MongoRepository<TaxCalculations, String> {

    Optional<TaxCalculations> findByTaxInformation_Id(String taxInformationId);

}
