package com.enjaz.benefeciary.service;

import com.enjaz.benefeciary.BeneficiaryExceptionHandling.BeneficiaryExceptionHandling;
import com.enjaz.benefeciary.entity.Beneficiary;
import com.enjaz.benefeciary.repository.BeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService{

    BeneficiaryRepository beneficiaryRepository;
    @Autowired
    public BeneficiaryServiceImpl(BeneficiaryRepository beneficiaryRepository){
        this.beneficiaryRepository = beneficiaryRepository;
    }
    @Override
    public List<Beneficiary> getAllBeneficiaries() {
        return beneficiaryRepository.findAll();
    }

    @Override
    public Beneficiary getSingleBeneficiary(String id) {
        Optional<Beneficiary> optionalBeneficiary = beneficiaryRepository.findById(id);
        if(optionalBeneficiary.isPresent()){
            return optionalBeneficiary.get();
        }
        else {
            throw new BeneficiaryExceptionHandling("No Beneficiary found with this ID - "+ id);
        }
    }

    @Override
    public Beneficiary createBeneficiary(Beneficiary beneficiary) {
        return beneficiaryRepository.save(beneficiary);
    }

    @Override
    public void updateBeneficiary(Beneficiary beneficiary,String id) {
        Optional<Beneficiary> benWithId = beneficiaryRepository.findById(id);
        if(benWithId.isPresent()){
            Beneficiary benUpdated = benWithId.get();
            benUpdated.setFirstname(beneficiary.getFirstname());
            benUpdated.setLastName(beneficiary.getLastName());
            benUpdated.setBeneficiaryType(beneficiary.getBeneficiaryType());
            benUpdated.setAccountNumber(beneficiary.getAccountNumber());
            benUpdated.setBankName(beneficiary.getBankName());
            benUpdated.setAddress(beneficiary.getAddress());
            beneficiaryRepository.save(benUpdated);
        }
        else {
            throw new BeneficiaryExceptionHandling("No Beneficiary found with this ID - "+ id);
        }

    }

    @Override
    public void deleteBeneficiaryById(String id) {
        Optional<Beneficiary> optionalBeneficiary = beneficiaryRepository.findById(id);
        if(optionalBeneficiary.isPresent()){
            beneficiaryRepository.deleteById(id);
        }
        else {
            throw new BeneficiaryExceptionHandling("No Beneficiary found with this ID - "+ id);
        }
    }
}
