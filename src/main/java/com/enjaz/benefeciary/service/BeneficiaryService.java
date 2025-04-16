package com.enjaz.benefeciary.service;

import com.enjaz.benefeciary.entity.Beneficiary;

import java.util.List;

public interface BeneficiaryService {

    public List<Beneficiary> getAllBeneficiaries();

    public Beneficiary getSingleBeneficiary(String id);

    public Beneficiary createBeneficiary(Beneficiary beneficiary);

    public void updateBeneficiary(Beneficiary beneficiary,String id);

    public void deleteBeneficiaryById(String id);
}
