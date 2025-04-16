package com.enjaz.benefeciary.repository;

import com.enjaz.benefeciary.entity.Beneficiary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface BeneficiaryRepository extends MongoRepository<Beneficiary, String> {

    @Query("{'accountNumber': ?0}")
    Optional<Beneficiary> findByAccountNumber(String accountNumber);
}
