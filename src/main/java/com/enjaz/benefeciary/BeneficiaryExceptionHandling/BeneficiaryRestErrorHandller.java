package com.enjaz.benefeciary.BeneficiaryExceptionHandling;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BeneficiaryRestErrorHandller {

    @ExceptionHandler
    public ResponseEntity<BeneficiaryResponseMessage> handleException(BeneficiaryExceptionHandling exc){
        BeneficiaryResponseMessage error = new BeneficiaryResponseMessage();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<BeneficiaryResponseMessage> handleException(ValidationException exc){
        BeneficiaryResponseMessage error = new BeneficiaryResponseMessage();
        error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler
    public ResponseEntity<BeneficiaryResponseMessage> handleException(Exception exc){
        BeneficiaryResponseMessage error = new BeneficiaryResponseMessage();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
