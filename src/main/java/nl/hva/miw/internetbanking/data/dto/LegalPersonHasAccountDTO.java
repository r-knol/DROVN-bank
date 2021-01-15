package nl.hva.miw.internetbanking.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.DecimalFormat;

@Getter
@Setter
@ToString
public class LegalPersonHasAccountDTO {

    private String companyName;
    private String iban;
    private double balance;
    private long kvkNumber;
    private String sector;
    private String address;
    private String firstNameAccountmanager;
    private String prepositionAccountmanager;
    private String surNameAccountmanager;

    public LegalPersonHasAccountDTO(String companyName, String iban, double balance, long kvkNumber, String sector, String address,
                                    String firstNameAccountmanager, String prepositionAccountmanager, String surNameAccountmanager) {
        this.companyName = companyName;
        this.iban = iban;
        this.balance = balance;
        this.kvkNumber = kvkNumber;
        this.sector = sector;
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

    public String getBalance() {
        DecimalFormat d = new DecimalFormat("###,###.00");
        return "€" + d.format(balance);
    }
}
