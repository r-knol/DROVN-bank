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
    private String accountmanager;

    public CompanyTransactionDTO(String companyName, long numberOfTransactions, long kvkNumber, String address, String accountmanager) {
        this.companyName = companyName;
        this.numberOfTransactions = numberOfTransactions;
        this.kvkNumber = kvkNumber;
        this.address = address;
        this.accountmanager = accountmanager;
    }
}