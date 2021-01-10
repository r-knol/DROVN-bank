package nl.hva.miw.internetbanking.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyTransactionDTO {

    private String companyName;
    private long numberOfTransactions;

    public CompanyTransactionDTO(String companyName, long numberOfTransactions) {
        this.companyName = companyName;
        this.numberOfTransactions = numberOfTransactions;
    }
}