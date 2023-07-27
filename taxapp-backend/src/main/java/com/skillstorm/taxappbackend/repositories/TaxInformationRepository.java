package com.skillstorm.taxappbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.taxappbackend.models.TaxInformation;

@Repository
public interface TaxInformationRepository extends JpaRepository<TaxInformation, Long> {

}
