package nl.hva.miw.internetbanking.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CompanyTransactionDTO {

    private String companyName;
    private long numberOfTransactions;
    private long kvkNumber;
    private String address;
    private String firstNameAccountmanager;
    private String prepositionAccountmanager;
    private String surNameAccountmanager;

    public CompanyTransactionDTO(String companyName, long numberOfTransactions, long kvkNumber, String address,
                                 String firstNameAccountmanager, String prepositionAccountmanager, String surNameAccountmanager) {
        this.companyName = companyName;
        this.numberOfTransactions = numberOfTransactions;
        this.kvkNumber = kvkNumber;
        this.address = address;
        this.firstNameAccountmanager = firstNameAccountmanager;
        this.prepositionAccountmanager = prepositionAccountmanager;
        this.surNameAccountmanager = surNameAccountmanager;
    }

    public String getFullName() {
        if (prepositionAccountmanager == null) {
            return firstNameAccountmanager + " " + surNameAccountmanager;
        } else {
            return firstNameAccountmanager + " " + prepositionAccountmanager + " " + surNameAccountmanager;
        }
    }
}