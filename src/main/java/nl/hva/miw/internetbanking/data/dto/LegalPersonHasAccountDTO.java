package nl.hva.miw.internetbanking.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LegalPersonHasAccountDTO {

    private String companyName;
    private String iban;
    private double balance;
    private long kvkNumber;
    private String sector;
    private String address;
    private String accountmanager;

    public LegalPersonHasAccountDTO(String companyName, String iban, double balance, long kvkNumber,
                                    String sector, String address, String accountmanager) {
        this.companyName = companyName;
        this.iban = iban;
        this.balance = balance;
        this.kvkNumber = kvkNumber;
        this.sector = sector;
        this.address = address;
        this.accountmanager = accountmanager;
    }
}
