package com.enjaz.benefeciary.BeneficiaryExceptionHandling;

public class BeneficiaryExceptionHandling extends RuntimeException{
    public BeneficiaryExceptionHandling(){

    }

    public BeneficiaryExceptionHandling(String message) {
        super(message);
    }

    public BeneficiaryExceptionHandling(String message, Throwable cause) {
        super(message, cause);
    }

    public BeneficiaryExceptionHandling(Throwable cause) {
        super(cause);
    }

}
