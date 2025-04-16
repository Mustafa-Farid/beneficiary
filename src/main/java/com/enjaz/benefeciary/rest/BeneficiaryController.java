package com.enjaz.benefeciary.rest;

import com.enjaz.benefeciary.BeneficiaryExceptionHandling.BeneficiaryExceptionHandling;
import com.enjaz.benefeciary.BeneficiaryExceptionHandling.BeneficiaryResponseMessage;
import com.enjaz.benefeciary.entity.Beneficiary;
import com.enjaz.benefeciary.service.BeneficiaryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class BeneficiaryController {

    private final BeneficiaryService benSer;
    private final ObjectMapper objectMapper;
    @Autowired
    public BeneficiaryController(BeneficiaryService benSer,ObjectMapper objectMapper){
        this.benSer = benSer;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/beneficiaries")
    public List<Beneficiary> getAllBeneficiaries(){
        return benSer.getAllBeneficiaries();
    }

    @GetMapping("/beneficiaries/{id}")
    public Beneficiary getBeneficiary(@PathVariable String id){
        return benSer.getSingleBeneficiary(id);
    }

    @PostMapping("/beneficiaries")
    public ResponseEntity<BeneficiaryResponseMessage> postBeneficiary(@RequestBody Beneficiary beneficiary){
        beneficiary.setId(null);
        Beneficiary theBeneficiary = benSer.createBeneficiary(beneficiary);
        return setResponseMessage("Beneficiary inserted successfully with ID :(" +theBeneficiary.getId()+")",HttpStatus.OK);
    }

    @PutMapping("/beneficiaries/{id}")
    public ResponseEntity<BeneficiaryResponseMessage> putBeneficiary(@RequestBody Beneficiary beneficiary, @PathVariable String id){
        benSer.updateBeneficiary(beneficiary,id);
        return setResponseMessage("Beneficiary with ID :("+ id + ") Updated Successfully",HttpStatus.OK);
    }

    @DeleteMapping("/beneficiaries/{id}")
    public ResponseEntity<BeneficiaryResponseMessage> deleteBeneficiary(@PathVariable String id){
        benSer.deleteBeneficiaryById(id);
        return setResponseMessage("Beneficiary with ID :("+ id + ") deleted Successfully",HttpStatus.OK);
    }

    @PatchMapping("beneficiaries/{id}")
    public ResponseEntity<BeneficiaryResponseMessage> patch(@PathVariable String id, @RequestBody Map<String,Object> patchPayLoad)
    {
        Beneficiary tempBeneficiary = benSer.getSingleBeneficiary(id);

        // throw exception if null
        if (tempBeneficiary == null) {
            throw new BeneficiaryExceptionHandling("Beneficiary id not found :(" + id +")");
        }

        // throw exception if request body contains "id" key
        if (patchPayLoad.containsKey("id")) {
            throw new BeneficiaryExceptionHandling("Employee id not allowed in request body :(" + id +")");
        }

        Beneficiary patchedBeneficiary = apply(patchPayLoad,tempBeneficiary);
        benSer.updateBeneficiary(patchedBeneficiary,id);
        return setResponseMessage("Beneficiary with ID :("+ id + ") Updated Successfully",HttpStatus.OK);
    }

    public ResponseEntity<BeneficiaryResponseMessage> setResponseMessage(String message, HttpStatus httpStatus){
        BeneficiaryResponseMessage successResponse = new BeneficiaryResponseMessage();
        successResponse.setStatus(httpStatus.value());
        successResponse.setMessage(message);
        successResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(successResponse,httpStatus);
    }

    private Beneficiary apply(Map<String, Object> patchPayLoad, Beneficiary tempBeneficiary) {
        // Convert employee object to a JSON object node
        ObjectNode employeeNode = objectMapper.convertValue(tempBeneficiary, ObjectNode.class);

        // Convert the patchPayload map to a JSON object node
        ObjectNode patchNode = objectMapper.convertValue(patchPayLoad, ObjectNode.class);

        // Merge the patch updates into the employee node
        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode, Beneficiary.class);
    }
}
