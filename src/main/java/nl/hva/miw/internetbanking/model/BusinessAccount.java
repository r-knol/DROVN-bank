package nl.hva.miw.internetbanking.model;

import java.util.List;

public class BusinessAccount extends Account {

    public BusinessAccount(long rekeningID, double saldo, String IBAN, List<Transaction> transactionHistorie) {
        super(rekeningID, saldo, IBAN, transactionHistorie);
    }
}
