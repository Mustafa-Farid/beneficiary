package com.enjaz.benefeciary.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Document(collection="beneficiaries")
public class Beneficiary {
    @Id
    private String id;

    @NotNull(message="firstname cannot be null")
    private String firstname;

    @NotNull(message="lastName cannot be null")
    private String lastName;

    @NotNull(message="beneficiaryType cannot be null")
    private String beneficiaryType;

    @NotNull(message="accountNumber cannot be null")
    private String accountNumber;

    @NotNull(message="bankName cannot be null")
    private String bankName;

    @NotNull(message="address cannot be null")
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBeneficiaryType() {
        return beneficiaryType;
    }

    public void setBeneficiaryType(String beneficiaryType) {
        this.beneficiaryType = beneficiaryType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Beneficiary{" +
                "Id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastName='" + lastName + '\'' +
                ", beneficiaryType='" + beneficiaryType + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", bankName='" + bankName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
