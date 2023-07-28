package com.skillstorm.taxappbackend.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.skillstorm.taxappbackend.models.TaxInformation;

public interface TaxInformationRepository extends MongoRepository<TaxInformation, String> {
    List<TaxInformation> findByUser_Id(String userId);

}
